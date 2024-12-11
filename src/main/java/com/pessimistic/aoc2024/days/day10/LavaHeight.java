package com.pessimistic.aoc2024.days.day10;

public record LavaHeight(
        int height
) {
    public static LavaHeight fromChar(Character character) {
        return new LavaHeight(Integer.parseInt(character.toString()));
    }

    public static LavaHeight none() {
        return new LavaHeight(Integer.MIN_VALUE);
    }

    @Override
    public String toString() {
        return String.valueOf(height);
    }

    public LavaHeight next() {
        return new LavaHeight(height + 1);
    }
}
