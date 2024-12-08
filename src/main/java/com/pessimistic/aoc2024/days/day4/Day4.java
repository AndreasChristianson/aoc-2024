package com.pessimistic.aoc2024.days.day4;

import com.pessimistic.aoc2024.util.FileUtils;
import com.pessimistic.aoc2024.util.TextUtils;
import com.pessimistic.aoc2024.util.TwoDimensionalTextUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Day4 {
    private Day4() {
    }


    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var chars = lines.stream().map(line -> Arrays.asList(line.split(""))).toList();
        var rotated45 = TwoDimensionalTextUtils.rotate45(chars);
        var rotated90 = TwoDimensionalTextUtils.rotate90(chars);
        var rotated135 = TwoDimensionalTextUtils.rotate45(rotated90);
        var rotated180 = TwoDimensionalTextUtils.rotate90(rotated90);
        var rotated225 = TwoDimensionalTextUtils.rotate45(rotated180);
        var rotated270 = TwoDimensionalTextUtils.rotate90(rotated180);
        var rotated315 = TwoDimensionalTextUtils.rotate45(rotated270);
        var rotated360 = TwoDimensionalTextUtils.rotate90(rotated270);
        var withRotation = List.of(
                rotated360,
                rotated45,
                rotated90,
                rotated135,
                rotated180,
                rotated225,
                rotated270,
                rotated315
        );
        return withRotation.stream()
                .flatMap(Collection::stream)
                .map(list -> String.join("", list))
                .mapToLong(line -> TextUtils.matchCount(line, "XMAS"))
                .sum();
    }


    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var chars = lines.stream().map(line -> Arrays.asList(line.split(""))).toList();
        var count = 0;

        for (var column = 1; column < chars.getFirst().size() - 1; column++) {
            for (var row = 1; row < chars.size() - 1; row++) {
                var center = chars.get(row).get(column);
                var nw = chars.get(row - 1).get(column - 1);
                var ne = chars.get(row - 1).get(column + 1);
                var sw = chars.get(row + 1).get(column - 1);
                var se = chars.get(row + 1).get(column + 1);
                var goodMid = center.equals("A");
                var goodSlash = (ne.equals("M") && sw.equals("S")) ||
                        (ne.equals("S") && sw.equals("M"));
                var goodBackSlash = (nw.equals("M") && se.equals("S")) ||
                        (nw.equals("S") && se.equals("M"));
                if (goodMid && goodSlash && goodBackSlash) {
                    count++;
                }
            }
        }
        return count;
    }
}
