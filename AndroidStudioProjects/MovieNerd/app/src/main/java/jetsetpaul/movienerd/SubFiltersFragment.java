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
import android.widget.Button;

/**
 * Created by pauljoiner on 8/8/16.
 */
public class SubFiltersFragment extends Fragment {

    Button mAddFiltersButton;
    Button mShowResultsButton;
    private RecyclerView mRecyclerView;
    private SubfilterAdapter subfilterAdapter;
    private Filter filter;
    Context mContext;
    SubFilterClickListener listener;
    private LinearLayoutManager layoutManager;
    public SubFiltersFragment(){
    }

    @Override
    public void onAttach(Context context) {
        this.mContext = context;
        if(mContext instanceof SubFilterClickListener){
            listener = (SubFilterClickListener) mContext;
        } else {
            Log.d("TAGG", "Something's not right");
        }
        super.onAttach(context);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        filter = ((Filter) args.getSerializable("FILTERZ"));
        String cat = filter.getmCategory();
        Log.d("TAGG", "setArguments: "+ cat);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout_1, container, false);
        mAddFiltersButton = (Button) rootView.findViewById(R.id.more_filters_button);
        mShowResultsButton = (Button) rootView.findViewById(R.id.show_results_button);
        mAddFiltersButton.setVisibility(View.VISIBLE);
        mShowResultsButton.setVisibility(View.VISIBLE);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        subfilterAdapter = new SubfilterAdapter(filter.getmSubFilters(), (SubFilterClickListener) getActivity());
        Log.d("TAGG", "onCreateView: "+filter.getmSubFilters().size());
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        subfilterAdapter.setList(filter.getmSubFilters());
        mRecyclerView.setAdapter(subfilterAdapter);
        mAddFiltersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        mShowResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onShowResultsClicked();


//                Log.d("URL", "url: "+ MainActivity.discoverTMDB(mContext));
            }
        });

        return rootView;
    }
    public interface SubFilterClickListener {
        public void onSubfilterClicked(String selectedParams);
        void onShowResultsClicked();
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }
}
