package com.blaizmiko.popcornapp.data.models.tvshows.detailed;

import com.blaizmiko.popcornapp.data.models.cast.Credits;
import com.blaizmiko.popcornapp.data.models.genretags.Genre;
import com.blaizmiko.popcornapp.data.models.images.ImagesResponseModel;
import com.blaizmiko.popcornapp.data.models.videos.VideosResponseModel;
import com.blaizmiko.popcornapp.data.models.tvshows.BriefTvShow;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailedTvShow extends BriefTvShow {
    @SerializedName("created_by")
    private List<Creator> creators;
    @SerializedName("episode_run_time")
    private List<Integer> episodeRunTime;
    @SerializedName("genres")
    private List<Genre> genres;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("in_production")
    private boolean inProduction;
    @SerializedName("languages")
    private List<String> languages;
    @SerializedName("last_air_date")
    private String lastAirDate;
    @SerializedName("networks")
    private List<Channel> channels;
    @SerializedName("number_of_episodes")
    private int numberOfEpisodes;
    @SerializedName("number_of_seasons")
    private int numberOfSeasons;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies;
    @SerializedName("seasons")
    private List<Season> seasons;
    @SerializedName("status")
    private String status;
    @SerializedName("type")
    private String type;

    @SerializedName("credits")
    private Credits credits;
    @SerializedName("images")
    private ImagesResponseModel imagesResponseModel;
    @SerializedName("videosResponseModel")
    private VideosResponseModel videosResponseModel;
    @SerializedName("external_ids")
    private ExternalIds externalIds;

    public List<Creator> getCreators() {
        return creators;
    }

    public void setCreators(List<Creator> creators) {
        this.creators = creators;
    }

    public List<Integer> getEpisodeRunTime() {
        return episodeRunTime;
    }

    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
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

    public boolean isInProduction() {
        return inProduction;
    }

    public void setInProduction(boolean inProduction) {
        this.inProduction = inProduction;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public ImagesResponseModel getImagesResponseModel() {
        return imagesResponseModel;
    }

    public void setImagesResponseModel(ImagesResponseModel imagesResponseModel) {
        this.imagesResponseModel = imagesResponseModel;
    }

    public VideosResponseModel getVideosResponseModel() {
        return videosResponseModel;
    }

    public void setVideosResponseModel(VideosResponseModel videosResponseModel) {
        this.videosResponseModel = videosResponseModel;
    }

    public ExternalIds getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(ExternalIds externalIds) {
        this.externalIds = externalIds;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
}
