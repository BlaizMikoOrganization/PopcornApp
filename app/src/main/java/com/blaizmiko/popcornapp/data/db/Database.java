package com.blaizmiko.popcornapp.data.db;

import android.content.Context;

import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.MyObjectBox;
import java.util.List;
import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.query.Query;

public class Database {
    @Inject
    Context context;

    private static BoxStore boxStore;

    public Database(final Context context) {
        this.context = context;
        boxStore = MyObjectBox.builder().androidContext(context).build();
    }

    public void putDetailedMovies(final List<DetailedMovieDBModel> detailedMovies) {
        final Box detailedMovieDBModelBox = boxStore.boxFor(DetailedMovieDBModel.class);
        detailedMovieDBModelBox.put(detailedMovies);
    }

    public void subscribeToUpdateDetailedMovie(final UpdatableView view) {
        final Box detailedMovieDBModelBox = boxStore.boxFor(DetailedMovieDBModel.class);
        final Query<DetailedMovieDBModel> query = detailedMovieDBModelBox.query().build();
        query.subscribe().on(AndroidScheduler.mainThread()).observer(data -> {
            view.update(data);
        });
    }

    public interface UpdatableView {
        void update (final List<?> dataList);
    }
}
