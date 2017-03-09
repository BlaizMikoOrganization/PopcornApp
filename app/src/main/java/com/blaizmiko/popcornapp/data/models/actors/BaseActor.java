package com.blaizmiko.popcornapp.data.models.actors;

import com.blaizmiko.popcornapp.common.utils.SymbolUtil;
import com.blaizmiko.popcornapp.data.models.movies.BaseMovie;
import com.blaizmiko.popcornapp.data.models.movies.BriefMovie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseActor {

    @SerializedName("profile_path")
    private String mProfileImageUrl;

    @SerializedName("adult")
    private boolean mAdult;

    @SerializedName("name")
    private String mName;

    @SerializedName("popularity")
    private double mPopularity;

    @SerializedName("known_for")
    private List<BriefMovie> mKnownMovies;

    public String getProfileImageUrl() {
        return mProfileImageUrl;
    }

    public void setProfileImageUrl(final String profileImageUrl) {
        mProfileImageUrl = profileImageUrl;
    }

    public boolean isAdult() {
        return mAdult;
    }

    public void setAdult(final boolean adult) {
        mAdult = adult;
    }

    public String getName() {
        return mName;
    }

    public void setName(final String name) {
        mName = name;
    }

    public double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(final double popularity) {
        mPopularity = popularity;
    }

    public List<BriefMovie> getKnownMovies() {
        return mKnownMovies;
    }

    public void setKnownMovies(final List<BriefMovie> knownMovies) {
        mKnownMovies = knownMovies;
    }

    public String getKnowMoviesTitlesAsString() {
        final StringBuilder stringBuilder = new StringBuilder();

        if(mKnownMovies != null) {
            for(final BaseMovie baseMovie : mKnownMovies) {
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
