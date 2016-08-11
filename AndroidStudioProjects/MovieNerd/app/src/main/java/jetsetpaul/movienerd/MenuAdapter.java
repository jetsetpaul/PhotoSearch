package jetsetpaul.movienerd;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pauljoiner on 8/6/16.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private  Activity mActivity;
    private List<Filter> mainMenuOptions = new ArrayList<Filter>();
    FiltersFragment.OnFilterSelectedListener listener;
    Filter filter;

    public MenuAdapter(Activity mActivity, FiltersFragment.OnFilterSelectedListener listener) {
        this.mActivity = mActivity;
        this.listener = listener;
        //xml array?
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.main_menu_list_item, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mFilter.setText(mainMenuOptions.get(position).getmCategory());
//        holder.bind(mainMenuOptions.get(position).getmCategory().toString(), (AdapterView.OnItemClickListener) listener);
    }

    @Override
    public int getItemCount() {
        return mainMenuOptions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mFilter;


        public ViewHolder(View v) {

            super(v);

            mFilter = (TextView) v.findViewById(R.id.main_menu_list_text);
            v.setOnClickListener(this);
        }
//        public void bind(final Filter filter, final OnItemClickListener listener){


        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onFilterSelected(mainMenuOptions.get(getAdapterPosition()));
                Log.d("TAGG", mainMenuOptions.get(getAdapterPosition()).getmSubFilters().toString());
            }

        }
    }

    public void setList(ArrayList<Filter> list){
        mainMenuOptions = list;
        notifyDataSetChanged();
    }

}

