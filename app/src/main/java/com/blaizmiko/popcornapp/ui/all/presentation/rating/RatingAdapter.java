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

    private List<RatingModel> ratings;
    private final int SERVICES_AMOUNT = 4;

    public RatingAdapter() {
        ratings = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rating_item, parent, false);

        int height = parent.getMeasuredHeight();
        int width = parent.getMeasuredWidth() / SERVICES_AMOUNT;

        view.setLayoutParams(new RecyclerView.LayoutParams(width, height));
        return new RatingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ratingTextView.setText(ratings.get(position).getRating());
        holder.ratingImageView.setImageResource(R.drawable.rating);
    }

    @Override
    public int getItemCount() {
        return ratings.size();
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
    public void update(List<RatingModel> rating) {
        this.ratings = null;
        this.ratings = rating;
        notifyDataSetChanged();
    }
}
