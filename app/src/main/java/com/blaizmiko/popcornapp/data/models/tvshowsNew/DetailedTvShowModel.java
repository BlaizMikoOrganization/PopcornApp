package com.blaizmiko.popcornapp.data.models.tvshowsNew;

import com.blaizmiko.popcornapp.data.models.Info;
import com.blaizmiko.popcornapp.data.models.genretags.Genre;
import com.blaizmiko.popcornapp.data.models.images.ImagesResponseModel;
import com.blaizmiko.popcornapp.data.models.moviesNew.SimilarMoviesResponse;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.Channel;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.Creator;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.ExternalIds;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.Season;
import com.blaizmiko.popcornapp.data.models.videos.VideosResponseModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailedTvShowModel extends BaseTvShowModel implements Info {
    @SerializedName("created_by")
    private List<Creator> creators;
    @SerializedName("genres")
    private List<Genre> genres;
    @SerializedName("last_air_date")
    private String lastAirDate;
    @SerializedName("videos")
    private VideosResponseModel videos;
    @SerializedName("images")
    private ImagesResponseModel images;
    @SerializedName("similar")
    private SimilarTvShowResponse similarTvShows;
    @SerializedName("status")
    private String status;
    @SerializedName("external_ids")
    private ExternalIds externalIds;
    @SerializedName("networks")
    private List<Channel> channels;

    public List<Creator> getCreators() {
        return creators;
    }

    public void setCreators(List<Creator> creators) {
        this.creators = creators;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ExternalIds getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(ExternalIds externalIds) {
        this.externalIds = externalIds;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }


}
