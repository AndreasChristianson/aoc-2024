package com.pessimistic.aoc2024.day7;

import com.pessimistic.aoc2024.day6.MapObject;
import com.pessimistic.aoc2024.twoDimensional.Direction;
import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.util.FileUtils;
import com.pessimistic.aoc2024.util.TextUtils;


public class Day7 {
    private Day7() {
    }

    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        return lines.stream().map(TextUtils::toIntStream).findAny().orElseThrow().sum();
    }

    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        return lines.stream().map(TextUtils::toIntStream).findAny().orElseThrow().sum();
    }
}
