package com.pessimistic.aoc2024.day2;

import com.pessimistic.aoc2024.FileUtils;
import com.pessimistic.aoc2024.TextUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day2 {
    private Day2() {
    }
    private static int readFile(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        return lines.stream()
                .map(TextUtils::splitOnWhitespace)
                .flatMap(s -> s.map(Integer::parseInt))
                .findFirst().orElse(0);
    }

    public static long doTheThing(String fileName) {
        return readFile(fileName);
    }

    public static long doTheOtherThing(String fileName) {
        return readFile(fileName);

    }
}
