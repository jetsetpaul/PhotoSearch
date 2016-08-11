package jetsetpaul.movienerd;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pauljoiner on 8/10/16.
 */
public class Movie_Results_Adapter extends RecyclerView.Adapter<Movie_Results_Adapter.ProductViewHolder>{
     ArrayList<Movie> MovieListArray;

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView titleText;
        TextView dateText;
        TextView scoreText;
        public ProductViewHolder(View itemView){
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.movie_title_text);
            dateText = (TextView) itemView.findViewById(R.id.movie_date_text);
            scoreText = (TextView) itemView.findViewById(R.id.movie_score_text);
        }
    }

    public Movie_Results_Adapter(ArrayList<Movie> movieList){
        MovieListArray = movieList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_result_list_item, parent, false);

        ProductViewHolder vh = new ProductViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.titleText.setText(MovieListArray.get(position).getmTitle());
        Log.d("TAGG", MovieListArray.toString());
        holder.dateText.setText(MovieListArray.get(position).getmReleaseYear());
        holder.scoreText.setText(MovieListArray.get(position).getmScore());
    }

    @Override
    public int getItemCount() {
        return MovieListArray.size();
    }

    public void setList(ArrayList<Movie> list){
        MovieListArray = list;
        notifyDataSetChanged();
    }

}
