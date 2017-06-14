package com.blaizmiko.popcornapp.data.models.tvshows;

import com.blaizmiko.popcornapp.data.db.models.movies.GenreDBModel;
import com.blaizmiko.popcornapp.data.models.images.ImagesResponse;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.ChannelTvShowModel;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.CreatorTvShowModel;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.ExternalIdsTvShowModel;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.SeasonTvShowModel;
import com.blaizmiko.popcornapp.data.models.videos.VideosResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailedTvShowModel extends BaseTvShowModel {
    @SerializedName("created_by")
    private List<CreatorTvShowModel> creators;
    @SerializedName("genres")
    private List<GenreDBModel> genres;
    @SerializedName("last_air_date")
    private String lastAirDate;
    @SerializedName("videos")
    private VideosResponse videos;
    @SerializedName("images")
    private ImagesResponse images;
    @SerializedName("similar")
    private BaseTvShowListResponse similarTvShows;
    @SerializedName("status")
    private String status;
    @SerializedName("external_ids")
    private ExternalIdsTvShowModel externalIds;
    @SerializedName("networks")
    private List<ChannelTvShowModel> channels;
    @SerializedName("seasons")
    private List<SeasonTvShowModel> seasons;

    public List<CreatorTvShowModel> getCreators() {
        return creators;
    }

    public void setCreators(List<CreatorTvShowModel> creators) {
        this.creators = creators;
    }

    public List<GenreDBModel> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDBModel> genres) {
        this.genres = genres;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public VideosResponse getVideos() {
        return videos;
    }

    public void setVideos(VideosResponse videos) {
        this.videos = videos;
    }

    public ImagesResponse getImages() {
        return images;
    }

    public void setImages(ImagesResponse images) {
        this.images = images;
    }

    public BaseTvShowListResponse getSimilarTvShows() {
        return similarTvShows;
    }

    public void setSimilarTvShows(BaseTvShowListResponse similarTvShows) {
        this.similarTvShows = similarTvShows;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ExternalIdsTvShowModel getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(ExternalIdsTvShowModel externalIds) {
        this.externalIds = externalIds;
    }

    public List<ChannelTvShowModel> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelTvShowModel> channels) {
        this.channels = channels;
    }

    public List<SeasonTvShowModel> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonTvShowModel> seasons) {
        this.seasons = seasons;
    }
}
