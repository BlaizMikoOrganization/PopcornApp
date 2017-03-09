package com.blaizmiko.popcornapp.data.models.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlayingMovies {
    @SerializedName("page")
    private int mPage;
    @SerializedName("results")
    private List<BriefMovie> mMovies;
    @SerializedName("premiereDates")
    private List<PremiereDate> mPremiereDates;
    @SerializedName("total_pages")
    private int mTotalPages;
    @SerializedName("total_results")
    private int mTotalResults;

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

    public List<PremiereDate> getPremiereDates() {
        return mPremiereDates;
    }

    public void setPremiereDates(List<PremiereDate> premiereDates) {
        mPremiereDates = premiereDates;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(int totalPages) {
        mTotalPages = totalPages;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(int totalResults) {
        mTotalResults = totalResults;
    }
}
