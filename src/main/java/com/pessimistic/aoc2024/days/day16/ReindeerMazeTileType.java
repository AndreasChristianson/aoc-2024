package com.pessimistic.aoc2024.days.day16;

public enum ReindeerMazeTileType {
    FLOOR,
    WALL,
    START,
    FINISH;

    public static ReindeerMazeTileType fromChar(Character character) {
        return switch (character) {
            case '.' -> FLOOR;
            case '#' -> WALL;
            case 'S' -> START;
            case 'E' -> FINISH;
            default -> throw new IllegalStateException("Unexpected value: %s".formatted(character));
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case FLOOR -> ".";
            case WALL -> "#";
            case START -> "S";
            case FINISH -> "F";
        };
    }
}
