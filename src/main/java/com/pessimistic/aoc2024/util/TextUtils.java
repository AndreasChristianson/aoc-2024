package com.pessimistic.aoc2024.util;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
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

    public static void printGrid(List<List<String>> grid) {
        for (List<String> line : grid) {
            for (var s : line) {
                System.out.print(s);
            }
            System.out.println();
        }
    }
}
