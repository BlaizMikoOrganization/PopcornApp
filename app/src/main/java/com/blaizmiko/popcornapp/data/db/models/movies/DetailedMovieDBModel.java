package com.blaizmiko.popcornapp.data.db.models.movies;

import android.util.Log;

import com.blaizmiko.popcornapp.data.db.interfaces.movies.IDetailedMovie;
import com.blaizmiko.popcornapp.data.db.models.cast.Cast;
import com.blaizmiko.popcornapp.data.db.models.genres.GenreDBModel;
import com.blaizmiko.popcornapp.data.db.models.images.ImageDBModel;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DetailedMovieDBModel extends RealmObject implements IDetailedMovie, TileAdapter.ITileItem{
    @PrimaryKey
    @SerializedName(COLUMN_ID)
    private long id;
    @SerializedName(COLUMN_POSTER_PATH)
    private String posterPath;
    @SerializedName(COLUMN_TITLE)
    private String title;
    @SerializedName(COLUMN_VOTE_AVERAGE)
    private double voteAverage;
    @SerializedName(COLUMN_BACKDROP_PATH)
    private String backdropPath;
    @SerializedName(COLUMN_OVERVIEW)
    private String overview;
    @SerializedName(COLUMN_RELEASE_DATE)
    private String releaseDate;
    @SerializedName(COLUMN_RELEASE_BUDGET)
    private String budget;
    @SerializedName(COLUMN_RELEASE_IMDB_ID)
    private String imdbId;
    @SerializedName(COLUMN_RELEASE_REVENUE)
    private String revenue;
    @SerializedName(COLUMN_RELEASE_RUNTIME)
    private String runtime;

    private RealmList<GenreDBModel> genresList = new RealmList<>();
    private RealmList<VideoDBModel> videosList = new RealmList<>();
    private RealmList<ImageDBModel> posters = new RealmList<>();
    private RealmList<ImageDBModel> backdrops = new RealmList<>();
    private RealmList<DetailedMovieDBModel> similars = new RealmList<>();
    private RealmList<Cast> casts = new RealmList<>();
    private RealmList<ReviewDBModel> reviews = new RealmList<>();

    private String imagePath;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_POSTER_PATH = "poster_path";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_VOTE_AVERAGE = "vote_average";
    public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
    public static final String COLUMN_OVERVIEW = "overview";
    public static final String COLUMN_RELEASE_DATE = "release_date";
    public static final String COLUMN_RELEASE_BUDGET = "budget";
    public static final String COLUMN_RELEASE_IMDB_ID = "imdb_id";
    public static final String COLUMN_RELEASE_REVENUE = "revenue";
    public static final String COLUMN_RELEASE_RUNTIME = "runtime";
    public static final String COLUMN_GENRES = "genresList";
    public static final String COLUMN_VIDEOS = "videosList";
    public static final String COLUMN_POSTERS = "posters";
    public static final String COLUMN_BACKDROPS = "backdrops";
    public static final String COLUMN_SIMILARS = "similars";
    public static final String COLUMN_CAST = "casts";
    public static final String COLUMN_REVIEW = "review";

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

    public String getBudget() {
        return budget;
    }
    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getImdbId() {
        return imdbId;
    }
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getRevenue() {
        return revenue;
    }
    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getRuntime() {
        return runtime;
    }
    public void setRuntime(String runtime) {
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

    public RealmList<Cast> getCasts() {
        return casts;
    }
    public void setCasts(RealmList<Cast> casts) {
        this.casts = casts;
    }

    public RealmList<ReviewDBModel> getReviews() {
        return reviews;
    }
    public void setReviews(RealmList<ReviewDBModel> reviews) {
        this.reviews = reviews;
    }

    public static String getInfoJsonString(final DetailedMovieDBModel movie) {

/*        return "{"
                +movie.getId()

        "{id=297762, posterPath='/imekS7f1OuHyUP2LAiTEM0zBzUz.jpg', title='Wonder Woman'," +
                " voteAverage=7.0, backdropPath='/hA5oCgvgCxj5MEWcLpjXXTwEANF.jpg'," +
                " overview='An Amazon princess comes to the world of Man to become the greatest of the female superheroes.'," +
                " releaseDate='30 мая 2017', budget='120 000 000$', imdbId='tt0451279'," +
                " revenue='713 894 475$', runtime='2 hr 21 min', genresList=GenreDBModel@[0,1,2], " +
                "videosList=VideoDBModel@[0,1,2,3,4,5,6], " +
                "posters=ImageDBModel@[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40], " +
                "backdrops=ImageDBModel@[41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79," +
                "80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98], " +
                "similars=DetailedMovieDBModel@[75,60,76,15,77,78,79,80,81,82,83,70,24,84,85,86,87,88,89,90]," +
                " casts=Cast@[], reviews=ReviewDBModel@[], imagePath='null'}";*/
        final Gson gson = new Gson();
        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(COLUMN_ID, movie.getId());
        jsonObject.addProperty(COLUMN_POSTER_PATH, movie.getPosterPath());
        jsonObject.addProperty(COLUMN_TITLE, movie.getTitle());
        jsonObject.addProperty(COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
        jsonObject.addProperty(COLUMN_BACKDROP_PATH, movie.getBackdropPath());
        jsonObject.addProperty(COLUMN_OVERVIEW, movie.getOverview());
        jsonObject.addProperty(COLUMN_RELEASE_DATE, movie.getReleaseDate());
        jsonObject.addProperty(COLUMN_RELEASE_BUDGET, movie.getBudget());
        jsonObject.addProperty(COLUMN_RELEASE_IMDB_ID, movie.getImdbId());
        jsonObject.addProperty(COLUMN_RELEASE_REVENUE, movie.getRevenue());
        jsonObject.addProperty(COLUMN_RELEASE_RUNTIME, movie.getRuntime());

/*        if (!movie.getGenres().isEmpty()) {
            final JsonElement element = gson.toJsonTree(movie.getGenres(), new TypeToken<List<GenreDBModel>>() {}.getType());
            jsonObject.add(COLUMN_GENRES, element);
        }

        if (!movie.getVideos().isEmpty()) {
            final JsonElement element = gson.toJsonTree(movie.getVideos(), new TypeToken<List<VideoDBModel>>() {}.getType());
            jsonObject.add(COLUMN_VIDEOS, element);
        }

        if (!movie.getPosters().isEmpty()) {
            final JsonElement element = gson.toJsonTree(movie.getVideos(), new TypeToken<List<ImageDBModel>>() {}.getType());
            jsonObject.add(COLUMN_POSTERS, element);
        }

        if (!movie.getBackdrops().isEmpty()) {
            final JsonElement element = gson.toJsonTree(movie.getBackdrops(), new TypeToken<List<ImageDBModel>>() {}.getType());
            jsonObject.add(COLUMN_BACKDROPS, element);
        }

        if (!movie.getSimilars().isEmpty()) {
            final JsonElement element = gson.toJsonTree(movie.getSimilars(), new TypeToken<List<DetailedMovieDBModel>>() {}.getType());
            jsonObject.add(COLUMN_SIMILARS, element);
        }*/
        return jsonObject.toString();
    }


    public static String getCastJsonString(final DetailedMovieDBModel movie) {
        final JsonObject jsonObject = new JsonObject();
        final Gson gson = new Gson();

        //final String open = "{\"casts\":["

        if (!movie.getCasts().isEmpty()) {
            final JsonArray element = gson.toJsonTree(movie.getCasts(), new TypeToken<List<Cast>>() {}.getType()).getAsJsonArray();

            jsonObject.add(COLUMN_CAST, element);
        }
        return jsonObject.toString();
    }

    public static String getReviewsJsonString(final DetailedMovieDBModel movie) {
        Log.d("ololo", "check this");
        final JsonObject jsonObject = new JsonObject();
        final Gson gson = new Gson();

        if (!movie.getReviews().isEmpty()) {
            final JsonArray element = gson.toJsonTree(movie.getReviews(), new TypeToken<List<ReviewDBModel>>() {}.getType()).getAsJsonArray();
            jsonObject.add(COLUMN_REVIEW, element);
        }
        return jsonObject.toString();
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", posterPath='" + posterPath + '\'' +
                ", title='" + title + '\'' +
                ", voteAverage=" + voteAverage +
                ", backdropPath='" + backdropPath + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", budget='" + budget + '\'' +
                ", imdbId='" + imdbId + '\'' +
                ", revenue='" + revenue + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genresList=" + genresList +
                ", videosList=" + videosList +
                ", posters=" + posters +
                ", backdrops=" + backdrops +
                ", similars=" + similars +
                ", casts=" + casts +
                ", reviews=" + reviews +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }


    private String toJSON() {

        return "{" +
                "\"id\":" + id +
                ", posterPath='" + posterPath + '\'' +
                ", title='" + title + '\'' +
                ", voteAverage=" + voteAverage +
                ", backdropPath='" + backdropPath + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", budget='" + budget + '\'' +
                ", imdbId='" + imdbId + '\'' +
                ", revenue='" + revenue + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genresList=" + genresList +
                ", videosList=" + videosList +
                ", posters=" + posters +
                ", backdrops=" + backdrops +
                ", similars=" + similars +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }


}

