package com.blaizmiko.popcornapp.ui.all.presentation.photos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.images.ImageModel;
import com.blaizmiko.popcornapp.ui.all.adapters.BaseAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosAdapter extends BaseAdapter<PhotosAdapter.ViewHolder> {

    private List<ImageModel> photos;
    private Context context;

    public PhotosAdapter(final Context context) {
        this.context = context;
        photos = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_photo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + photos.get(position).getFilePath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.photoImageView);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.image_view_adapter_movie_details_photo_item_photo)
        ImageView photoImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            photoImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition(), PhotosAdapter.this);
        }
    }

    //Public methods
    public void update(List<ImageModel> imageModels) {
        photos.clear();
        photos.addAll(imageModels);
        notifyDataSetChanged();
    }

    public ImageModel getItemByPosition(int position) {
        return photos.get(position);
    }

    public List<ImageModel> getAllItems() {
        return photos;
    }
}
