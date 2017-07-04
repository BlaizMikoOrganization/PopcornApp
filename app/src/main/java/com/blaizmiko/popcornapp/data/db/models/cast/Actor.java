package com.blaizmiko.popcornapp.data.db.models.cast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Actor extends RealmObject{

    @PrimaryKey
    @SerializedName("id")
    private long id;
    @SerializedName("profile_path")
    private String profileImageUrl;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("name")
    private String name;
    @SerializedName("popularity")
    private double popularity;
    @SerializedName("biography")
    private String biography;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("deathday")
    private String deathday;
    @SerializedName("gender")
    private int gender;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("place_of_birth")
    private String placeOfBirth;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("also_known_as")
    private RealmList<KnownAs> alsoKnownAs;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public boolean isAdult() {
        return adult;
    }
    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPopularity() {
        return popularity;
    }
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getBiography() {
        return biography;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeathday() {
        return deathday;
    }
    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getImdbId() {
        return imdbId;
    }
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }
    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getHomepage() {
        return homepage;
    }
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public List<KnownAs> getAlsoKnownAs() {
        return alsoKnownAs;
    }
    public void setAlsoKnownAs(RealmList<KnownAs> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }
}
