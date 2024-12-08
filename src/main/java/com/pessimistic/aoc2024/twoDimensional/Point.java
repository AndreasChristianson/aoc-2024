package com.pessimistic.aoc2024.twoDimensional;

public record Point(
        int Row,
        int Col
) {

    public static Point of(Integer row, Integer col) {
        return new Point(row, col);
    }

    public Point add (Point delta){
        return new Point(Row + delta.Row, Col + delta.Col);
    }

    public Point delta(Point other) {
        return new Point(Row - other.Row, Col - other.Col);
    }

    public Point subtract(Point delta) {
        return new Point(Row - delta.Row, Col - delta.Col);
    }
}
