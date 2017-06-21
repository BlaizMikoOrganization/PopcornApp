package com.blaizmiko.popcornapp.data.db;

import android.content.Context;
import android.util.Log;

import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.GenreDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.ImageDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.MoviesResponseDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.VideoDBModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;

public class Database {
    private Realm realm;

    public Database(final Context context) {
        Realm.init(context);
        Log.d("context", "" + context);
        // Get a Realm instance for this thread
        realm = Realm.getDefaultInstance();
    }


    public void putDetailedMovies(final List<DetailedMovieDBModel> detailedMovies) {
    }

    public void putImageDBModel(final ImageDBModel imageDBModel) {
    }

    public void putNowPlayingMovies(final MoviesResponseDBModel moviesResponse) {
    }

    public void putImageDBModels(final List<ImageDBModel> imageList) {
    }

    public void putVideoDBModels(final List<VideoDBModel> videoList) {
    }

    public void putGenreDBModels(final List<GenreDBModel> genreList) {
    }

    //new
    public void putDetailedMovie(final DetailedMovieDBModel detailedMovie) {
        Log.d("genre 1", ""+detailedMovie.getGenres().get(0).getId());
        Log.d("genre 2", ""+detailedMovie.getGenres().get(1).getId());
        Log.d("detailedMovie id", ""+detailedMovie.getId());

        RealmResults<DetailedMovieDBModel> result2 = realm.where(DetailedMovieDBModel.class)
                .findAll();

        for (DetailedMovieDBModel movie : result2) {
            Log.d("movie ", ""+movie.getTitle());
        }


        realm.beginTransaction();
        realm.copyToRealmOrUpdate(detailedMovie);
        realm.commitTransaction();
        RealmResults<DetailedMovieDBModel> result3 = realm.where(DetailedMovieDBModel.class)
                .findAll();

        for (DetailedMovieDBModel movie : result3) {
            Log.d("movie ", ""+movie.getTitle() +"" +movie.getGenres().size());
        }
    }

    public void subscribeToRestoreDetailedMovie(final DBUpdateNowPlayingMovies view) {
    }

    public interface DBUpdateNowPlayingMovies {
        void updateNowPlayingMovies(final List<DetailedMovieDBModel> nowPlayingMoviesList);
    }
}
