package com.pessimistic.aoc2024.days.day15;

import com.pessimistic.aoc2024.twoDimensional.Point;

public enum WarehouseObject {
    WALL,
    CRATE,
    ROBOT,
    WIDE_LEFT,
    WIDE_RIGHT;

    public static WarehouseObject fromChar(Character character) {
        return switch (character) {
            case '#' -> WALL;
            case 'O' -> CRATE;
            case '@' -> ROBOT;
            case '.' -> null;
            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case WALL -> "#";
            case CRATE -> "O";
            case WIDE_LEFT -> "[";
            case WIDE_RIGHT -> "]";
            case ROBOT -> "@";
        };
    }

    public static long gps(Point point) {
        return point.col() + point.row() * 100;
    }
}
