package com.blaizmiko.popcornapp.data.models.actors.popular;

import com.blaizmiko.popcornapp.common.utils.SymbolUtil;
import com.blaizmiko.popcornapp.data.models.actors.detailed.BaseActorModel;
import com.blaizmiko.popcornapp.data.models.movies.BaseMovieModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularActorModel extends BaseActorModel {
    @SerializedName("known_for")
    private List<BaseMovieModel> knownMovies;

    public List<BaseMovieModel> getKnownMovies() {
        return knownMovies;
    }

    public void setKnownMovies(final List<BaseMovieModel> knownMovies) {
        this.knownMovies = knownMovies;
    }

    public String getKnowMoviesTitlesAsString() {
        final StringBuilder stringBuilder = new StringBuilder();

        if(knownMovies != null) {
            for(final BaseMovieModel baseMovie : knownMovies) {
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
