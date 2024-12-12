package com.pessimistic.aoc2024.twoDimensional;

import java.util.List;
import java.util.function.Function;

public enum Direction {
    N, W, E, S;


    private static final List<Direction> CARDINALS = List.of(N, W, E, S);

    public static List<Direction> cardinalDirections() {
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

    public Function<Point,Integer> offsetFunction() {
        return switch (this) {
            case N, S -> Point::Row;
            case W, E -> Point::Col;
        };
    }
}
