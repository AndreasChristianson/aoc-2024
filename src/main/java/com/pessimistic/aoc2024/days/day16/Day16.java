package com.pessimistic.aoc2024.days.day16;

import com.pessimistic.aoc2024.graph.DirectedGraph;
import com.pessimistic.aoc2024.twoDimensional.Direction;
import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.util.FileUtils;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.pessimistic.aoc2024.days.day16.ReindeerMazeTileType.*;

public class Day16 {
    private static final Logger logger = Logger.getLogger(Day16.class.getName());

    private Day16() {
    }

    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var grid = Grid.of(lines, ReindeerMazeTileType::fromChar);
        var maze = DirectedGraph.fromGrid(
                grid,
                ReindeerMaze::weightFunction,
                ReindeerMazeTile::converter,
                List.of(FLOOR, START, FINISH)
        );
        var from = grid.find(START).stream().findFirst().orElseThrow();

        return maze.findMinDistance(
                new ReindeerMazeTile(START, from, Direction.E),
                reindeerMazeTile -> reindeerMazeTile.type().equals(FINISH)
        );
    }


    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var grid = Grid.of(lines, ReindeerMazeTileType::fromChar);
        var maze = DirectedGraph.fromGrid(
                grid,
                ReindeerMaze::weightFunction,
                ReindeerMazeTile::converter,
                List.of(FLOOR, START, FINISH)
        );
        var from = grid.find(START).stream().findFirst().orElseThrow();

        return maze.findPaths(
                        new ReindeerMazeTile(START, from, Direction.E),
                        reindeerMazeTile -> reindeerMazeTile.type().equals(FINISH)
                )
                .stream()
                .flatMap(Collection::stream)
                .map(ReindeerMazeTile::location)
                .collect(Collectors.toSet())
                .size();
    }
}
