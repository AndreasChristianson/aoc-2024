package com.pessimistic.aoc2024.days.day13;

import com.pessimistic.aoc2024.twoDimensional.Point;
import com.pessimistic.aoc2024.util.FileUtils;
import com.pessimistic.aoc2024.util.TextUtils;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class Day13 {
    private Day13() {
    }

    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var lineSystems = toLineSystems(lines, 0);
        return lineSystems.stream()
                .map(LineSystem::findPresses)
                .mapToLong(stream -> stream
                        .mapToLong(pair -> pair.getLeft() * 3 + pair.getRight())
                        .min()
                        .orElse(0))
                .sum();
    }

    private static List<LineSystem> toLineSystems(List<String> lines, long additional) {
        var ret = new ArrayList<LineSystem>();
        for (var partition : ListUtils.partition(lines, 4)) {
            /*
            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279
            */
            var aMatches = TextUtils.allMatches(partition.get(0), "Button A: X\\+(?<x>\\d+), Y\\+(?<y>\\d+)")
                    .findFirst().orElseThrow();
            var bMatches = TextUtils.allMatches(partition.get(1), "Button B: X\\+(?<x>\\d+), Y\\+(?<y>\\d+)")
                    .findFirst().orElseThrow();
            var target = TextUtils.allMatches(partition.get(2), "Prize: X=(?<x>\\d+), Y=(?<y>\\d+)")
                    .findFirst().orElseThrow();

            ret.add(new LineSystem(
                    Point.of(Integer.parseInt(aMatches.get("x")), Integer.parseInt(aMatches.get("y"))),
                    Point.of(Integer.parseInt(bMatches.get("x")), Integer.parseInt(bMatches.get("y"))),
                    Point.of(Integer.parseInt(target.get("x")) + additional, Integer.parseInt(target.get("y")) + additional)
            ));
        }
        return ret;
    }


    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var lineSystems = toLineSystems(lines, 10000000000000L);
        return lineSystems.stream()
                .map(LineSystem::findPresses)
                .mapToLong(stream -> stream
                        .mapToLong(pair -> pair.getLeft() * 3 + pair.getRight())
                        .min()
                        .orElse(0))
                .sum();
    }
}
