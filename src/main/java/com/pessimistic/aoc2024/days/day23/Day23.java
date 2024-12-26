package com.pessimistic.aoc2024.days.day23;

import com.pessimistic.aoc2024.util.FileUtils;

import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Day23 {
    private static final Logger logger = Logger.getLogger(Day23.class.getName());

    private Day23() {
    }

    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var lanparty = LanParty.fromLines(lines);
        var triplets = lanparty.findTriplets();
        return triplets.stream()
                .filter(triplet -> triplet.startsWith("t"))
                .count();
    }

    // https://en.wikipedia.org/wiki/Bron%E2%80%93Kerbosch_algorithm
    public static String star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var lanparty = LanParty.fromLines(lines);
        var cliques = lanparty.findCliques();
        var maxCliqueSize = cliques.stream()
                .mapToLong(Set::size)
                .max()
                .orElseThrow();
        return cliques.stream()
                .filter(clique -> clique.size() == maxCliqueSize)
                .findAny()
                .orElseThrow()
                .stream()
                .sorted()
                .collect(Collectors.joining(","));
    }
}
