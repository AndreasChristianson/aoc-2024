package com.pessimistic.aoc2024.days.day18;

import com.pessimistic.aoc2024.graph.DirectedGraph;
import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.twoDimensional.Point;
import com.pessimistic.aoc2024.twoDimensional.Range2D;
import com.pessimistic.aoc2024.util.FileUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static com.pessimistic.aoc2024.days.day18.InsideAComputer.SPACE;

public class Day18 {
    private static final Logger logger = Logger.getLogger(Day18.class.getName());

    private Day18() {
    }

    public static long star1(String fileName, Range2D range, int limit) {
        var fallingBytes = new HashMap<Point, InsideAComputer>();
        FileUtils.readTestFile(fileName).stream()
                .limit(limit)
                .map(line -> line.split(","))
                .map(array -> Point.of(Long.parseLong(array[1]), Long.parseLong(array[0])))
                .forEach(point -> fallingBytes.put(point, InsideAComputer.FALLING_BYTE));

        var grid = new Grid<InsideAComputer, String>(fallingBytes, range);
        grid.fill(SPACE);
        System.out.println(grid);
        var graph = DirectedGraph.fromGrid(
                grid,
                (from, to) -> to.getRight() == SPACE &&
                        from.getLeft().manhattanDistance(to.getLeft()) == 1 ? Optional.of(1L) : Optional.empty(),
                pair -> List.of(pair),
                List.of(SPACE)
        );
        graph.traverseFrom(Pair.of(Point.of(0, 0), SPACE));
        return graph.findMinDistance(item -> item.getLeft().equals(range.max()));
    }

    public static String star2(String fileName, Range2D range) {
        var points = FileUtils.readTestFile(fileName).stream()
                .map(line -> line.split(","))
                .map(array -> Point.of(Long.parseLong(array[1]), Long.parseLong(array[0])))
                .toList();


        var grid = new Grid<InsideAComputer, String>(new HashMap<>(), range);
        grid.fill(SPACE);
        for (var point : points) {
            grid.set(point, InsideAComputer.FALLING_BYTE);
            var graph = DirectedGraph.fromGrid(
                    grid,
                    (from, to) -> to.getRight() == SPACE &&
                            from.getLeft().manhattanDistance(to.getLeft()) == 1 ? Optional.of(1L) : Optional.empty(),
                    pair -> List.of(pair),
                    List.of(SPACE)
            );
            try {
                graph.traverseFrom(Pair.of(Point.of(0, 0), SPACE));
                graph.findMinDistance(item -> item.getLeft().equals(range.max()));
            } catch (Exception _) {
                System.out.println(grid);

                return "%d,%d".formatted(point.col(), point.row());
            }
        }
        return "";
    }
}
