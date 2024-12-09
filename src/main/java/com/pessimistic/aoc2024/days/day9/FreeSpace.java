package com.pessimistic.aoc2024.days.day9;

public record FreeSpace(
        int length
) implements Blocks {

    @Override
    public String idString() {
        return ".";
    }
}
