package net.mirwaldt;

import com.google.gson.*;

import java.util.Iterator;
import java.util.Map;

public class IgnoringRedValueJsonNumbersAdder implements JsonNumbersAdder {
    private final long RED_VALUE_INDICATOR = Long.MAX_VALUE;

    @Override
    public long sumJsonNumbers(String json) {
        return sumRecursive(JsonParser.parseString(json));
    }

    private long sumRecursive(JsonElement jsonElement) {
        if (jsonElement.isJsonNull()) {
            return 0;
        } else if (jsonElement instanceof JsonPrimitive) {
            JsonPrimitive primitive = (JsonPrimitive) jsonElement;
            if (primitive.isBoolean()) {
                return 0;
            } else if(primitive.isString()) {
                if("red".equals(primitive.getAsString())) {
                    return RED_VALUE_INDICATOR;
                } else {
                    return 0;
                }
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
                long value = sumRecursive(iterator.next());
                if(value == RED_VALUE_INDICATOR) {
                    value = 0;
                }
                sum += value;
            }
            return sum;
        } else if (jsonElement instanceof JsonObject) {
            final JsonObject jsonObject = (JsonObject) jsonElement;
            long sum = 0;
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                long value = sumRecursive(entry.getValue());
                if(value == RED_VALUE_INDICATOR) {
                    return 0;
                } else {
                    sum += value;
                }
            }
            return sum;
        } else {
            throw new IllegalArgumentException("Cannot handle jsonElement of type '" + jsonElement.getClass() + "'!");
        }
    }
}
