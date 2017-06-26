package com.blaizmiko.popcornapp.data.db.interfaces.movies;

import com.blaizmiko.popcornapp.data.db.models.movies.MoviesResponseDBModel;

public interface IDBConsumer {
    void transferData(final MoviesResponseDBModel movieResponse);
}
