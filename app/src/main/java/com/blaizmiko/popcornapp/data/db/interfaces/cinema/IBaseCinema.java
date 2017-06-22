package com.blaizmiko.popcornapp.data.db.interfaces.cinema;

import io.realm.RealmModel;

public interface IBaseCinema{
    long getId();
    void setId(long id);

    String getPosterPath();
    void setPosterPath(String posterPath);

    String getTitle();
    void setTitle(String title);

    double getVoteAverage();
    void setVoteAverage(double voteAverage);

    String getBackdropPath();
    void setBackdropPath(String backdropPath);
}
