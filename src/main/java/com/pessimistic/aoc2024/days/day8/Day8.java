package com.pessimistic.aoc2024.days.day8;

import com.pessimistic.aoc2024.util.FileUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Day8 {
    private Day8() {
    }

    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var grid = AntennaGrid.toAntennaGrid(lines, Antenna::charToItem);
        var antiNodes = grid.getUniqueItems().stream()
                .flatMap(grid::getAntiNodeLines)
                .flatMap(line -> Stream.of(line.getNthPoint(-1), line.getNthPoint(2)))
                .filter(grid.getRange()::contains)
                .collect(Collectors.toSet());
        System.out.println(grid.toString(point -> antiNodes.contains(point) ? "#" : null));
        return antiNodes.size();
    }


    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var grid = AntennaGrid.toAntennaGrid(lines, Antenna::charToItem);
        var antiNodes = grid.getUniqueItems().stream()
                .flatMap(grid::getAntiNodeLines)
                .flatMap(line -> line.propagateWaveCrestsWithinRange(grid.getRange()))
                .collect(Collectors.toSet());
        System.out.println(grid.toString(point -> antiNodes.contains(point) ? "#" : null));
        return antiNodes.size();
    }
}
