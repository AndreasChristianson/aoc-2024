package com.pessimistic.aoc2024.day6;

public enum MapObject {
    OBSTACLE, GUARD;

    static MapObject charToItem(Character character) {
        return switch (character) {
            case '#' -> OBSTACLE;
            case '^' -> GUARD;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case OBSTACLE -> "#";
            case GUARD -> "G";
        };
    }
}
