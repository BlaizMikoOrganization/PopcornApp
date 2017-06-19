package com.blaizmiko.popcornapp.data.db.interfaces.movies;

import com.blaizmiko.popcornapp.data.db.interfaces.cinema.IBaseCinema;
import com.blaizmiko.popcornapp.data.db.models.movies.GenreDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.ImageDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.VideoDBModel;
import com.blaizmiko.popcornapp.data.models.images.ImagesResponse;
import com.blaizmiko.popcornapp.data.models.movies.BaseMovieListResponse;
import com.blaizmiko.popcornapp.data.models.videos.VideosResponse;
import java.util.List;

public interface IDetailedMovie extends IBaseCinema{
    int getBudget();
    void setBudget(int budget);

    String getImdbId();
    void setImdbId(String imdbId);

    int getRevenue();
    void setRevenue(int revenue);

    int getRuntime();
    void setRuntime(int runtime);

    List<ImageDBModel> getPosters();
    void setPosters(List<ImageDBModel> posters);

    List<ImageDBModel> getBackdrops();
    void setBackdrops(List<ImageDBModel> backdrops);

    List<VideoDBModel> getVideos();
    void setVideos(List<VideoDBModel> videos);

/*    BaseMovieListResponse getSimilarMovies();
    void setSimilarMovies(BaseMovieListResponse similarMovies);*/

    List<GenreDBModel> getGenres();
    void setGenres(List<GenreDBModel> genres);
}
