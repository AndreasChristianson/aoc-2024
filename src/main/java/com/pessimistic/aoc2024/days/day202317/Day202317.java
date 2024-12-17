package com.pessimistic.aoc2024.days.day202317;

import com.pessimistic.aoc2024.twoDimensional.Direction;
import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.twoDimensional.Point;
import com.pessimistic.aoc2024.util.FileUtils;

import java.util.List;
import java.util.function.Predicate;

public class Day202317 {
    private Day202317() {
    }

    private static Grid<HeatLoss, Integer> readFile(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        return Grid.of(lines, HeatLoss::fromChar);
    }

    public static long star1(String fileName) {
        var grid = readFile(fileName);
        var start = Point.of(0, 0);
        var finish = Point.of(
                grid.getRange().rowRange().max(),
                grid.getRange().colRange().max()
        );
//        var distances = grid.bfs(
//                start,
//                HeatLoss::value,
//                Direction.cardinalDirections(),
//                maxInOneDirection(3)
//        );
//        return distances.get(finish);
        return -1;
    }

    private static Predicate<List<Point>> maxInOneDirection(int maxAllowed) {
        return list -> {
            Direction lastDirection = null;
            var lastDirectionCount = 0;
            for (int i = list.size() - 1; i >= 1; i--) {
                for (Direction direction : Direction.cardinalDirections()) {
                    if (list.get(i).add(direction.getDelta()).equals(list.get(i - 1))) {
                        if (direction.equals(lastDirection)) {
                            lastDirectionCount++;
                            if (lastDirectionCount > maxAllowed) {
                                return false;
                            }
                        } else {
                            lastDirection = direction;
                            lastDirectionCount = 1;
                        }
                    }
                }
            }
            return true;
        };
    }

    public static long star2(String fileName) {
        return readFile(fileName)
                .getRange().rowRange().max();
    }
}
