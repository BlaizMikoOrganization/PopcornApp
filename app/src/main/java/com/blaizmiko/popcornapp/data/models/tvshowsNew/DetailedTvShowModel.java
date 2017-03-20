package com.blaizmiko.popcornapp.data.models.tvshowsNew;

import com.blaizmiko.popcornapp.data.models.Info;
import com.blaizmiko.popcornapp.data.models.genretags.Genre;
import com.blaizmiko.popcornapp.data.models.images.ImagesResponseModel;
import com.blaizmiko.popcornapp.data.models.moviesNew.SimilarMoviesResponse;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.Creator;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.Season;
import com.blaizmiko.popcornapp.data.models.videos.VideosResponseModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailedTvShowModel extends BaseTvShowModel implements Info {
    @SerializedName("created_by")
    private List<Creator> creators;
    @SerializedName("episode_run_time")
    private List<Integer> episodeRunTime;
    @SerializedName("genres")
    private List<Genre> genres;
    @SerializedName("in_production")
    private boolean inProduction;
    @SerializedName("last_air_date")
    private String lastAirDate;
    @SerializedName("number_of_episodes")
    private int numberOfEpisodes;
    @SerializedName("number_of_seasons")
    private int numberOfSeasons;
    @SerializedName("seasons")
    private List<Season> seasons;
    @SerializedName("videos")
    private VideosResponseModel videos;
    @SerializedName("images")
    private ImagesResponseModel images;
    @SerializedName("similar")
    private SimilarTvShowResponse similarTvShows;

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

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    @Override
    public VideosResponseModel getVideos() {
        return videos;
    }

    @Override
    public void setVideos(VideosResponseModel videos) {
        this.videos = videos;
    }

    @Override
    public ImagesResponseModel getImages() {
        return images;
    }

    @Override
    public void setImages(ImagesResponseModel images) {
        this.images = images;
    }

    public SimilarTvShowResponse getSimilarTvShows() {
        return similarTvShows;
    }

    public void setSimilarTvShows(SimilarTvShowResponse similarTvShows) {
        this.similarTvShows = similarTvShows;
    }
}
