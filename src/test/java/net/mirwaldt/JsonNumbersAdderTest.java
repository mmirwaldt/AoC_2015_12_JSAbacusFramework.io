package net.mirwaldt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonNumbersAdderTest {
    private final static JsonNumbersAdder defaultJsonNumbersAdder = new DefaultJsonNumbersAdder();

    private static Stream<Arguments> jsonNumbersAdder() {
        return Stream.of(Arguments.of(defaultJsonNumbersAdder));
    }

    @ParameterizedTest
    @MethodSource("jsonNumbersAdder")
    void test_emptyJson(JsonNumbersAdder jsonNumbersAdder) {
        assertEquals(0, jsonNumbersAdder.sumJsonNumbers("{}"));
        assertEquals(0, jsonNumbersAdder.sumJsonNumbers("[]"));
    }

    @ParameterizedTest
    @MethodSource("jsonNumbersAdder")
    void test_noNumbersInJson(JsonNumbersAdder jsonNumbersAdder) {
        assertEquals(0, jsonNumbersAdder.sumJsonNumbers("{ \"a\" : \"b\"}"));
        assertEquals(0, jsonNumbersAdder.sumJsonNumbers("[\"a\"]"));
    }
}
