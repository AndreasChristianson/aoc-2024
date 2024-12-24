package com.pessimistic.aoc2024.days.day19;

import java.util.List;
import java.util.stream.Collectors;

public record Towel(
        List<TowelColor> colors
) {
    @Override
    public String toString() {
        return colors.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public int colorCount() {
        return colors.size();
    }
}
