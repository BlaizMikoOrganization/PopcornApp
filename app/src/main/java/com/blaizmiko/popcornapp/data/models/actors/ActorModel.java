package com.blaizmiko.popcornapp.data.models.actors;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mikhail_Kantsavoi on 3/10/2017.
 */

public class ActorModel extends BaseActorModel {

    @SerializedName("imdb_id")
    private String imdbId;

    @SerializedName("biography")
    private String biography;

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("place_of_birth")
    private String placeOfBirth;

    @SerializedName("homepage")
    private String homePageUrl;

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(final String imdbId) {
        this.imdbId = imdbId;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(final String biography) {
        this.biography = biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(final String birthday) {
        this.birthday = birthday;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(final String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getHomePageUrl() {
        return homePageUrl;
    }

    public void setHomePageUrl(final String homePageUrl) {
        this.homePageUrl = homePageUrl;
    }
}
