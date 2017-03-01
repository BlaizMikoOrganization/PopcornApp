package com.blaizmiko.popcornapp.ui.adapters.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.models.movies.Image;
import com.blaizmiko.popcornapp.models.movies.Video;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder>{

    List<Video> mTrailers;
    Context mContext;
    public TrailerAdapter(Context context) {
        mTrailers = new ArrayList<>();
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_detail_movies_trailer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(Constants.Api.BaseTrailerPreviewImageUrl +mTrailers.get(position).getKey() + Constants.Api.BaseTrailerPreviewImageHighRes)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.trailerImageView);
    }

    @Override
    public int getItemCount() {
        return mTrailers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_trailer_image_image_view)
        ImageView trailerImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Public methods
    public void update(List<Video> trailers) {
        mTrailers.clear();
        mTrailers.addAll(trailers);
        notifyDataSetChanged();
    }
}
