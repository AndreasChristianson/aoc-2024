package com.pessimistic.aoc2024.days.day10;

import com.pessimistic.aoc2024.days.day9.FileSystem;
import com.pessimistic.aoc2024.util.FileUtils;
import com.pessimistic.aoc2024.util.TextUtils;


public class Day10 {
    private Day10() {
    }

    public static long star1(String fileName) {
        var line = FileUtils.readWholeTestFile(fileName);
        return TextUtils.toLongStream(line).findFirst().orElseThrow();
    }


    public static long star2(String fileName) {
        var line = FileUtils.readWholeTestFile(fileName);
        return TextUtils.toLongStream(line).findFirst().orElseThrow();
    }
}
