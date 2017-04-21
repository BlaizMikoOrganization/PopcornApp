package com.blaizmiko.popcornapp.ui.gallery;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.blaizmiko.popcornapp.application.Constants;

@InjectViewState
public class GalleryPresenter extends MvpPresenter<GalleryView> {

    private String cinemaName;
    private String releaseDate;
    private String[] imageUrls;
    private int currentPosition;

    void initGalleryView(@NonNull final Intent intent) {
        cinemaName = intent.getStringExtra(Constants.Extras.TITLE);
        releaseDate = intent.getStringExtra(Constants.Extras.RELEASE_DATE);
        imageUrls = intent.getStringArrayExtra(Constants.Extras.URLS_ARRAY);
        currentPosition = intent.getIntExtra(Constants.Extras.POSITION, 0);

        getViewState().setGallery(imageUrls, currentPosition);
        getViewState().setCinemaName(cinemaName);
        getViewState().setReleaseDate(releaseDate);
        getViewState().setPageCount(currentPosition + 1, imageUrls.length);
    }

    void setPageCount(final int position) {
        this.currentPosition = position;
        getViewState().setPageCount(currentPosition + 1, imageUrls.length);
    }

}
