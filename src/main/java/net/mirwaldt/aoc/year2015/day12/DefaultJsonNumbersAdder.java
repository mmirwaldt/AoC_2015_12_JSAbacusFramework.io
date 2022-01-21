package net.mirwaldt.aoc.year2015.day12;

import com.google.gson.*;

import java.util.*;

public class DefaultJsonNumbersAdder extends AbstractJsonNumbersAdder {
    @Override
    public long sumJsonNumbers(String json) {
        return sumRecursive(JsonParser.parseString(json));
    }

    @Override
    protected long handleString(String asString) {
        return 0;
    }

    @Override
    protected long handleArray(Iterator<JsonElement> iterator) {
        long sum = 0;
        while (iterator.hasNext()) {
            sum += sumRecursive(iterator.next());
        }
        return sum;
    }

    @Override
    protected long handleObject(Set<Map.Entry<String, JsonElement>> entrySet) {
        long sum = 0;
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            sum += sumRecursive(entry.getValue());
        }
        return sum;
    }
}
