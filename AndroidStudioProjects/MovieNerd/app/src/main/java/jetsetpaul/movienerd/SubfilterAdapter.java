package jetsetpaul.movienerd;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by pauljoiner on 8/8/16.
 */
public class SubfilterAdapter extends RecyclerView.Adapter<SubfilterAdapter.ViewHolder> {

    ArrayList<String> mSubfilters = new ArrayList<>();
    Activity mActivity;
    MenuAdapter mainAdapter;
    SubFiltersFragment.SubFilterClickListener listener;
//    SubFiltersFragment.OnItemClickListener listener;


    public SubfilterAdapter(ArrayList<String> mSubfilters, SubFiltersFragment.SubFilterClickListener listener){
        this.mSubfilters = mSubfilters;
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.main_menu_list_item, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.subfilterText.setText(mSubfilters.get(position));
        Log.d("TAGG", "Binding " + position + " "+ mSubfilters.get(position));
    }

    @Override
    public int getItemCount() {
        return mSubfilters.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView subfilterText;
        public CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            subfilterText = (TextView) itemView.findViewById(R.id.main_menu_list_text);
            mCardView = (CardView) itemView.findViewById(R.id.cardView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "You clicked " + mSubfilters.get((getAdapterPosition())), Toast.LENGTH_SHORT).show();
            mCardView.setCardBackgroundColor(Color.parseColor("#fff2f4"));
            listener.onSubfilterClicked(mSubfilters.get((getAdapterPosition())));
        }
    }
    public void setList(ArrayList<String> list){
        mSubfilters = list;
        notifyDataSetChanged();
    }
}
