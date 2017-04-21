package com.blaizmiko.popcornapp.ui.gallery;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;

interface GalleryView extends MvpView {

    void setCinemaName(@NonNull final String cinemaName);

    void setReleaseDate(@NonNull final String releaseDate);

    void setPageCount(final int currentPosition, final int totalAmount);

    void setGallery(final String[] imageUrls, final int currentPosition);
}
