package com.pessimistic.aoc2024.days.day23;

import com.pessimistic.aoc2024.util.FileUtils;

import java.util.logging.Logger;

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

    public static String star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var lanparty = LanParty.fromLines(lines);
        var cluster = lanparty.findLargestCluster();
        return "-1";
    }
}
