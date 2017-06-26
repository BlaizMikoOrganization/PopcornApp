package com.blaizmiko.popcornapp.data.db.interfaces.movies;

import com.blaizmiko.popcornapp.data.db.models.genres.GenreDBModel;
import com.blaizmiko.popcornapp.data.db.models.images.ImageDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.VideoDBModel;

import io.realm.RealmList;

public interface IDetailedMovie extends IBaseMovie{
    int getBudget();
    void setBudget(int budget);

    String getImdbId();
    void setImdbId(String imdbId);

    int getRevenue();
    void setRevenue(int revenue);

    int getRuntime();
    void setRuntime(int runtime);

    RealmList<ImageDBModel> getPosters();
    void setPosters(RealmList<ImageDBModel> posters);

    RealmList<ImageDBModel> getBackdrops();
    void setBackdrops(RealmList<ImageDBModel> backdrops);

    RealmList<VideoDBModel> getVideos();
    void setVideos(RealmList<VideoDBModel> videos);

    RealmList<GenreDBModel> getGenres();
    void setGenres(RealmList<GenreDBModel> genres);
}
