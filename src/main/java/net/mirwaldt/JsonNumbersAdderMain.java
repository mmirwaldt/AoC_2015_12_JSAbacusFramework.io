package net.mirwaldt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonNumbersAdderMain {
    public static void main(String[] args) throws IOException {
        final String json = Files.readString(Path.of("input.txt"), StandardCharsets.US_ASCII);

        final JsonNumbersAdder partOneJsonNumbersAdder = new DefaultJsonNumbersAdder();
        System.out.println(partOneJsonNumbersAdder.sumJsonNumbers(json)); // result: 191164

        final JsonNumbersAdder partTwoJsonNumbersAdder = new IgnoringRedValueJsonNumbersAdder();
        System.out.println(partTwoJsonNumbersAdder.sumJsonNumbers(json)); // result: 87842
    }
}
