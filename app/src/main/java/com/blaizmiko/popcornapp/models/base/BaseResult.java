package com.blaizmiko.popcornapp.models.base;

import com.google.gson.annotations.SerializedName;

public class BaseResult {

    @SerializedName("page")
    private int mPage;
    @SerializedName("total_results")
    private int mTotalResults;
    @SerializedName("total_pages")
    private int mTotalPages;

    public int getPage() {
        return mPage;
    }

    public void setPage(final int page) {
        mPage = page;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(final int totalResults) {
        mTotalResults = totalResults;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(final int totalPages) {
        mTotalPages = totalPages;
    }
}
