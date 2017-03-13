package com.blaizmiko.popcornapp.ui.all.presentation.trailers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.videos.Trailer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder>{

    private List<Trailer> trailers;
    private Context context;
    public TrailersAdapter(Context context) {
        trailers = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie_details_trailer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(Constants.YouTubeApi.BASE_TRAILER_PREVIEW_IMAGE_URL + trailers.get(position).getKey() + Constants.YouTubeApi.TRAILER_PREVIEW_IMAGE_HIGH_RES)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.trailerImageView);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_adapter_movie_details_trailer_preview_image)
        ImageView trailerImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Public methods
    public void update(List<Trailer> trailers) {
        this.trailers.clear();
        this.trailers.addAll(trailers);
        notifyDataSetChanged();
    }
}
