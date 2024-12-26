package com.pessimistic.aoc2024.days.day20;

public enum RacetrackSquare {
    WALL,
    SPACE,
    START,
    END;

    @Override
    public String toString() {
        return switch (this) {
            case WALL -> "#";
            case SPACE -> ".";
            case END -> "E";
            case START -> "S";
        };
    }

    public static RacetrackSquare fromChar(Character character) {
        return switch (character) {
            case '.' -> SPACE;
            case 'S' -> START;
            case 'E' -> END;
            default -> null;
        };
    }
}
