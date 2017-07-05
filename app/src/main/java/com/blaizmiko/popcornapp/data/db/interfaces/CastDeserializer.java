package com.blaizmiko.popcornapp.data.db.interfaces;

import com.blaizmiko.popcornapp.data.db.models.cast.Actor;
import com.blaizmiko.popcornapp.data.db.models.cast.Cast;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class CastDeserializer implements JsonDeserializer<Cast> {
    @Override
    public Cast deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        final Gson gson = new Gson();
        final JsonObject castJsonObject = json.getAsJsonObject();

        final Cast cast = gson.fromJson(castJsonObject, Cast.class);
        final Actor actor = gson.fromJson(castJsonObject, Actor.class);

        cast.setActor(actor);
        return cast;
    }
}
