package com.blaizmiko.popcornapp.data.db.models.cast;

import io.realm.RealmObject;

public class KnownAs extends RealmObject {
    private String knownAs;

    public String getKnownAs() {
        return knownAs;
    }
    public void setKnownAs(String knownAs) {
        this.knownAs = knownAs;
    }
}
