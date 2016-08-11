package jetsetpaul.movienerd;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements FiltersFragment.OnFilterSelectedListener, SubFiltersFragment.SubFilterClickListener {
    public CardView mCardView;
    public static Parameters myParams;
    public Filter selectedFilter;
    static RecyclerView recyclerView;
    static Movie_Results_Adapter movieResultsAdapter;
    static RecyclerView.LayoutManager mLayoutManager;
    static ArrayList<String> movieResultsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCardView = (CardView) findViewById(R.id.cardView);
        myParams = new Parameters(null, null, null, null);
        if (savedInstanceState == null) {
            final Fragment mFragment = new FiltersFragment();
            //add fragment to layout then call that layout below
            getSupportFragmentManager().beginTransaction().add(R.id.container, mFragment).commit();
        }
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        // Associate searchable configuration with the SearchView
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFilterSelected(Filter selectedFilter) {
        SubFiltersFragment subFiltersFragment = new SubFiltersFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("FILTERZ", selectedFilter);
        subFiltersFragment.setArguments(bundle);
        Log.d("TAGG", "onFilterSelected: " + selectedFilter.getmCategory());
        Filter filter = (Filter) bundle.getSerializable("FILTERZ");

        Log.d("TAGG", "onFilterSelected: " + filter.getmSubFilters());
        getSupportFragmentManager().beginTransaction().replace(R.id.container, subFiltersFragment)
                .addToBackStack("frag").commit();
        Log.d("TAGG", "Got here " + selectedFilter.getmSubFilters().toString());
        this.selectedFilter = selectedFilter;

    }

//    @Override
//    public void showResults() {
//        ResultsFragment resultsFragment = new ResultsFragment();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container, resultsFragment).addToBackStack("frag").commit();
//    }

    @Override
    public void onSubfilterClicked(String selectedParams) {
        if(selectedFilter.getmCategory().equals("Genre")) {
            myParams.setGenre(selectedParams);
        }else if(selectedFilter.getmCategory().equals("Year Released")){
            myParams.setDecade(selectedParams);
        }else if(selectedFilter.getmCategory().equals("Streaming Market")){
            myParams.setStreamingPlatform(selectedParams);
        }else if(selectedFilter.getmCategory().equals("Rating")){
            myParams.setScore(selectedParams);
        }
        Log.d("WOO", "Genre "+myParams.getGenre());
        Log.d("WOO", "Year Released "+myParams.getDecade());
        Log.d("WOO", "Streaming Market " +myParams.getStreamingPlatform());
        Log.d("WOO", "Rating "+ myParams.getScore());

    }

    @Override
    public void onShowResultsClicked() {
        discoverTMDB();
    }

    public static void networkGo(String url, Context context){
        ConnectivityManager connectivityManager  = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(context, "The network state is: " +
                    networkInfo.getState().toString(), Toast.LENGTH_SHORT).show();
            new DownloadUrlTask((AppCompatActivity) context).execute(url);
        } else {
            // the connection is not available
        }
    }

    private static String readInput(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
        String read;
        while((read = bufferReader.readLine()) != null) {
            stringBuilder.append(read);
        }
        return  stringBuilder.toString();
    }


    private static String downloadUrl(String url) throws IOException, JSONException {
        InputStream inputStream = null;
        try {
            URL nativeUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) nativeUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            inputStream = connection.getInputStream();
            return readInput(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private static class DownloadUrlTask extends AsyncTask<String, Void, String> {
        AppCompatActivity mActivity;
        public DownloadUrlTask(AppCompatActivity activity){
            mActivity = activity;
        }
        @Override
        protected String doInBackground(String...urls) {
            try {
                String jsonString = downloadUrl(urls[0]);
                return jsonString;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String string) {
           Bundle bundle = new Bundle();
           bundle.putString ("JSON_STRING", string);
            ResultsFragment resultsFragment = new ResultsFragment();
            resultsFragment.setArguments(bundle);
           mActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, resultsFragment).addToBackStack("frag").commit();
            Log.d("TAGG", string);

        }
    }

    public static HashMap<String, Integer> parseGenreArray(int stringArrayResourceId, Context context) {
        String[] stringArray = context.getResources().getStringArray(stringArrayResourceId);
        HashMap<String, Integer> outputArray = new HashMap<>(stringArray.length);
        for (String entry : stringArray) {
            String[] splitResult = entry.split("\\|", 2);
            outputArray.put(splitResult[0], Integer.valueOf(splitResult[1]));
        }
        return outputArray;
    }

    public static HashMap<String, String> parseDecadeArray(int stringArrayResourceId, Context context){
        String[] stringArray = context.getResources().getStringArray(stringArrayResourceId);
        HashMap<String, String> outputArray = new HashMap<>(stringArray.length);
        for (String entry : stringArray){
            String [] splitResult = entry.split("\\|", 2);
            outputArray.put(splitResult[0], String.valueOf(splitResult[1]));
        }
        return outputArray;
    }
    public static HashMap<String, String> parseScoreArray(int stringArrayResourceId, Context context){
        String[] stringArray = context.getResources().getStringArray(stringArrayResourceId);
        HashMap<String, String> outputArray = new HashMap<>(stringArray.length);
        for (String entry : stringArray){
            String [] splitResult = entry.split("\\|", 2);
            outputArray.put(splitResult[0], String.valueOf(splitResult[1]));
        }
        return outputArray;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void discoverTMDB(){
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<String, Integer> myGenreArray = parseGenreArray(R.array.tmdb_genre_values, this);
        HashMap<String, String> myDecadeArray = parseDecadeArray(R.array.tmdb_date_values, this);
        HashMap<String, String> myScoreArray = parseScoreArray(R.array.tmdb_score_average_values, this);
        if(myParams!= null){
            stringBuilder.append(Constants.TMDB_DISCOVER);
            stringBuilder.append(Constants.TMDB_KEY);
            if(myGenreArray.containsKey(myParams.getGenre())){
                int genreId = myGenreArray.get(myParams.getGenre());
                stringBuilder.append("&with_genres="+genreId);

            }if(myDecadeArray.containsKey(myParams.getDecade())){
                String decadeId = myDecadeArray.get(myParams.getDecade());
                stringBuilder.append(decadeId);
            }if(myScoreArray.containsKey(myParams.getScore())){
                String scoreId = myScoreArray.get(myParams.getScore());
                stringBuilder.append(scoreId);
            }
            networkGo(stringBuilder.toString(), this);

        }

    }
}