package net.mirwaldt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartTwoJsonNumbersAdderTest {
    private final static JsonNumbersAdder jsonNumbersAdder = new IgnoringRedValueJsonNumbersAdder();

    private static Stream<Arguments> jsonNumbersAdder() {
        return Stream.of(Arguments.of(jsonNumbersAdder));
    }

    @ParameterizedTest
    @MethodSource("jsonNumbersAdder")
    void test_jsonWithRedElements(JsonNumbersAdder jsonNumbersAdder) {
        assertEquals(4, jsonNumbersAdder.sumJsonNumbers("[1,{\"c\":\"red\",\"b\":2},3]"));
        assertEquals(0, jsonNumbersAdder.sumJsonNumbers("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}"));
        assertEquals(6, jsonNumbersAdder.sumJsonNumbers("[1,\"red\",5]"));
    }
}
