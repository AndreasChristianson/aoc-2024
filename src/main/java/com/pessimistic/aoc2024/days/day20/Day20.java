package com.pessimistic.aoc2024.days.day20;

import com.pessimistic.aoc2024.graph.DirectedGraph;
import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.twoDimensional.Point;
import com.pessimistic.aoc2024.util.FileUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class Day20 {
    private static final Logger logger = Logger.getLogger(Day20.class.getName());

    private Day20() {
    }

    public static long star2(int allowedCheatLength, long minCheat, String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var grid = Grid.of(lines, RacetrackSquare::fromChar);
        var start = grid.getItemPoints(RacetrackSquare.START)
                .getFirst();
        var end = grid.getItemPoints(RacetrackSquare.END)
                .getFirst();
        var track = new RaceTrack(
                grid
        );
        track.traverseFrom(start);
        var noCheats = track.findMinDistance(end::equals);
        logger.info("distance without cheats: %d".formatted(noCheats));
        var cheats = track.findCheats(allowedCheatLength);

        logger.info("cheat count: %d".formatted(cheats.size()));



        return cheats.entrySet()
                .stream()
                .filter(entry -> entry.getValue() >= minCheat)
                .count();
    }
}
