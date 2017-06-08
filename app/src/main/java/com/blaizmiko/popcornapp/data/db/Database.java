package com.blaizmiko.popcornapp.data.db;

import android.content.Context;
import android.util.Log;

import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.MyObjectBox;
import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.QueryBuilder;

public class Database {
    @Inject
    Context context;

    private static BoxStore boxStore;

    public Database(final Context context) {
        this.context = context;
        boxStore = MyObjectBox.builder().androidContext(context).build();
    }

    public void putDetailedMovies(final List<DetailedMovieDBModel> detailedMovie) {
        final Box detailedMovieDBModelBox = boxStore.boxFor(DetailedMovieDBModel.class);
        detailedMovieDBModelBox.put(detailedMovie);
    }

    public void printAllDetailedMovies() {
        final Box detailedMovieDBModelBox = boxStore.boxFor(DetailedMovieDBModel.class);
        final QueryBuilder<DetailedMovieDBModel> builder = detailedMovieDBModelBox.query();
        final List<DetailedMovieDBModel> detailedMovies = builder.build().find();
        for (DetailedMovieDBModel model : detailedMovies) {
            Log.d("detailedMovie", ""+model.title);
        }
    }
}
