package com.pessimistic.aoc2024.days.day9;

import com.pessimistic.aoc2024.days.day8.AntennaGrid;
import com.pessimistic.aoc2024.util.FileUtils;
import com.pessimistic.aoc2024.util.TextUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Day9 {
    private Day9() {
    }

    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);

        return TextUtils.toLongStream(lines.getFirst()).findAny().orElseThrow();
    }


    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);

        return TextUtils.toLongStream(lines.getFirst()).findAny().orElseThrow();
    }
}
