package com.pessimistic.aoc2024.numbers;

import java.util.Iterator;

public record Range(
        long min, //inclusive
        long max //inclusive
) implements Iterable<Long> {
    public Range {
        assert min <= max;
    }

    @Override
    public Iterator<Long> iterator() {
        return new Iterator<Long>() {
            private long current = min;

            @Override
            public boolean hasNext() {
                return current <= max;
            }

            @Override
            public Long next() {
                return current++;
            }
        };
    }

    public boolean contains(int i) {
        return i >= min && i <= max;
    }

    public long diff() {
        return max - min + 1;
    }

    public boolean contains(double d) {
        return d >= min && d <= max;
    }

    public long width() {
        return max - min +1;
    }

    public long midPoint() {
        assert width()%2==1;
        return width()/2+min();
    }
}
