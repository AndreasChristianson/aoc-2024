package com.pessimistic.aoc2024.days.day8;

public record Antenna(
        String callsign
) {
    public static Antenna charToItem(Character character) {
        return switch (character) {
            case '.' -> null;
            default -> new Antenna(character.toString());
        };
    }

    @Override
    public String toString() {
        return callsign;
    }
}
