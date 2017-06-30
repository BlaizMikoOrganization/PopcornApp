package com.blaizmiko.popcornapp.data.db.models.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MoviesResponseDBModel extends RealmObject{
    @PrimaryKey
    private long id;
    @SerializedName("results")
    private RealmList<DetailedMovieDBModel> movies;

    public static final String COLUMN_ID = "id";

    public MoviesResponseDBModel() {
        this.id = 0;
        this.movies = new RealmList<>();
    }
    public MoviesResponseDBModel(final long id) {
        this.id = id;
        this.movies = new RealmList<>();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public List<DetailedMovieDBModel> getMovies() {
        return movies;
    }
    public void setMovies(RealmList<DetailedMovieDBModel> detailedMovies) {
        this.movies = detailedMovies;
    }

    @Override
    public String toString() {
        String moviesString = "";
        for (DetailedMovieDBModel movie : movies) {
            moviesString +=movie +", \n";
        }
        return "MoviesResponseDBModel{" +
                "id=" + id +
                ", movies=" + moviesString +
                '}';
    }
}


