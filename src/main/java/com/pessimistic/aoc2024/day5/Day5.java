package com.pessimistic.aoc2024.day5;

import com.pessimistic.aoc2024.FileUtils;
import com.pessimistic.aoc2024.TextUtils;
import com.pessimistic.aoc2024.TwoDimensionalUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Day5 {
    private Day5() {
    }


    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        return lines.stream().map(Long::parseLong).findFirst().orElseThrow();
    }


    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        return lines.stream().map(Long::parseLong).findFirst().orElseThrow();
    }
}
