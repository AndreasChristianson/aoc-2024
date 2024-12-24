package com.pessimistic.aoc2024.days.day19;

import com.pessimistic.aoc2024.util.FileUtils;
import com.pessimistic.aoc2024.util.Memoization;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Day19 {
    private static final Logger logger = Logger.getLogger(Day19.class.getName());

    private Day19() {
    }

    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var towels = getTowels(lines);
        var onsen = new Onsen(towels);
        var patterns = getPatterns(lines);

        return patterns.stream()
                .filter(onsen::canMake)
                .peek(pattern -> logger.info("Can make %s".formatted(pattern)))
                .count();
    }

    private static List<Towel> getPatterns(List<String> lines) {
        return lines.stream()
                .skip(2)
                .map(string -> Arrays.stream(string.split(""))
                        .map(TowelColor::fromString)
                        .toList()
                )
                .map(Towel::new)
                .toList();
    }

    private static Set<Towel> getTowels(List<String> lines) {
        var towels = Arrays.stream(lines.getFirst().split(","))
                .map(String::trim)
                .map(string -> Arrays.stream(string.split(""))
                        .map(TowelColor::fromString)
                        .toList()
                )
                .map(Towel::new)
                .collect(Collectors.toSet());
        return towels;
    }

    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var towels = getTowels(lines);
        var onsen = new Onsen(towels);
        var patterns = getPatterns(lines);

        return patterns.stream()
                .mapToLong(onsen::permutationCount)
                .peek(permutations->logger.info("Permutation count: %d".formatted(permutations)))
                .sum();
    }
}
