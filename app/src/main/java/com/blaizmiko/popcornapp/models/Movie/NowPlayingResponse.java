package com.blaizmiko.popcornapp.models.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlayingResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<ShortMovie> movies;
    @SerializedName("dates")
    private List<Date> dates;
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

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
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
