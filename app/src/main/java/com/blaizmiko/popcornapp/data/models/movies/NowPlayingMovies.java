package com.blaizmiko.popcornapp.data.models.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlayingMovies {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<BriefMovie> movies;
    @SerializedName("premiereDates")
    private List<PremiereDate> premiereDates;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<BriefMovie> getMovies() {
        return movies;
    }

    public void setMovies(List<BriefMovie> movies) {
        this.movies = movies;
    }

    public List<PremiereDate> getPremiereDates() {
        return premiereDates;
    }

    public void setPremiereDates(List<PremiereDate> premiereDates) {
        this.premiereDates = premiereDates;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
