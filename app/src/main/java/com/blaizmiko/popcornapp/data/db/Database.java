package com.blaizmiko.popcornapp.data.db;

import android.content.Context;
import android.util.Log;

import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.GenreDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.ImageDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.MoviesResponseDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.MyObjectBox;
import com.blaizmiko.popcornapp.data.db.models.movies.VideoDBModel;
import com.blaizmiko.popcornapp.data.models.movies.BaseMovieListResponse;

import java.util.ArrayList;
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

/*    public void putVideos(final List<VideoDBModel> videos) {
        final List<VideoDBModel> uniqVideos = new ArrayList<>();
    }*/

    public void putDetailedMovies(final List<DetailedMovieDBModel> detailedMovies) {
        final Box detailedMovieDBModelBox = boxStore.boxFor(DetailedMovieDBModel.class);
        detailedMovieDBModelBox.put(detailedMovies);
    }

    public void putImageDBModel(final ImageDBModel imageDBModel) {
        Box imageBox = boxStore.boxFor(ImageDBModel.class);
        imageBox.put(imageDBModel);
    }

    public void putNowPlayingMovies(final MoviesResponseDBModel moviesResponse){
        Box movieResponseBox = boxStore.boxFor(MoviesResponseDBModel.class);
        movieResponseBox.put(moviesResponse);
    }

    public void putImageDBModels(final List<ImageDBModel> imageList) {
        Box imageBox = boxStore.boxFor(ImageDBModel.class);
        imageBox.put(imageList);
    }

    public void putVideoDBModels(final List<VideoDBModel> videoList) {
        final Box videoBox = boxStore.boxFor(VideoDBModel.class);
        videoBox.put(videoList);
    }

    public void putGenreDBModels(final List<GenreDBModel> genreList){
        final Box genreBox = boxStore.boxFor(GenreDBModel.class);
        genreBox.put(genreList);
    }


    public void putDetailedMovie(final DetailedMovieDBModel detailedMovie) {
        final Box detailedMovieDBModelBox = boxStore.boxFor(DetailedMovieDBModel.class);
        //final Box imagesBox = boxStore.boxFor(ImageDBModel.class);
        //final Box videoBox = boxStore.boxFor(VideoDBModel.class);
        //final Box genreBox = boxStore.boxFor(GenreDBModel.class);


        //imagesBox.put(detailedMovie.getPosters());
        //imagesBox.put(detailedMovie.getBackdrops());
        //videoBox.put(detailedMovie.getVideos());
        //genreBox.put(detailedMovie.getGenres());
        detailedMovieDBModelBox.put(detailedMovie);
    }

    public void subscribeToRestoreDetailedMovie(final DBUpdateNowPlayingMovies view) {
        final Box detailedMovieDBModelBox = boxStore.boxFor(DetailedMovieDBModel.class);
    }

    public Box getBoxForDetailedMovies() {
        return boxStore.boxFor(DetailedMovieDBModel.class);
    }

    public Box getBoxForVideos() {
        return boxStore.boxFor(VideoDBModel.class);
    }

    public Box getBoxForImages() {
        return boxStore.boxFor(ImageDBModel.class);
    }

    public Box getBoxForGenres() {
        return boxStore.boxFor(GenreDBModel.class);
    }

    public Box getBoxForMoviesResponse() {
        return boxStore.boxFor(MoviesResponseDBModel.class);
    }

    public interface DBUpdateNowPlayingMovies {
        void updateNowPlayingMovies(final List<DetailedMovieDBModel> nowPlayingMoviesList);
    }
}
