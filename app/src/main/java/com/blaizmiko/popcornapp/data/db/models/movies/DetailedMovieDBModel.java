package com.blaizmiko.popcornapp.data.db.models.movies;

import com.blaizmiko.popcornapp.data.db.interfaces.movies.IDetailedMovie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DetailedMovieDBModel extends RealmObject implements IDetailedMovie {
    @Expose
    @PrimaryKey
    @SerializedName("id")
    private long id;
    @Expose
    @SerializedName("poster_path")
    private String posterPath;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("vote_average")
    private double voteAverage;
    @Expose
    @SerializedName("backdrop_path")
    private String backdropPath;
    @Expose
    @SerializedName("overview")
    private String overview;
    @Expose
    @SerializedName("release_date")
    private String releaseDate;
    @Expose
    @SerializedName("budget")
    private int budget;
    @Expose
    @SerializedName("imdb_id")
    private String imdbId;
    @Expose
    @SerializedName("revenue")
    private int revenue;
    @Expose
    @SerializedName("runtime")
    private int runtime;
    @Expose
    public RealmList<GenreDBModel> genres = new RealmList<>();
    @Expose
    public RealmList<VideoDBModel> videosList = new RealmList<>();
    @Expose(deserialize = false, serialize = false)
    public RealmList<ImageDBModel> posters = new RealmList<>();
    @Expose(deserialize = false, serialize = false)
    public RealmList<ImageDBModel> backdrops = new RealmList<>();


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }
    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

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

    public void addGenres(final List<GenreDBModel> genres) {
        this.genres.addAll(genres);
    }
    public RealmList<GenreDBModel> getGenres() {
        return genres;
    }
    public void setGenres(RealmList<GenreDBModel> genres) {
        this.genres = genres;
    }


    public void addPosters(final List<ImageDBModel> posters) {
        this.posters.addAll(posters);
    }
    public RealmList<ImageDBModel> getPosters() {
        return posters;
    }
    public void setPosters(RealmList<ImageDBModel> posters) {
        this.posters = posters;
    }

    public void addBackdrops(final List<ImageDBModel> backdrops) {
        this.backdrops.addAll(backdrops);
    }
    public RealmList<ImageDBModel> getBackdrops() {
        return backdrops;
    }
    public void setBackdrops(RealmList<ImageDBModel> backdrops) {
        this.backdrops = backdrops;
    }

    public void addVideos(final List<VideoDBModel> videos) {
        this.videosList.addAll(videos);
    }
    public RealmList<VideoDBModel> getVideos() {
        return videosList;
    }
    public void setVideos(RealmList<VideoDBModel> videos) {
        this.videosList = videos;
    }
}

