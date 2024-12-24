package com.pessimistic.aoc2024.twoDimensional;

import org.apache.commons.collections4.iterators.IteratorChain;

import java.util.Iterator;

/**
 * @param row when used with Cartesian coords, x
 * @param col when used with Cartesian coords, y
 */
public record Point(
        long row,
        long col
) {

    public static Point of(long row, long col) {
        return new Point(row, col);
    }

    public Point add(Point delta) {
        return new Point(row + delta.row, col + delta.col);
    }

    public Point subtract(Point delta) {
        return new Point(row - delta.row, col - delta.col);
    }

    public Point scale(long n) {
        return new Point(row * n, col * n);
    }

    public Point negate() {
        return new Point(-1 * row, -1 * col);
    }

    public Point wrap(Range2D range) {
        var normalizedCol = col() - range.colRange().min();
        var newCol = normalizedCol % range.colRange().width();
        long finalCol;
        if (normalizedCol < 0) {
            finalCol = range.colRange().max() + newCol + 1;
        } else {
            finalCol = newCol + range.colRange().min();
        }

        var normalizedRow = row() - range.rowRange().min();
        var newRow = normalizedRow % range.rowRange().width();
        long finalRow;
        if (normalizedRow < 0) {
            finalRow = range.rowRange().max() + newRow + 1;
        } else {
            finalRow = newRow + range.rowRange().min();
        }

        return new Point(finalRow, finalCol);
    }

    public long manhattanDistance(Point other) {
        return Math.abs(this.col() - other.col())
                + Math.abs(this.row() - other.row());
    }

    public Iterable<Point> atManhattanDistance(long distance) {
        return () -> new Iterator<Point>() {
            private enum Quadrant {
                NE,
                SE,
                SW,
                NW;
            }

            long currCol = 0;
            long currRow = distance;
            Quadrant quadrant = Quadrant.NE;

            @Override
            public boolean hasNext() {
                return quadrant != null;
            }

            @Override
            public Point next() {
                var ret = Point.this.add(new Point(currRow, currCol));
                switch (quadrant) {
                    case NE:
                        currCol++;
                        currRow--;
                        if (currRow == 0) {
                            quadrant = Quadrant.SE;
                        }
                        break;
                    case SE:
                        currCol--;
                        currRow--;
                        if (currCol == 0) {
                            quadrant = Quadrant.SW;
                        }
                        break;

                    case SW:
                        currCol--;
                        currRow++;
                        if (currRow == 0) {
                            quadrant = Quadrant.NW;
                        }
                        break;

                    case NW:
                        currCol++;
                        currRow++;
                        if (currCol == 0) {
                            quadrant = null;
                        }
                        break;
                    case null:
                        throw new AssertionError();
                }
                return ret;
            }
        };
    }

    public Iterable<Point> withinManhattanDistance(int distance) {
        var ret = new IteratorChain<Point>();
        for (int i = 1; i <= distance; i++) {
            ret.addIterator(atManhattanDistance(i).iterator());
        }
        return () -> ret;
    }
}
