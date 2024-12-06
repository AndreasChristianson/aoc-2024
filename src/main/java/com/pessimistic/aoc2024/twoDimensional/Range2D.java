package com.pessimistic.aoc2024.twoDimensional;

import com.pessimistic.aoc2024.numbers.Range;

import java.util.Iterator;

public record Range2D(
        Range rowRange,
        Range colRange
) implements Iterable<Point> {
    public boolean contains(Point point) {
        return colRange.contains(point.Col()) &&
                rowRange.contains(point.Row());
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < rowRange.diff() * colRange.diff();
            }

            @Override
            public Point next() {
                var ret = Point.of(
                        current / rowRange().diff() + rowRange().min(),
                        current % colRange().diff() + colRange().min()
                );
                current++;
                return ret;
            }
        };
    }
}
