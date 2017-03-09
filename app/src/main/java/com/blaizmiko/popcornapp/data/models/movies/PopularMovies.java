package com.blaizmiko.popcornapp.data.models.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularMovies {
    @SerializedName("page")
    private int mPage;
    @SerializedName("results")
    private List<BriefMovie> mMovies;
    @SerializedName("total_results")
    private int mTotalResults;
    @SerializedName("total_pages")
    private int mTotalPages;

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public List<BriefMovie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<BriefMovie> movies) {
        mMovies = movies;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(int totalResults) {
        mTotalResults = totalResults;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(int totalPages) {
        mTotalPages = totalPages;
    }
}
