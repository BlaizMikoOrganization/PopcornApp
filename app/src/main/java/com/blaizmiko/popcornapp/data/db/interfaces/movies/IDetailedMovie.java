package com.blaizmiko.popcornapp.data.db.interfaces.movies;

import com.blaizmiko.popcornapp.data.db.interfaces.cinema.IBaseCinema;
import com.blaizmiko.popcornapp.data.db.models.movies.GenreDBModel;
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

    ImagesResponse getImages();
    void setImages(ImagesResponse images);

    VideosResponse getVideos();
    void setVideos(VideosResponse videos);

    BaseMovieListResponse getSimilarMovies();
    void setSimilarMovies(BaseMovieListResponse similarMovies);

    List<GenreDBModel> getGenres();
    void setGenres(List<GenreDBModel> genres);
}
