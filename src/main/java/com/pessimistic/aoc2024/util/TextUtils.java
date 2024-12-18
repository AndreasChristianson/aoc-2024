package com.pessimistic.aoc2024.util;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class TextUtils {
    private TextUtils() {
    }

    public static Stream<String> splitOnWhitespace(String input) {
        return Arrays.stream(input.trim().split("\\s+"));
    }

    public static LongStream toLongStream(String input) {
        return splitOnWhitespace(input).mapToLong(Long::parseLong);
    }

    public static Stream<Map<String, String>> allMatches(String input, String regex) {
        Matcher matcher = Pattern.compile(regex)
                .matcher(input);
        return matcher.results()
                .map(matchResult -> matchResult.namedGroups()
                        .keySet().stream()
                        .filter(s -> matchResult.group(s) != null)
                        .collect(Collectors.toConcurrentMap(Function.identity(), matcher::group)));
    }

    public static Long matchCount(String input, String regex) {
        Matcher matcher = Pattern.compile(regex)
                .matcher(input);
        return matcher.results().count();
    }
}
