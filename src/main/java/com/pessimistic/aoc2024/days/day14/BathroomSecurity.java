package com.pessimistic.aoc2024.days.day14;

import com.pessimistic.aoc2024.twoDimensional.Point;
import com.pessimistic.aoc2024.twoDimensional.Range2D;

import java.util.List;
import java.util.stream.Collectors;

public class BathroomSecurity {
    private final List<Robot> robots;
    private final Range2D range;

    public BathroomSecurity(
            List<Robot> robots,
            Range2D range
    ) {
        this.robots = robots;
        this.range = range;
    }

    @Override
    public String toString() {
        var ret = new StringBuilder();
        for (var col : range.colRange()) {
            for (var row : range.rowRange()) {
                var count = countAtPoint(Point.of(row, col));
                if (count > 0) {
                    ret.append("% 3d".formatted(count));
                } else {
                    ret.append("  .");
                }

            }
            ret.append("\n");
        }
        return ret.toString();
    }

    private long countAtPoint(Point position) {
        return robots.stream()
                .filter(robot -> robot.getPosition().equals(position))
                .count();
    }

    public void increment() {
        for (var robot : robots) {
            robot.move(range);
        }
    }

    public long getSafetyFactor() {

        return robots.stream()
                .map(Robot::getPosition)
                .filter(position -> position.row() != range.rowRange().midPoint())
                .filter(position -> position.col() != range.colRange().midPoint())
                .collect(Collectors.groupingBy(position -> {
                    var left = position.row() < range.rowRange().midPoint();
                    var bottom = position.col() < range.colRange().midPoint();
                    if (left && bottom) {
                        return 3;
                    }
                    if (left) {
                        return 2;
                    }
                    if (bottom) {
                        return 4;
                    }
                    return 1;
                }, Collectors.counting()))
                .values().stream()
                .mapToLong(i -> i)
                .reduce(1, (l, r) -> l * r);
    }
}
