package com.blaizmiko.popcornapp.data.db.models.cast;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Cast extends RealmObject{
    @PrimaryKey
    @SerializedName("id")
    private long id;
    @SerializedName("character")
    private String character;

    private Actor actor;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }
    public void setCharacter(String character) {
        this.character = character;
    }

    public Actor getActor() {
        return actor;
    }
    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
