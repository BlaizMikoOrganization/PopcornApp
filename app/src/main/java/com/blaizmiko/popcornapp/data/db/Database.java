package com.blaizmiko.popcornapp.data.db;

import android.content.Context;
import android.util.Log;

import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.MyObjectBox;

import java.util.List;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.query.Query;

public class Database {
    private BoxStore boxStore;

    public Database(final Context context) {
        boxStore = MyObjectBox.builder().androidContext(context).build();
    }

    public void putDetailedMovies(final List<DetailedMovieDBModel> detailedMovies) {
        final Box detailedMovieDBModelBox = boxStore.boxFor(DetailedMovieDBModel.class);
        detailedMovieDBModelBox.put(detailedMovies);
    }

    public void putDetailedMovie(final DetailedMovieDBModel detailedMovie) {
        final Box detailedMovieDBModelBox = boxStore.boxFor(DetailedMovieDBModel.class);
        detailedMovieDBModelBox.put(detailedMovie);
    }

    public void subscribeToRestoreDetailedMovie(final DBUpdateNowPlayingMovies view) {
        final Box detailedMovieDBModelBox = boxStore.boxFor(DetailedMovieDBModel.class);

    }

    public Box getBoxForDetailedMovies() {
        return boxStore.boxFor(DetailedMovieDBModel.class);
    }

    public interface DBUpdateNowPlayingMovies {
        void updateNowPlayingMovies(final List<DetailedMovieDBModel> nowPlayingMoviesList);
    }
}
