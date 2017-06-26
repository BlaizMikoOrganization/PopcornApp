package com.blaizmiko.popcornapp.data.db.models.movies;

import com.blaizmiko.popcornapp.data.db.interfaces.movies.IDetailedMovie;
import com.blaizmiko.popcornapp.data.db.models.genres.GenreDBModel;
import com.blaizmiko.popcornapp.data.db.models.images.ImageDBModel;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DetailedMovieDBModel extends RealmObject implements IDetailedMovie, TileAdapter.ITileItem{

    @PrimaryKey
    @SerializedName("id")
    private long id;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("title")
    private String title;
    @SerializedName("vote_average")
    private double voteAverage;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("budget")
    private int budget;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("revenue")
    private int revenue;
    @SerializedName("runtime")
    private int runtime;

    private RealmList<GenreDBModel> genresList = new RealmList<>();
    private RealmList<VideoDBModel> videosList = new RealmList<>();
    private RealmList<ImageDBModel> posters = new RealmList<>();
    private RealmList<ImageDBModel> backdrops = new RealmList<>();
    private RealmList<DetailedMovieDBModel> similars = new RealmList<>();

    private String imagePath;

    public static final String COLUMN_ID = "id";

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

    public List<DetailedMovieDBModel> getSimilars() {
        return similars;
    }
    public void setSimilarResponse(RealmList<DetailedMovieDBModel> similars) {
        this.similars = similars;
    }

    public void addGenres(final List<GenreDBModel> genres) {
        this.genresList.addAll(genres);
    }
    public RealmList<GenreDBModel> getGenres() {
        return genresList;
    }
    public void setGenres(RealmList<GenreDBModel> genres) {
        this.genresList = genres;
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

    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "DetailedMovieDBModel{" +
                "id=" + id +
                ", posterPath='" + posterPath + '\'' +
                ", title='" + title + '\'' +
                ", voteAverage=" + voteAverage +
                ", backdropPath='" + backdropPath + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", budget=" + budget +
                ", imdbId='" + imdbId + '\'' +
                ", revenue=" + revenue +
                ", runtime=" + runtime +
                ", genresList=" + genresList +
                ", videosList=" + videosList +
                ", posters=" + posters +
                ", backdrops=" + backdrops +
                ", similarResponse=" + similars +
                '}';
    }
}

