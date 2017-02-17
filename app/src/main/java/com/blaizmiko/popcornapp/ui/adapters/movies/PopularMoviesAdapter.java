package com.blaizmiko.popcornapp.ui.adapters.movies;

import android.content.Context;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.models.movies.BriefMovie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.iarcuschin.simpleratingbar.*;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.MovieViewHolder> {

    private Context mContext;
    private List<BriefMovie> mMovieList;

    public PopularMoviesAdapter(Context context) {
        mContext = context;
        mMovieList = new ArrayList<>();
    }

    @Override
    public PopularMoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_popular_movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        holder.titleTextView.setText(mMovieList.get(position).getTitle());
        int convertValue = 2;
        holder.avrVoteRatingBar.setRating((float) mMovieList.get(position).getVoteAverage()/convertValue);
        holder.avrVoteTextView.setText(Double.toString(mMovieList.get(position).getVoteAverage()));

        Glide.with(mContext)
                .load(Constants.Api.BasePopularMovieImageUrl + mMovieList.get(position).getPosterPath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.posterImageView);
    }

    public int getItemCount() {
        return mMovieList.size();
    }


    class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_popular_movie_poster_image_view)
        ImageView posterImageView;
        @BindView(R.id.adapter_popular_movie_title_text_view)
        TextView titleTextView;
        @BindView(R.id.adapter_popular_movie_avr_vote_rating_bar)
        SimpleRatingBar avrVoteRatingBar;
        @BindView(R.id.adapter_popular_movie_avr_vote_text_view)
        TextView avrVoteTextView;





        MovieViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    //Public methods
    public void update(final List<BriefMovie> movieList) {
        mMovieList.clear();
        mMovieList.addAll(movieList);
        notifyDataSetChanged();
    }
}
