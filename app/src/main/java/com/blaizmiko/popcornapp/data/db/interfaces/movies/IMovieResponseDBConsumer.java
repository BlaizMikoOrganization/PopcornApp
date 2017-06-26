package com.blaizmiko.popcornapp.data.db.interfaces.movies;

import com.blaizmiko.popcornapp.data.db.models.movies.MoviesResponseDBModel;

public interface IMovieResponseDBConsumer {
    void transferData(final MoviesResponseDBModel movieResponse);
}
