package jetsetpaul.movienerd;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by pauljoiner on 8/7/16.
 */
public class FiltersFragment extends Fragment {
    private ArrayList<String> genreFilters= new ArrayList<>();
    private ArrayList<String> streamingFilters= new ArrayList<>();
    private ArrayList<String> ratingFilters= new ArrayList<>();
    private ArrayList<String> yearReleasedFilters= new ArrayList<>();

    Activity mActivity;
    private ArrayList<Filter> mainMenuOptions = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private MenuAdapter adapter;



    public FiltersFragment(){
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (Activity) activity;
        setRetainInstance(true);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout_1, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        try{adapter = new MenuAdapter(mActivity, (OnFilterSelectedListener) getActivity());}catch (ClassCastException cce){
            Log.d("TAGG", "Class catch xception");
        }
        mainMenuOptions = new ArrayList<>();
        mainMenuOptions.add(new Filter("Genre", genreFilters));
        mainMenuOptions.add(new Filter("Year Released", yearReleasedFilters));
        mainMenuOptions.add(new Filter("Streaming Market", streamingFilters));
        mainMenuOptions.add(new Filter("Rating", ratingFilters));
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
        adapter.setList(mainMenuOptions);
        if(genreFilters.size()==0){
            fillSubFilters();
        }

        return rootView;
    }
    @Override
    public void onViewCreated(View view , Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }
    public void fillSubFilters(){
        genreFilters.add("Action");
        genreFilters.add("Adventure");
        genreFilters.add("Animation");
        genreFilters.add("Comedy");
        genreFilters.add("Crime");
        genreFilters.add("Documentary");
        genreFilters.add("Drama");
        genreFilters.add("Family");
        genreFilters.add("Fantasy");
        genreFilters.add("Foreign");
        genreFilters.add("History");
        genreFilters.add("Horror");
        genreFilters.add("Music");
        genreFilters.add("Mystery");
        genreFilters.add("Romance");
        genreFilters.add("Science Fiction");
        genreFilters.add("TV Movie");
        genreFilters.add("Thriller");
        genreFilters.add("War");
        genreFilters.add("Western");
        streamingFilters.add("Netflix");
        streamingFilters.add("Hulu");
        streamingFilters.add("Amazon Prime");
        streamingFilters.add("Itunes");
        yearReleasedFilters.add("2010s");
        yearReleasedFilters.add("2000s");
        yearReleasedFilters.add("1990s");
        yearReleasedFilters.add("1980s");
        yearReleasedFilters.add("1970s");
        yearReleasedFilters.add("1960s");
        yearReleasedFilters.add("1950s");
        yearReleasedFilters.add("1940s");
        yearReleasedFilters.add("1930s");
        yearReleasedFilters.add("1920s");
        yearReleasedFilters.add("1910s");
        ratingFilters.add("10/10");
        ratingFilters.add("9/10");
        ratingFilters.add("8/10");
        ratingFilters.add("7/10");
        ratingFilters.add("6/10");
        ratingFilters.add("5/10");
        ratingFilters.add("4/10");
        ratingFilters.add("3/10");
        ratingFilters.add("2/10");
        ratingFilters.add("1/10");
    }
    public interface OnFilterSelectedListener {
        public void onFilterSelected(Filter selectedFilter);
    }
}
