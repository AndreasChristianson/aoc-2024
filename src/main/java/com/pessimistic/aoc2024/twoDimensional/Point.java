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
}
