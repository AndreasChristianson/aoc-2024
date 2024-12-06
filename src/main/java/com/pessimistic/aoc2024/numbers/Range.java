package com.pessimistic.aoc2024.numbers;

import java.util.Iterator;

public record Range(
        int min, //inclusive
        int max //inclusive
) implements Iterable<Integer> {
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int current = min;

            @Override
            public boolean hasNext() {
                return current <= max;
            }

            @Override
            public Integer next() {
                return current++;
            }
        };
    }

    public boolean contains(int i) {
        return i >= min && i <= max;
    }

    public int diff() {
        return max - min +1;
    }
}
