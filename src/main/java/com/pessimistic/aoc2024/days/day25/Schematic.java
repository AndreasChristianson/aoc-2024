package com.pessimistic.aoc2024.days.day25;

import com.pessimistic.aoc2024.twoDimensional.Grid;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Schematic {
    final SortedMap<Long, Long> lengthsByRow;

    public Schematic(SortedMap<Long, Long> lengthsByRow) {
        this.lengthsByRow = lengthsByRow;
    }

    public static Schematic fromGrid(Grid<Character, String> grid) {
        var ret = new TreeMap<Long, Long>();
        for (var point : grid.getItemPoints('#')) {
            ret.put(point.col(), ret.getOrDefault(point.col(), -1L) + 1);
        }
        return new Schematic(ret);
    }

    @Override
    public String toString() {
        return lengthsByRow.sequencedValues()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    public boolean fits(Schematic other) {
        return lengthsByRow.keySet()
                .stream()
                .allMatch(index -> lengthsByRow.get(index) + other.lengthsByRow.get(index) <= 5);
    }
}
