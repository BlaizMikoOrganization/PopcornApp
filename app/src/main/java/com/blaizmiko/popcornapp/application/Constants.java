package com.blaizmiko.popcornapp.application;

import com.blaizmiko.popcornapp.common.utils.SymbolUtil;

public class Constants {

    public interface MovieDbApi {
        String API_KEY = "92e0a05bf5b6e05f60a73954b743558f";

        int DEFAULT_CINEMA_ID = 1;
        int DEFAULT_SEASON_NUMBER = 1;

        String BASE_MOVIE_DB_URL = "https://api.themoviedb.org/3/";
        String BASE_PROFILE_IMAGE_URL = "https://image.tmdb.org/t/p/w185";
        String BASE_HIGH_RES_IMAGE_URL = "https://image.tmdb.org/t/p/w780";
        String BASE_LOW_RES_IMAGE_URL = "https://image.tmdb.org/t/p/w185";

        String DEFAULT_LANGUAGE = "en_US";

        String ImagesAppendToResponse = "images";
        String VideoAppendToResponse = "videos";
        String SimilarMoviesAppendToResponse = "similar";
        String IncludeImageLanguage = "en,null";
        String ExternalIdsAppendToResponse = "external_ids";

        String InfoDetailsBaseAppendToResponse = ImagesAppendToResponse + SymbolUtil.COMMA
                + VideoAppendToResponse + SymbolUtil.COMMA
                + SimilarMoviesAppendToResponse + SymbolUtil.COMMA;

        String InfoDetailsMovieAppendToResponse = InfoDetailsBaseAppendToResponse;

        String InfoDetailsTvShowAppendToResponse = InfoDetailsBaseAppendToResponse + SymbolUtil.COMMA
                + ExternalIdsAppendToResponse;

        //TestValues
        String NowMovieDefaultRegion = "US";
        int FirstPage = 1;
    }

    public interface YouTubeApi {
        String API_KEY = "AIzaSyCL2R4fZwhtKSy3aDMELPDP2SvlSHe2Kog";
        String BASE_TRAILER_PREVIEW_IMAGE_URL = "http://img.youtube.com/vi/";
        String TRAILER_PREVIEW_IMAGE_HIGH_RES = "/mqdefault.jpg";
    }

    public interface OMDbApi {
        String BASE_OMDB_URL = "http://www.omdbapi.com/";
        String INCLUDE_TOMATOES_RATING = "true";
    }

    public interface NetworkingConfig {
        int TIMEOUT = 20;
    }

    public interface Extras {
        String ID = "id";
        String TITLE = "title";
        String RATING = "rating";
        String BACKDROP_URL = "backdrop_url";
        String POSTER_URL = "poster_url";
        String URLS_ARRAY = "image_list";
        String POSITION = "position";
        String RELEASE_DATE = "release_date";
        String VIDEO_URL = "video_url";
        String SEASON_NUMBER = "season_number";
        String AUTHOR = "author";
        String REVIEW = "review";
    }
}
