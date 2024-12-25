package com.pessimistic.aoc2024.days.day25;

import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.util.FileUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.logging.Logger;

public class Day25 {
    private static final Logger logger = Logger.getLogger(Day25.class.getName());

    private Day25() {
    }

    public static long star1(String fileName) {
       var  lines = FileUtils.readTestFile(fileName);
        var groups = ListUtils.partition(lines, 8);
        var keys = groups.stream()
                .filter(list -> list.getFirst().equals("#####"))
                .map(Grid::<String>of)
                .map(Schematic::fromGrid)
                .toList();

        var locks = groups.stream()
                .filter(list -> list.getFirst().equals("....."))
                .map(Grid::<String>of)
                .map(Schematic::fromGrid)
                .toList();
        return locks.stream()
                .flatMap(lock -> keys.stream().map(key -> Pair.of(lock, key)))
                .filter(pair -> pair.getLeft().fits(pair.getRight()))
                .count();
    }

    public static long star2(String fileName) {
        return -1;
    }
}
