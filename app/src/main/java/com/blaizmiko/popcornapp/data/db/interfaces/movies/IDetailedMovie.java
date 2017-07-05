package com.blaizmiko.popcornapp.data.db.interfaces.movies;

import com.blaizmiko.popcornapp.data.db.models.genres.GenreDBModel;
import com.blaizmiko.popcornapp.data.db.models.images.ImageDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.VideoDBModel;

import io.realm.RealmList;

public interface IDetailedMovie extends IBaseMovie{
    String getBudget();
    void setBudget(String budget);

    String getImdbId();
    void setImdbId(String imdbId);

    String getRevenue();
    void setRevenue(String revenue);

    String getRuntime();
    void setRuntime(String runtime);

    RealmList<ImageDBModel> getPosters();
    void setPosters(RealmList<ImageDBModel> posters);

    RealmList<ImageDBModel> getBackdrops();
    void setBackdrops(RealmList<ImageDBModel> backdrops);

    RealmList<VideoDBModel> getVideos();
    void setVideos(RealmList<VideoDBModel> videos);

    RealmList<GenreDBModel> getGenres();
    void setGenres(RealmList<GenreDBModel> genres);
}
