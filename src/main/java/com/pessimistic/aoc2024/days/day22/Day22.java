package com.pessimistic.aoc2024.days.day22;

import com.pessimistic.aoc2024.util.FileUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Day22 {
    private static final Logger logger = Logger.getLogger(Day22.class.getName());

    private Day22() {
    }

    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var generators = lines.stream()
                .map(Long::parseLong)
                .map(Generator::new)
                .toList();
        generators.forEach(generator -> generator.next(2000));
        return generators.stream()
                .mapToLong(Generator::get)
                .sum();
    }

    //    -9 to 9 range: 19 values
    //    4 in sequence
    //    19^4 = 130321
    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var generators = lines.stream()
                .map(Long::parseLong)
                .map(Generator::new)
                .toList();
        generators.forEach(generator -> generator.next(2000));
        var combinedHistory = new HashMap<FourChanges, Long>();
        generators.stream()
                .map(Generator::history)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .forEach(entry -> combinedHistory.merge(entry.getKey(), Long.valueOf(entry.getValue()), Long::sum));
        return combinedHistory.values().stream()
                .mapToLong(Long::longValue)
                .max()
                .orElseThrow();
    }
}
