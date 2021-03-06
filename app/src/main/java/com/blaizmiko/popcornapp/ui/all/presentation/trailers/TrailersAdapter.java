package com.blaizmiko.popcornapp.ui.all.presentation.trailers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.db.models.movies.VideoDBModel;
import com.blaizmiko.popcornapp.ui.all.adapters.BaseAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailersAdapter extends BaseAdapter<TrailersAdapter.ViewHolder>{

    private List<VideoDBModel> trailers;
    private final Context context;
    public TrailersAdapter(final Context context) {
        trailers = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_info_trailer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(context)
                .load(Constants.YouTubeApi.BASE_TRAILER_PREVIEW_IMAGE_URL + trailers.get(position).getKey() + Constants.YouTubeApi.TRAILER_PREVIEW_IMAGE_HIGH_RES)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.trailerImageView);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public VideoDBModel getItemByPosition(final int position) {
        return trailers.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.image_view_info_trailer_preview_image)
        protected ImageView trailerImageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            trailerImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            itemClickListener.onItemClick(v, getAdapterPosition(), TrailersAdapter.this);
        }
    }

    //Public methods
    public void update(final List<VideoDBModel> trailers) {
        this.trailers.clear();
        this.trailers.addAll(trailers);
        notifyDataSetChanged();
    }
}
