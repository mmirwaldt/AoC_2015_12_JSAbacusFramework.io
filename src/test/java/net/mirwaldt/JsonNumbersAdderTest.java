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

    @ParameterizedTest
    @MethodSource("jsonNumbersAdder")
    void test_numbersInJson(JsonNumbersAdder jsonNumbersAdder) {
        assertEquals(6, jsonNumbersAdder.sumJsonNumbers("[1,2,3]"));
        assertEquals(6, jsonNumbersAdder.sumJsonNumbers("{\"a\":2,\"b\":4}"));
        assertEquals(3, jsonNumbersAdder.sumJsonNumbers("[[[3]]]"));
        assertEquals(3, jsonNumbersAdder.sumJsonNumbers("{\"a\":{\"b\":4},\"c\":-1}"));
        assertEquals(0, jsonNumbersAdder.sumJsonNumbers("{\"a\":[-1,1]}"));
        assertEquals(0, jsonNumbersAdder.sumJsonNumbers("[-1,{\"a\":1}]"));
    }
}
