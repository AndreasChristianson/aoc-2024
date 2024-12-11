package com.pessimistic.aoc2024.days.day10;

import com.pessimistic.aoc2024.days.day9.FileSystem;
import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.twoDimensional.Point;
import com.pessimistic.aoc2024.util.FileUtils;
import com.pessimistic.aoc2024.util.TextUtils;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Day10 {
    private Day10() {
    }

    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var grid = Grid.<LavaHeight, String>of(lines, LavaHeight::fromChar);
        return grid.find(new LavaHeight(0)).stream()
                .map(point -> new Trailhead( grid, point))
                .map(Trailhead::findTrails)
                .map(list -> list.stream()
                        .filter(Trailhead.Trail::complete)
                        .map(Trailhead.Trail::getLastStop)
                        .collect(Collectors.toSet())
                )
                .flatMap(Collection::stream)
                .count();
    }



    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var grid = Grid.<LavaHeight, String>of(lines, LavaHeight::fromChar);
        return grid.find(new LavaHeight(0)).stream()
                .map(point -> new Trailhead( grid, point))
                .map(Trailhead::findTrails)
                .flatMap(Collection::stream)
                .filter(Trailhead.Trail::complete)
                .count();
    }
}
