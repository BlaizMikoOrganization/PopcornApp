package com.blaizmiko.popcornapp.data.db.interfaces.movies;

import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;

public interface IDetailedMovieDBConsumer {
    void transferData(final DetailedMovieDBModel detailedMovieDBModel);
}
