package com.pessimistic.aoc2024.day3;

import com.pessimistic.aoc2024.FileUtils;
import com.pessimistic.aoc2024.NullUtils;
import com.pessimistic.aoc2024.TextUtils;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day3 {
    private Day3() {
    }


    public static long star1(String fileName) {
        var text = FileUtils.readWholeTestFile(fileName);
        var matches = TextUtils.allMatches(text, "mul\\((?<left>\\d+),(?<right>\\d+)\\)", List.of("left","right"));
        return matches.stream()
                .mapToInt(map -> Integer.parseInt(map.get("left")) * Integer.parseInt(map.get("right")))
                .sum();
    }


    public static long star2(String fileName) {
        var text = FileUtils.readWholeTestFile(fileName);
        var matches = TextUtils.allMatches(
                text,
                "(?<do>do)\\(\\)|(?<dont>don't)\\(\\)|(?<mul>mul)\\((?<left>\\d+),(?<right>\\d+)\\)",
                List.of("left","right", "do", "dont", "mul")
        );
        var process = true;
        var sum = 0L;
        for(var match : matches) {
            switch (NullUtils.coalesce(match.get("do"), match.get("dont"), match.get("mul"))) {
                case "mul":{
                    if(process) {
                        sum+=Integer.parseInt(match.get("left")) * Integer.parseInt(match.get("right"));
                    }
                }
                break;
                case "do": process=true;
                break;
                case "don't": process=false;
                break;
            }

        }
        return sum;
    }
}
