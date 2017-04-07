package com.blaizmiko.popcornapp.data.models.movies;
import com.blaizmiko.popcornapp.data.models.cinema.BaseCinemaModel;
import com.google.gson.annotations.SerializedName;

public class BaseMovieModel extends BaseCinemaModel {
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
