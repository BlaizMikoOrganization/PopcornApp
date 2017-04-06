package com.blaizmiko.popcornapp.data.models.cinema;

public interface Cinema {
    int getId();
    void setId(final int id);

    String getPosterPath();
    void setPosterPath(final String posterPath);

    String getTitle();
    void setTitle(final String title);

    double getVoteAverage();
    void setVoteAverage(final double voteAverage);

    String getBackdropPath();
    void setBackdropPath(final String backdropPath);
}
