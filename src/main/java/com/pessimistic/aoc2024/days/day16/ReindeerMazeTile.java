package com.pessimistic.aoc2024.days.day16;

import com.pessimistic.aoc2024.twoDimensional.Direction;
import com.pessimistic.aoc2024.twoDimensional.Point;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public record ReindeerMazeTile(
        ReindeerMazeTileType type,
        Point location,
        Direction direction
) {
    public static List<ReindeerMazeTile> converter(Pair<Point, ReindeerMazeTileType> pair) {
        return Direction.cardinalDirections()
                .stream()
                .map(dir -> new ReindeerMazeTile(pair.getRight(), pair.getLeft(), dir))
                .toList();
    }
}
