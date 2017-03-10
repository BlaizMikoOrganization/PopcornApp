package com.blaizmiko.popcornapp.ui.all.presentation.rating;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.data.models.rating.Rating;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder> {

    private Rating rating;
    private final int SERVICES_AMOUNT = 4;

    public RatingAdapter() {
        rating = new Rating();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie_details_rating_item, parent, false);

        int height = parent.getMeasuredHeight();
        int width = parent.getMeasuredWidth() / SERVICES_AMOUNT;

        view.setLayoutParams(new RecyclerView.LayoutParams(width, height));
        return new RatingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                holder.ratingTextView.setText(rating.getIMDb());
                holder.ratingImageView.setImageResource(R.drawable.rating);
                break;
            case 1:
                holder.ratingTextView.setText(rating.getMetascore());
                holder.ratingImageView.setImageResource(R.drawable.rating);
                break;
            case 2:
                holder.ratingTextView.setText(rating.getTomatometer());
                holder.ratingImageView.setImageResource(R.drawable.rating);
                break;
            case 3:
                holder.ratingTextView.setText(rating.getTomatoAudienceScore());
                holder.ratingImageView.setImageResource(R.drawable.rating);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return SERVICES_AMOUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return position % SERVICES_AMOUNT;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_adapter_movie_details_rating_item_service_logo)
        ImageView ratingImageView;
        @BindView(R.id.text_view_adapter_movie_details_rating_item_service_rating)
        TextView ratingTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Public methods
    public void update(Rating rating) {
        this.rating = null;
        this.rating = rating;
        notifyDataSetChanged();
    }
}
