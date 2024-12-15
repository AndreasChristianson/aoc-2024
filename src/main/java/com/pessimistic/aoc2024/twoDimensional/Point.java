package com.pessimistic.aoc2024.twoDimensional;

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
            finalCol = range.colRange().max() + newCol+1;
        } else {
            finalCol = newCol + range.colRange().min();
        }

        var normalizedRow = row() - range.rowRange().min();
        var newRow = normalizedRow % range.rowRange().width();
        long finalRow;
        if (normalizedRow < 0) {
            finalRow = range.rowRange().max() + newRow+1;
        } else {
            finalRow = newRow + range.rowRange().min();
        }

        return new Point(finalRow, finalCol);
    }
}
