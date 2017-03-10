package com.blaizmiko.popcornapp.data.models.tvshows;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularTvShows {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<BriefTvShow> tvShows;
    @SerializedName("total_results")
    private int totalResult;
    @SerializedName("total_pages")
    private int totalPages;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<BriefTvShow> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<BriefTvShow> tvShows) {
        this.tvShows = tvShows;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
