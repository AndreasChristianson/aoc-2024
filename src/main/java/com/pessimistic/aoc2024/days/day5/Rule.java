package com.pessimistic.aoc2024.days.day5;

public record Rule(
        Page first,
        Page second
) {

    public static Rule parse(String line) {
        var lineParts = line.split("\\|");
        return new Rule(Page.parse(lineParts[0]), Page.parse(lineParts[1]));
    }
}
