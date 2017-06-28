package com.blaizmiko.popcornapp.data.db;

import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;

import java.util.List;

public interface DataConsumer {
    void consumeMoviesList(final List<DetailedMovieDBModel> movies);
}
