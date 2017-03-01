package com.blaizmiko.popcornapp.ui.adapters.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.models.movies.Genre;
import com.blaizmiko.popcornapp.models.movies.Image;
import com.blaizmiko.popcornapp.models.movies.MovieImages;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder>{

    private List<Image> mMovieImages;
    private Context mContext;

    public ImagesAdapter(Context context) {
        mContext = context;
        mMovieImages = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie_images_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mMovieImages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_movie_images_pictures_image_view)
        ImageView picture;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Public methods
    public void update(List<Image> images) {
        mMovieImages.clear();
        mMovieImages.addAll(images);
        notifyDataSetChanged();
    }
}
