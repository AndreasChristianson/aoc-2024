package com.pessimistic.aoc2024;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TextUtils {
    private TextUtils() {
    }

    public static Stream<String> splitOnWhitespace(String input) {
        return Arrays.stream(input.trim().split("\\s+"));
    }

    public static IntStream toIntStream(String input) {
        return splitOnWhitespace(input).mapToInt(Integer::parseInt);
    }
}
