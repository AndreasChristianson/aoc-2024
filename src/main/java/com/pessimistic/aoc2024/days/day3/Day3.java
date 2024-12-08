package com.pessimistic.aoc2024.days.day3;

import com.pessimistic.aoc2024.util.FileUtils;
import com.pessimistic.aoc2024.util.NullUtils;
import com.pessimistic.aoc2024.util.TextUtils;

public class Day3 {
    private Day3() {
    }


    public static long star1(String fileName) {
        var text = FileUtils.readWholeTestFile(fileName);
        return TextUtils.allMatches(
                        text,
                        "mul\\((?<left>\\d+),(?<right>\\d+)\\)"
                )
                .mapToLong(match -> {
                    var left = Long.parseLong(match.get("left"));
                    var right = Long.parseLong(match.get("right"));
                    return left * right;
                })
                .sum();
    }


    public static long star2(String fileName) {
        final var text = FileUtils.readWholeTestFile(fileName);
        var matches = TextUtils.allMatches(
                text,
                "(?<do>do)\\(\\)|(?<dont>don't)\\(\\)|(?<mul>mul)\\((?<left>\\d+),(?<right>\\d+)\\)"
        );
        var process = true;
        var sum = 0L;
        for (var match : matches.toList()) {
            switch (NullUtils.coalesce(match.get("do"), match.get("dont"), match.get("mul"))) {
                case "mul":
                    if (process) {
                        var left = Long.parseLong(match.get("left"));
                        var right = Long.parseLong(match.get("right"));
                        sum += left * right;
                    }
                    break;
                case "do":
                    process = true;
                    break;
                case "don't":
                    process = false;
                case null:
                default:
            }
        }
        return sum;
    }
}
