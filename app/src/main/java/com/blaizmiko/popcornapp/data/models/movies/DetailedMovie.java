package com.blaizmiko.popcornapp.data.models.movies;

import com.blaizmiko.popcornapp.data.models.images.ImagesResponseModel;
import com.blaizmiko.popcornapp.data.models.videos.VideosResponseModel;
import com.google.gson.annotations.SerializedName;

public class DetailedMovie extends Movie{
    @SerializedName("images")
    private ImagesResponseModel imagesResponseModel;
    @SerializedName("videosResponseModel")
    private VideosResponseModel videosResponseModel;
    @SerializedName("similar")
    private SimilarMovies similarMovies;

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

    public SimilarMovies getSimilarMovies() {
        return similarMovies;
    }

    public void setSimilarMovies(SimilarMovies similarMovies) {
        this.similarMovies = similarMovies;
    }
}

