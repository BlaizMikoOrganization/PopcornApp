package com.blaizmiko.popcornapp.application;

import com.blaizmiko.popcornapp.common.utils.SymbolUtil;

public class Constants {

    public interface MovieDbApi {
        String API_KEY = "92e0a05bf5b6e05f60a73954b743558f";

        String BASE_MOVIE_DB_URL = "https://api.themoviedb.org/3/";
        String BASE_PROFILE_IMAGE_URL = "https://image.tmdb.org/t/p/w185";
        String BASE_HIGH_RES_IMAGE_URL = "https://image.tmdb.org/t/p/w780";
        String BASE_LOW_RES_IMAGE_URL = "https://image.tmdb.org/t/p/w185";

        String DEFAULT_LANGUAGE = "en_US";

        String CreditsAppendToResponse = "credits";
        String ImagesAppendToResponse = "images";
        String VideoAppendToResponse = "videos";
        String IncludeImageLanguage = "en,null";

        String ExternalIdsAppendToResponse = "external_ids";

        String MovieDetailsAppendToResponse = ImagesAppendToResponse + SymbolUtil.COMMA + VideoAppendToResponse + SymbolUtil.COMMA + CreditsAppendToResponse;
        String TvShowDetailsAppendToResponse = MovieDetailsAppendToResponse + SymbolUtil.COMMA + ExternalIdsAppendToResponse;

        //TestValues
        String NowMovieDefaultRegion = "US";
        int FirstPage = 1;
    }

    public interface YouTubeApi {
        String BASE_TRAILER_URL = "https://www.youtube.com/watch?v=";
        String BASE_TRAILER_PREVIEW_IMAGE_URL = "https://img.youtube.com/vi/";
        String TRAILER_PREVIEW_IMAGE_HIGH_RES = "/2.jpg";
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
    }

}
