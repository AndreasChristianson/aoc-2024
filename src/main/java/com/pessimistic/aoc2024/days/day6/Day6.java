package com.pessimistic.aoc2024.days.day6;

import com.pessimistic.aoc2024.twoDimensional.Direction;
import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.util.FileUtils;


public class Day6 {
    private Day6() {
    }

    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var initialGrid = Grid.<MapObject, String>of(lines, MapObject::charToItem);
        march(initialGrid);
        System.out.println(initialGrid.toStringWithFlag("reached", "X"));
        return initialGrid.flagCount("reached");
    }

    private static void march(Grid<MapObject, String> grid) {
        var currentPosition = grid.find(MapObject.GUARD).getFirst();
        var direction = Direction.N;
        while (grid.getRange().contains(currentPosition)) {
            grid.flag(currentPosition, "reached");
            var nextPosition = currentPosition.add(direction.getDelta());
            var potentialObstacle = grid.get(nextPosition);
            if (potentialObstacle.filter(MapObject.OBSTACLE::equals).isPresent()) {
                direction = direction.rotateClockwise90();
            } else {
                grid.flag(currentPosition, direction.name());
                currentPosition = nextPosition;
            }
            if (grid.hasFlag(direction.name(), currentPosition)) {
                grid.flag(currentPosition, "loop!");
                break;
            }
        }
    }

    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var initialGrid = Grid.<MapObject, String>of(lines, MapObject::charToItem);
        march(initialGrid);
        var guardStartPosition = initialGrid.find(MapObject.GUARD).getFirst();
        return initialGrid.getFlagPoints("reached").stream()
                .filter(point -> !point.equals(guardStartPosition))
                .parallel()
                .map(point -> {
                    var copy = initialGrid.copy();
                    copy.set(point, MapObject.OBSTACLE);
                    march(copy);
                    return copy;
                })
                .filter(copy -> copy.hasFlag("loop!"))
                .count();
    }
}
