package com.pessimistic.aoc2024.days.day21;

import com.pessimistic.aoc2024.twoDimensional.Point;

import java.util.List;

public enum DirPadInstruction {
    LEFT(Point.of(1, 0)),
    RIGHT(Point.of(1, 2)),
    DOWN(Point.of(1, 1)),
    UP(Point.of(0, 1)),
    ENTER(Point.of(0, 2)),
    ;
    private static final Point OFF_LIMITS = Point.of(0, 0);


    private final Point location;

    DirPadInstruction(Point location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return switch (this) {
            case LEFT -> "<";
            case RIGHT -> ">";
            case UP -> "^";
            case DOWN -> "v";
            case ENTER -> "A";
        };
    }

    public List<DirPadInstruction> encode(DirPadInstruction lastPosition) {
        return switch (this) {
            case ENTER -> switch (lastPosition) {
                case ENTER -> List.of(ENTER);
                case LEFT -> List.of(RIGHT, RIGHT, UP, ENTER);
                case RIGHT -> List.of(UP, ENTER);
//                case DOWN -> List.of(RIGHT, UP, ENTER);
                case DOWN -> List.of(UP, RIGHT, ENTER); //options
                case UP -> List.of(RIGHT, ENTER);
            };
            case LEFT -> switch (lastPosition) {
                case ENTER -> List.of(DOWN, LEFT, LEFT, ENTER);
                case LEFT -> List.of(ENTER);
                case RIGHT -> List.of(LEFT, LEFT, ENTER);
                case DOWN -> List.of(LEFT, ENTER);
                case UP -> List.of(DOWN, LEFT, ENTER);
            };
            case RIGHT -> switch (lastPosition) {
                case ENTER -> List.of(DOWN, ENTER);
                case LEFT -> List.of(RIGHT, RIGHT, ENTER);
                case RIGHT -> List.of(ENTER);
                case DOWN -> List.of(RIGHT, ENTER);
//                case UP -> List.of(RIGHT, DOWN, ENTER);
                case UP -> List.of(DOWN, RIGHT, ENTER);  // options
            };
            case UP -> switch (lastPosition) {
                case ENTER -> List.of(LEFT, ENTER);
                case LEFT -> List.of(RIGHT, UP, ENTER);
                case RIGHT -> List.of(LEFT, UP, ENTER);
//                case RIGHT -> List.of(UP, LEFT, ENTER); // options
                case DOWN -> List.of(UP, ENTER);
                case UP -> List.of(ENTER);
            };
            case DOWN -> switch (lastPosition) {
                case ENTER -> List.of(LEFT, DOWN, ENTER);
//                case ENTER -> List.of(DOWN, LEFT, ENTER); //options
                case LEFT -> List.of(RIGHT, ENTER);
                case RIGHT -> List.of(LEFT, ENTER);
                case DOWN -> List.of(ENTER);
                case UP -> List.of(DOWN, ENTER);
            };
        };
    }
}
