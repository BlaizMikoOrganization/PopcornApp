package com.blaizmiko.popcornapp.models.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlayingMovies {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<ShortMovie> movies;
    @SerializedName("premiereDates")
    private List<PremiereDate> premiereDates;
    @SerializedName("total_pages")
    private int total_pages;
    @SerializedName("total_results")
    private int total_results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ShortMovie> getMovies() {
        return movies;
    }

    public void setMovies(List<ShortMovie> movies) {
        this.movies = movies;
    }

    public List<PremiereDate> getPremiereDates() {
        return premiereDates;
    }

    public void setPremiereDates(List<PremiereDate> premiereDates) {
        this.premiereDates = premiereDates;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
