package net.mirwaldt.aoc.year2015.day12;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class AbstractJsonNumbersAdder implements JsonNumbersAdder {
    protected long sumRecursive(JsonElement jsonElement) {
        if (jsonElement.isJsonNull()) {
            return 0;
        } else if (jsonElement instanceof JsonPrimitive) {
            JsonPrimitive primitive = (JsonPrimitive) jsonElement;
            if (primitive.isBoolean()) {
                return 0;
            } else if(primitive.isString()) {
                return handleString(primitive.getAsString());
            } else if (primitive.isNumber()) {
                String numberAsString = primitive.getAsString();
                try {
                    return Long.parseLong(numberAsString);
                } catch (NumberFormatException e) {
                    // ignore, we tried it only
                }
                return 0;
            } else {
                throw new IllegalArgumentException("Cannot handle jsonPrimitive '"
                        + primitive + "'!");
            }
        } else if (jsonElement instanceof JsonArray) {
            final JsonArray jsonArray = (JsonArray) jsonElement;
            final Iterator<JsonElement> iterator = jsonArray.iterator();
            return handleArray(iterator);
         } else if (jsonElement instanceof JsonObject) {
            final JsonObject jsonObject = (JsonObject) jsonElement;
            return handleObject(jsonObject.entrySet());
        } else {
            throw new IllegalArgumentException("Cannot handle jsonElement of type '" + jsonElement.getClass() + "'!");
        }
    }

    protected abstract long handleString(String valueAsString);

    protected abstract long handleObject(Set<Map.Entry<String, JsonElement>> entrySet);

    protected abstract long handleArray(Iterator<JsonElement> iterator);

}
