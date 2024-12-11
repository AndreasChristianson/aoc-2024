package com.pessimistic.aoc2024.twoDimensional;

import java.util.List;

public enum Direction {
    N, W, E, S;


    private static final Iterable<Direction> CARDINALS = List.of(N, W, E, S);

    public static Iterable<Direction> cardinalDirections() {
        return CARDINALS;
    }

    public Point getDelta() {
        return switch (this) {
            case N -> new Point(-1, 0);
            case S -> new Point(1, 0);
            case E -> new Point(0, 1);
            case W -> new Point(0, -1);
        };
    }

    public Direction rotateClockwise90() {
        return switch (this) {
            case N -> E;
            case S -> W;
            case W -> N;
            case E -> S;
        };
    }
}
