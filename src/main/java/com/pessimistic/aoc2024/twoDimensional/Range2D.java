package com.pessimistic.aoc2024.twoDimensional;

import com.pessimistic.aoc2024.numbers.Range;

import java.util.Iterator;

public record Range2D(
        Range rowRange,
        Range colRange
) implements Iterable<Point> {
    public static Range2D of(int rowMin, int rowMax, int colMin, int colMax) {
        return new Range2D(
                new Range(rowMin, rowMax),
                new Range(colMin, colMax)
        );
    }

    public boolean contains(Point point) {
        return colRange.contains(point.col()) &&
                rowRange.contains(point.row());
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

    public Point max() {
        return Point.of(rowRange().max(), colRange().max());
    }
}
