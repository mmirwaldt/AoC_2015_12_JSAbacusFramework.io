package net.mirwaldt.aoc.year2015.day12;

import com.google.gson.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IgnoringRedValueJsonNumbersAdder extends AbstractJsonNumbersAdder {
    private final long RED_VALUE_INDICATOR = Long.MAX_VALUE;

    @Override
    public long sumJsonNumbers(String json) {
        return sumRecursive(JsonParser.parseString(json));
    }

    @Override
    protected long handleString(String valueAsString) {
        if("red".equals(valueAsString)) {
            return RED_VALUE_INDICATOR;
        } else {
            return 0;
        }
    }

    @Override
    protected long handleArray(Iterator<JsonElement> iterator) {
        long sum = 0;
        while (iterator.hasNext()) {
            long value = sumRecursive(iterator.next());
            if(value == RED_VALUE_INDICATOR) {
                value = 0;
            }
            sum += value;
        }
        return sum;
    }

    @Override
    protected long handleObject(Set<Map.Entry<String, JsonElement>> entrySet) {
        long sum = 0;
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            long value = sumRecursive(entry.getValue());
            if(value == RED_VALUE_INDICATOR) {
                return 0;
            } else {
                sum += value;
            }
        }
        return sum;
    }
}
