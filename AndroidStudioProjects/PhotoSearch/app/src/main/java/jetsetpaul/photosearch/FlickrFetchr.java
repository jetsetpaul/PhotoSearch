package jetsetpaul.photosearch;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pauljoiner on 9/29/16.
 */

public class FlickrFetchr {

    private static final String TAG = "FlickrFetchr";

    private static final String API_KEY = "b4bbd154e928261405919b746be8bbb1";
    private static final String FETCH_RECENTS_METHOD = "flickr.photos.getRecent";
    private static final String SEARCH_METHOD = "flickr.photos.search";
    private static final Uri ENDPOINT = Uri
            .parse("https://api.flickr.com/services/rest/")
            .buildUpon()
            .appendQueryParameter("api_key", API_KEY)
            .appendQueryParameter("format", "json")
            .appendQueryParameter("nojsoncallback", "1")
            .appendQueryParameter("extras", "url_s, url_o")
            .build();


    public byte[] getURLBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getURLString(String urlSpec) throws IOException{
        return new String(getURLBytes(urlSpec));
    }

    public List<Photo> fetchRecentPhotos(){
        String url = buildUrl(FETCH_RECENTS_METHOD, null);
        return downloadPhotoItems(url);
    }

    public List<Photo> searchPhotos(String query){
        String url = buildUrl(SEARCH_METHOD, query);
        return downloadPhotoItems(url);
    }

    private List<Photo> downloadPhotoItems(String url){

        List<Photo> photos = new ArrayList<>();
        try{

            String jsonString = getURLString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(photos, jsonBody);
        } catch (JSONException je){
            Log.e(TAG, "Failed to parse JSON", je);
        }
        catch (IOException ioe){
            Log.e(TAG, "Failed to fetch items", ioe);
        }
        return photos;
    }

    private String buildUrl(String method, String query){
        Uri.Builder uriBuilder = ENDPOINT.buildUpon()
                .appendQueryParameter("method", method);
        if (method.equals(SEARCH_METHOD)){
            uriBuilder.appendQueryParameter("text", query);
        }
        return uriBuilder.build().toString();
    }

    private void parseItems(List<Photo> photos, JSONObject jsonBody)
        throws IOException, JSONException {
        JSONObject photosJsonObject = jsonBody.getJSONObject("photos");
        JSONArray photoJsonArray = photosJsonObject.getJSONArray("photo");

        for (int i = 0; i < photoJsonArray.length(); i++){
            JSONObject photoJsonObject = photoJsonArray.getJSONObject(i);

            Photo photo = new Photo();
            photo.setmId(photoJsonObject.getString("id"));
            photo.setmCaption(photoJsonObject.getString("title"));

            if (!photoJsonObject.has("url_s")){
                continue;
            }
            if (!photoJsonObject.has("url_o")){
                continue;
            }

            photo.setmURL(photoJsonObject.getString("url_s"));
            photo.setmLargeURL(photoJsonObject.getString("url_o"));
            photos.add(photo);
        }
    }
}
