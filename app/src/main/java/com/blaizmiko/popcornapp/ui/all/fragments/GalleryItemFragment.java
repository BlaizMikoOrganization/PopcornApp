package com.blaizmiko.popcornapp.ui.all.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;

public class GalleryItemFragment extends BaseMvpFragment{
    private String imageUrl;
    @BindView(R.id.image_view_gallery)
    protected ImageView pictureImageView;


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        imageUrl = getArguments().getString(Constants.Extras.BACKDROP_URL);

        return inflater.inflate(R.layout.fragment_gallery_item, container, false);
    }

    @Override
    protected void bindViews() {
        Context context = getActivity().getApplicationContext();
        Glide.with(context)
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(pictureImageView);
    }
}
