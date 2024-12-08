package com.pessimistic.aoc2024.days.day5;

public record Page(
        int number
) {
    public static Page parse(String part) {
        return new Page(Integer.parseInt(part));
    }
}
