package com.blaizmiko.popcornapp.data.models.tvshows;

import com.blaizmiko.popcornapp.data.models.movies.BriefMovie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopTvShows {
    @SerializedName("page")
    private int page;
    private List<BriefTvShow> tvShows;
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

    public List<BriefTvShow> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<BriefTvShow> tvShows) {
        this.tvShows = tvShows;
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
