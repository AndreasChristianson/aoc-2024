package com.pessimistic.aoc2024.day1;

import com.pessimistic.aoc2024.util.FileUtils;
import com.pessimistic.aoc2024.util.TextUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day1 {
    private Day1() {
    }

    public static Long similarityStar1(String fileName) {
        var combined = getCombined(fileName);
        var left = combined.stream().map(l -> l.get(0)).sorted().toList();
        var right = combined.stream().map(l -> l.get(1)).sorted().toList();
        var sum = 0L;
        for (var i = 0; i < combined.size(); i++) {
            sum += Math.abs(left.get(i) - right.get(i));
        }
        return sum;
    }

    public static long similarityStar2(String fileName) {
        var combined = getCombined(fileName);
        var left = combined.stream().map(l -> l.get(0)).sorted().toList();
        var rightCounts = combined.stream()
                .map(l -> l.get(1)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return left.stream().mapToLong(i -> i * rightCounts.getOrDefault(i, 0L))
                .sum();
    }

    private static List<List<Integer>> getCombined(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        return lines.stream()
                .map(TextUtils::splitOnWhitespace)
                .map(s -> s.map(Integer::parseInt).toList())
                .toList();
    }
}
