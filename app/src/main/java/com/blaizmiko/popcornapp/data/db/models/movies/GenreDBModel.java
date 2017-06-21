package com.blaizmiko.popcornapp.data.db.models.movies;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class GenreDBModel extends RealmObject{
    @PrimaryKey
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
