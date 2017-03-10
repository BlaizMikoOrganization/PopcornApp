package com.blaizmiko.popcornapp.data.models.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularMovies {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<BriefMovie> movies;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

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

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
