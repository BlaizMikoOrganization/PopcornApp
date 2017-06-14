package com.blaizmiko.popcornapp.data.db.interfaces.movies;

import com.blaizmiko.popcornapp.data.db.interfaces.cinema.IBaseCinema;

public interface IBaseMovie extends IBaseCinema{
    String getOverview();
    void setOverview(String overview);

    String getReleaseDate();
    void setReleaseDate(String releaseDate);
}
