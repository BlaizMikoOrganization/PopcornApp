package com.blaizmiko.popcornapp.data.models.cinema;

import com.blaizmiko.popcornapp.data.models.Info;
import com.blaizmiko.popcornapp.data.models.images.ImagesResponseModel;
import com.blaizmiko.popcornapp.data.models.videos.VideosResponseModel;

public class Cinema implements Info {
    private String posterPath;
    private String backdropPath;
    private String overview;
    private ImagesResponseModel images;
    private VideosResponseModel videos;
    private SimilarCinemasResponse similarResponse;

    @Override
    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public String getBackdropPath() {
        return backdropPath;
    }

    @Override
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public ImagesResponseModel getImages() {
        return images;
    }

    public void setImages(ImagesResponseModel images) {
        this.images = images;
    }

    public VideosResponseModel getVideos() {
        return videos;
    }

    public void setVideos(VideosResponseModel videos) {
        this.videos = videos;
    }

    public SimilarCinemasResponse getSimilarResponse() {
        return similarResponse;
    }

    public void setSimilarResponse(SimilarCinemasResponse similarResponse) {
        this.similarResponse = similarResponse;
    }
}
