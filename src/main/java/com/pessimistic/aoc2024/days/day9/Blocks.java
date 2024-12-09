package com.pessimistic.aoc2024.days.day9;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface Blocks {
    int length();

    String idString();

    default String asString() {
        return IntStream.range(0, length())
                .mapToObj(_ -> idString())
                .collect(Collectors.joining());
    }

}
