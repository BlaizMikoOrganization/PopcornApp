package com.blaizmiko.popcornapp.models.movie;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Movie extends BaseMovie{
    @SerializedName("belongs_to_collection")
    private MovieSeries mMovieSeries;
    @SerializedName("budget")
    private int mBudget;
    @SerializedName("genres")
    private List<Genre> mGenres;
    @SerializedName("homepage")
    private String mHomepage;
    @SerializedName("imdbId")
    private String mImdbId;
    @SerializedName("production_companies")
    private List<ProductionCompany> mProductionCompanies;
    @SerializedName("production_countries")
    private List<ProductionCountry> mProductionCountries;
    @SerializedName("revenue")
    private int mRevenue;
    @SerializedName("runtime")
    private int mRuntime;
    @SerializedName("spoken_languages")
    private List<UsedLanguage> mUsedLanguages;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("tagline")
    private String mTagline;

    public MovieSeries getMovieSeries() {
        return mMovieSeries;
    }

    public void setMovieSeries(MovieSeries movieSeries) {
        mMovieSeries = movieSeries;
    }

    public int getBudget() {
        return mBudget;
    }

    public void setBudget(int budget) {
        mBudget = budget;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

    public String getHomepage() {
        return mHomepage;
    }

    public void setHomepage(String homepage) {
        mHomepage = homepage;
    }

    public String getImdbId() {
        return mImdbId;
    }

    public void setImdbId(String imdbId) {
        mImdbId = imdbId;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return mProductionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        mProductionCompanies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return mProductionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        mProductionCountries = productionCountries;
    }

    public int getRevenue() {
        return mRevenue;
    }

    public void setRevenue(int revenue) {
        mRevenue = revenue;
    }

    public int getRuntime() {
        return mRuntime;
    }

    public void setRuntime(int runtime) {
        mRuntime = runtime;
    }

    public List<UsedLanguage> getSpokenLanguages() {
        return mUsedLanguages;
    }

    public void setSpokenLanguages(List<UsedLanguage> usedLanguages) {
        mUsedLanguages = usedLanguages;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getTagline() {
        return mTagline;
    }

    public void setTagline(String tagline) {
        mTagline = tagline;
    }
}
