package com.blaizmiko.popcornapp.models.Movie;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Movie extends BaseMovie{
    @SerializedName("belongs_to_collection")
    private Collection mBelongsToCollection;
    @SerializedName("budget")
    private int mBudget;
    @SerializedName("genres")
    private List<Genre> mGenres;
    @SerializedName("homepage")
    private String mHomepage;
    @SerializedName("imdbId")
    private String mImdbId;
    @SerializedName("production_companies")
    private List<Company> mCompanies;
    @SerializedName("production_countries")
    private List<Country> mCountries;
    @SerializedName("revenue")
    private int mRevenue;
    @SerializedName("runtime")
    private int mRuntime;
    @SerializedName("spoken_languages")
    private List<Language> mLanguages;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("tagline")
    private String mTagline;

    public Collection getBelongsToCollection() {
        return mBelongsToCollection;
    }

    public void setBelongsToCollection(Collection belongsToCollection) {
        mBelongsToCollection = belongsToCollection;
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

    public List<Company> getCompanies() {
        return mCompanies;
    }

    public void setCompanies(List<Company> companies) {
        mCompanies = companies;
    }

    public List<Country> getCountries() {
        return mCountries;
    }

    public void setCountries(List<Country> countries) {
        mCountries = countries;
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

    public List<Language> getLanguages() {
        return mLanguages;
    }

    public void setLanguages(List<Language> languages) {
        mLanguages = languages;
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
