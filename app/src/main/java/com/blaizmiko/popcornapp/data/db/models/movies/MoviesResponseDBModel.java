package com.blaizmiko.popcornapp.data.db.models.movies;

import java.util.List;

public class MoviesResponseDBModel {
    private long id;
    private List<DetailedMovieDBModel> movies;
    public MoviesResponseDBModel(long id) {
        this.id = id;
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
    public void setMovies(List<DetailedMovieDBModel> detailedMovies) {
        this.movies = detailedMovies;
    }
}


