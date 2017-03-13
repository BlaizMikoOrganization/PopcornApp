package com.blaizmiko.popcornapp.data.models.movies;

import com.blaizmiko.popcornapp.data.models.genretags.Genre;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Movie extends BaseMovie{
    @SerializedName("belongs_to_collection")
    private MovieSeries movieSeries;
    @SerializedName("budget")
    private int budget;
    @SerializedName("genres")
    private List<Genre> genres;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies;
    @SerializedName("production_countries")
    private List<ProductionCountry> productionCountries;
    @SerializedName("revenue")
    private int revenue;
    @SerializedName("runtime")
    private int runtime;
    @SerializedName("spoken_languages")
    private List<UsedLanguage> usedLanguages;
    @SerializedName("status")
    private String status;
    @SerializedName("tagline")
    private String tagline;

    public MovieSeries getMovieSeries() {
        return movieSeries;
    }

    public void setMovieSeries(MovieSeries movieSeries) {
        this.movieSeries = movieSeries;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<UsedLanguage> getSpokenLanguages() {
        return usedLanguages;
    }

    public void setSpokenLanguages(List<UsedLanguage> usedLanguages) {
        this.usedLanguages = usedLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<UsedLanguage> getUsedLanguages() {
        return usedLanguages;
    }

    public void setUsedLanguages(List<UsedLanguage> usedLanguages) {
        this.usedLanguages = usedLanguages;
    }
}
