package com.pessimistic.aoc2024.days.day202317;

public record HeatLoss(
        int value
) {
    public static HeatLoss fromChar(Character character) {
        return new HeatLoss(Integer.parseInt(String.valueOf(character)));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
