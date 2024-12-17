package com.pessimistic.aoc2024.days.day16;

import com.pessimistic.aoc2024.twoDimensional.Direction;

import java.util.Optional;

public class ReindeerMaze {
    public static Optional<Long> weightFunction(ReindeerMazeTile from, ReindeerMazeTile to) {
        if (to.type() == ReindeerMazeTileType.WALL) {
            return Optional.empty();
        }
        if (
                from.location().equals(to.location())
                        && from.direction() != to.direction()
        ) {
            return Optional.of(rotateCost(from.direction(), to.direction()));
        }
        if (
                from.location().manhattanDistance(to.location()) == 1
                        && from.direction().equals(to.direction())
                        && from.location().add(from.direction().getDelta()).equals(to.location())
        ) {
            return Optional.of(1L);
        }
        return Optional.empty();
    }

    private static long rotateCost(Direction from, Direction to) {
        return from.minRotationDegrees(to) / 90 * 1000L;
    }
}
