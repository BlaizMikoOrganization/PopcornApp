package com.blaizmiko.popcornapp.data.models.actors;

import com.blaizmiko.popcornapp.common.utils.SymbolUtil;
import com.blaizmiko.popcornapp.data.models.movies.BriefMovie;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BriefActorModel extends BaseActorModel {

    @SerializedName("known_for")
    private List<BriefMovie> knownMovies;

    public List<BriefMovie> getKnownMovies() {
        return knownMovies;
    }

    public void setKnownMovies(final List<BriefMovie> knownMovies) {
        this.knownMovies = new ArrayList<>(knownMovies);
    }

    public String getKnowMoviesTitlesAsString() {
        final StringBuilder stringBuilder = new StringBuilder();

        if (knownMovies != null) {
            knownMovies.stream()
                    .filter(baseMovie -> baseMovie.getTitle() != null && !baseMovie.getTitle().isEmpty())
                    .forEach(baseMovie -> {
                        stringBuilder.append(baseMovie.getTitle());
                        stringBuilder.append(SymbolUtil.PIPE_WITH_SPACES);
                    });

            if (stringBuilder.length() > SymbolUtil.PIPE_WITH_SPACES.length()) {
                stringBuilder.setLength(stringBuilder.length() - SymbolUtil.PIPE_WITH_SPACES.length());
            }
        }

        return stringBuilder.toString();
    }
}
