package com.blaizmiko.popcornapp.data.db.models.movies;

import com.blaizmiko.popcornapp.data.db.interfaces.cinema.IBaseCinema;
import com.blaizmiko.popcornapp.data.db.interfaces.movies.IDetailedMovie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class MoviesResponseDBModel extends RealmObject{
    private long id;
    @SerializedName("results")
    private RealmList<DetailedMovieDBModel> movies;

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


