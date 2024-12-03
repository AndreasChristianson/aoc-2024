package com.pessimistic.aoc2024;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public static List<Map<String, String>> allMatches(String input, String regex, List<String> groupsToExtract) {
        var allMatches = new ArrayList<Map<String, String>>();
        Matcher matcher = Pattern.compile(regex)
                .matcher(input);
        while (matcher.find()) {
            var map = new HashMap<String, String>();
            for (var groupName: groupsToExtract) {
                map.put(groupName, matcher.group(groupName));
            }
            allMatches.add(map);
        }
        return allMatches;
    }

}
