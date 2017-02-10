package com.blaizmiko.popcornapp.ui.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.models.Movie.ShortMovie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShortMoviesAdapter extends RecyclerView.Adapter<ShortMoviesAdapter.MovieViewHolder> {

    private List<ShortMovie> mMovieList;
    private Context mContext;

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_poster_image_view)
        ImageView posterImageView;
//        @BindView(R.id.movie_title_text_view)
//        TextView titleTextView;

        public MovieViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public ShortMoviesAdapter(List<ShortMovie> popularMoviesList, Context context) {
        mMovieList = popularMoviesList;
        mContext = context;
    }

    @Override
    public ShortMoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        //holder.titleTextView.setText(mMovieList.get(position).getTitle());
        //testShit
        switch (position) {
            case 0:
                holder.posterImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.test_poster_1));
                break;
            case 1:
                holder.posterImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.test_poster_2));
                break;
            case 2:
                holder.posterImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.test_poster_1));
                break;
            case 3:
                holder.posterImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.test_poster_2));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }
}
