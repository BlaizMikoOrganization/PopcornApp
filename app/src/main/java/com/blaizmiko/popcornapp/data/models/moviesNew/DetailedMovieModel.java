package com.blaizmiko.popcornapp.data.models.moviesNew;

import com.blaizmiko.popcornapp.data.models.Info;
import com.blaizmiko.popcornapp.data.models.genretags.Genre;
import com.blaizmiko.popcornapp.data.models.images.ImagesResponseModel;
import com.blaizmiko.popcornapp.data.models.videos.VideosResponseModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailedMovieModel extends BaseMovieModel implements Info {
    @SerializedName("budget")
    private int budget;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("revenue")
    private int revenue;
    @SerializedName("runtime")
    private int runtime;
    @SerializedName("images")
    private ImagesResponseModel images;
    @SerializedName("videos")
    private VideosResponseModel videos;
    @SerializedName("similar")
    private SimilarMoviesResponse similarMovies;
    @SerializedName("genres")
    private List<Genre> genres;

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
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

    @Override
    public ImagesResponseModel getImages() {
        return images;
    }

    @Override
    public void setImages(ImagesResponseModel images) {
        this.images = images;
    }

    @Override
    public VideosResponseModel getVideos() {
        return videos;
    }

    @Override
    public void setVideos(VideosResponseModel videos) {
        this.videos = videos;
    }

    public SimilarMoviesResponse getSimilarMovies() {
        return similarMovies;
    }

    public void setSimilarMovies(SimilarMoviesResponse similarMovies) {
        this.similarMovies = similarMovies;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
