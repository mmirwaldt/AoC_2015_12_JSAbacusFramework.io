package net.mirwaldt;

import com.google.gson.*;

import java.util.*;

public class DefaultJsonNumbersAdder implements JsonNumbersAdder {
    @Override
    public long sumJsonNumbers(String json) {
        return sumRecursive(JsonParser.parseString(json));
    }

    private long sumRecursive(JsonElement jsonElement) {
        if (jsonElement.isJsonNull()) {
            return 0;
        } else if (jsonElement instanceof JsonPrimitive) {
            JsonPrimitive primitive = (JsonPrimitive) jsonElement;
            if (primitive.isBoolean() || primitive.isString()) {
                return 0;
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
            long sum = 0;
            while (iterator.hasNext()) {
                sum += sumRecursive(iterator.next());
            }
            return sum;
        } else if (jsonElement instanceof JsonObject) {
            final JsonObject jsonObject = (JsonObject) jsonElement;
            long sum = 0;
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                sum += sumRecursive(entry.getValue());
            }
            return sum;
        } else {
            throw new IllegalArgumentException("Cannot handle jsonElement of type '" + jsonElement.getClass() + "'!");
        }
    }
}
