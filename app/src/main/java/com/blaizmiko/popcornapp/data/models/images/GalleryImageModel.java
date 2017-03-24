package com.blaizmiko.popcornapp.data.models.images;

import android.os.Parcel;
import android.os.Parcelable;

public class GalleryImageModel {
    private String filePath;
    private String cinemaName;
    private String releaseDate;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        cinemaName = cinemaName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }



}
