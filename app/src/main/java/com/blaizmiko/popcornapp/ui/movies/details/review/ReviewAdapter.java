package com.blaizmiko.popcornapp.ui.movies.details.review;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.data.models.movies.ReviewMovieModel;
import com.blaizmiko.popcornapp.ui.all.adapters.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewAdapter extends BaseAdapter<ReviewAdapter.ViewHolder> {
    private List<ReviewMovieModel> reviews;

    public ReviewAdapter() {
        reviews = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie_review_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.authorTextView.setText(reviews.get(position).getAuthor());
        holder.reviewTextView.setText(reviews.get(position).getContent());
    }

    public ReviewMovieModel getItemByPosiition(final int position) {
        return reviews.get(position);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.text_view_info_movie_author)
        protected TextView authorTextView;
        @BindView(R.id.text_view_info_movie_details_review)
        protected TextView reviewTextView;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            reviewTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getAdapterPosition(), ReviewAdapter.this);
            }
        }
    }

    public void update(final List<ReviewMovieModel> reviews) {
        this.reviews.clear();
        this.reviews.addAll(reviews);
        notifyDataSetChanged();
    }
}
