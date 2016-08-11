package jetsetpaul.movienerd;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pauljoiner on 8/10/16.
 */
public class ResultsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    Movie_Results_Adapter results_adapter;
    Context mContext;
    ArrayList<Movie> movieList;
    public ResultsFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout_1, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        mRecyclerView.setVisibility(View.VISIBLE);
        results_adapter = new Movie_Results_Adapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(results_adapter);
        Log.d("TAGG", "AAAH" + movieList.toString());
        return rootView;
    }

    public void parseJson(String jsonToParse) throws JSONException {


        JSONObject responseObject= new JSONObject(jsonToParse);
        JSONArray resultsArray = responseObject.getJSONArray("results");
        for(int i = 0; i < resultsArray.length(); i++){
            JSONObject movie = resultsArray.getJSONObject(i);
            String movieName = movie.getString("title");
            String movieReleaseDate = movie.getString("release_date");
            String movieScore = movie.getString("vote_average");
            Movie movie1 = new Movie(movieName, movieReleaseDate, movieScore);
            movieList.add(movie1);
//            movieResultsList.add(movieReleaseDate);
        }

    }

    @Override
    public void setArguments(Bundle args) {
        movieList = new ArrayList<>();
        String arguments = args.getString("JSON_STRING");
        try {
            parseJson(arguments);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.setArguments(args);
    }
}
