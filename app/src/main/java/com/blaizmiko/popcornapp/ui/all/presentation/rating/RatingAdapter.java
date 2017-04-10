package com.blaizmiko.popcornapp.ui.all.presentation.rating;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.data.models.rating.RatingModel;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder> {
    private final String RATING_NAME_IMDB = "Internet Movie Database";
    private final String RATING_NAME_METACRITIC = "Metacritic";
    private final String RATING_NAME_ROTTEN_TOMATOES = "Rotten Tomatoes";
    private final String RATING_NAME_MOVIE_DB = "The movie db";

    private List<RatingModel> ratings;

    public RatingAdapter() {
        ratings = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rating_item, parent, false);

        final int height = parent.getMeasuredHeight();
        final int width = parent.getMeasuredWidth() / ratings.size();

        view.setLayoutParams(new RecyclerView.LayoutParams(width, height));
        return new RatingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.ratingTextView.setText(ratings.get(position).getRating());
        holder.ratingImageView.setImageResource(ratings.get(position).getSiteLogo());
    }

    @Override
    public int getItemCount() {
        return ratings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_adapter_movie_details_rating_item_service_logo)
        protected ImageView ratingImageView;
        @BindView(R.id.text_view_adapter_movie_details_rating_item_service_rating)
        protected TextView ratingTextView;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Public methods
    public void update(final List<RatingModel> ratings) {
        this.ratings.clear();
        this.ratings.addAll(ratings);

        for (RatingModel rating : ratings) {
            switch (rating.getSite()) {
                case RATING_NAME_IMDB:
                    rating.setSiteLogo(R.drawable.icon_rating_imdb);
                    break;
                case RATING_NAME_METACRITIC:
                    rating.setSiteLogo(R.drawable.icon_rating_metacritic);
                    break;
                case RATING_NAME_MOVIE_DB:
                    rating.setSiteLogo(R.drawable.icon_rating_moviedb);
                    break;
                case RATING_NAME_ROTTEN_TOMATOES:
                    rating.setSiteLogo(R.drawable.icon_rating_rotten_tomatoes);
                    break;
            }
        }
        notifyDataSetChanged();
    }
}
