package com.blaizmiko.popcornapp.data.models.actors;

import com.blaizmiko.popcornapp.common.utils.SymbolUtil;
import com.blaizmiko.popcornapp.data.models.movies.BaseMovie;
import com.blaizmiko.popcornapp.data.models.movies.BriefMovie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseActor {

    @SerializedName("profile_path")
    private String profileImageUrl;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("name")
    private String name;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("known_for")
    private List<BriefMovie> knownMovies;

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(final String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(final boolean adult) {
        this.adult = adult;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(final double popularity) {
        this.popularity = popularity;
    }

    public List<BriefMovie> getKnownMovies() {
        return knownMovies;
    }

    public void setKnownMovies(final List<BriefMovie> knownMovies) {
        this.knownMovies = knownMovies;
    }

    public String getKnowMoviesTitlesAsString() {
        final StringBuilder stringBuilder = new StringBuilder();

        if(knownMovies != null) {
            for(final BaseMovie baseMovie : knownMovies) {
                if(baseMovie.getTitle() != null && !baseMovie.getTitle().isEmpty()) {
                    stringBuilder.append(baseMovie.getTitle());
                    stringBuilder.append(SymbolUtil.PIPE_WITH_SPACES);
                }
            }

            if(stringBuilder.length() > SymbolUtil.PIPE_WITH_SPACES.length()) {
                stringBuilder.setLength(stringBuilder.length() - SymbolUtil.PIPE_WITH_SPACES.length());
            }
        }

        return stringBuilder.toString();
    }
}
