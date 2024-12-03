package com.pessimistic.aoc2024.day3;

import com.pessimistic.aoc2024.FileUtils;
import com.pessimistic.aoc2024.TextUtils;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day3 {
    private Day3() {
    }

    private static Stream<IntStream> readFile(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        return lines.stream()
                .map(TextUtils::toIntStream);
    }

    public static long star1(String fileName) {
        return readFile(fileName)
                .count();
    }


    public static long star2(String fileName) {
        return readFile(fileName)
                .count();
    }
}
