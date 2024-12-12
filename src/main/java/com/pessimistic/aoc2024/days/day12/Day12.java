package com.pessimistic.aoc2024.days.day12;

import com.pessimistic.aoc2024.days.day11.Stones;
import com.pessimistic.aoc2024.twoDimensional.Direction;
import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.util.FileUtils;
import com.pessimistic.aoc2024.util.TextUtils;


public class Day12 {
    private Day12() {
    }

    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var garden = GardenGrid.fromLines(lines, Plant::new);
        return garden.group(Direction.cardinalDirections(), Object::equals)
                .mapToLong(GardenGrid.Group::fenceCost)
                .sum();
    }


    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var garden = GardenGrid.fromLines(lines, Plant::new);
        return garden.group(Direction.cardinalDirections(), Object::equals)
                .mapToLong(GardenGrid.Group::bulkFenceCost)
                .sum();
    }
}
