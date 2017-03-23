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
    private int[] reviewsLines;

    public ReviewAdapter() {
        reviews = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie_review_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.authorTextView.setText(reviews.get(position).getAuthor());
        holder.reviewTextView.setText(reviews.get(position).getContent());
    }

    public int [] getReviewsLines() {
        return reviewsLines;
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.text_view_info_movie_author)
        TextView authorTextView;
        @BindView(R.id.text_view_info_movie_details_review)
        TextView reviewTextView;

        ViewHolder(View itemView) {
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

    public void update(List<ReviewMovieModel> reviews) {
        this.reviews.clear();
        this.reviews.addAll(reviews);
        reviewsLines = new int[reviews.size()];
        notifyDataSetChanged();
    }
}
