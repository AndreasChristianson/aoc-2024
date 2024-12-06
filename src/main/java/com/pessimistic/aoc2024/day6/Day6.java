package com.pessimistic.aoc2024.day6;

import com.pessimistic.aoc2024.numbers.Range;
import com.pessimistic.aoc2024.twoDimensional.Direction;
import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.twoDimensional.Range2D;
import com.pessimistic.aoc2024.util.FileUtils;

import java.util.List;


public class Day6 {
    private Day6() {
    }

    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var initialGrid = Grid.<MapObject, String>of(lines, MapObject::charToItem);
        var grid = march(initialGrid);
        System.out.println(grid.toStringWithFlag("reached","X"));
        return grid.flagCount("reached");
    }

    private static Grid<MapObject, String> march(Grid<MapObject, String> grid) {
        var currentPosition = grid.find(MapObject.GUARD).orElseThrow();
        var direction = Direction.N;
        while (grid.getRange().contains(currentPosition)) {
            var nextPosition = currentPosition.add(direction.getDelta());
            var potentialObstacle = grid.get(nextPosition);
            if (potentialObstacle.filter(MapObject.OBSTACLE::equals).isPresent()) {
                direction = direction.rotateClockwise90();
            } else {
                grid.flag(currentPosition, "reached");
                grid.flag(currentPosition, direction.name());
                currentPosition = nextPosition;
            }
            if(grid.hasFlag(direction.name(), currentPosition)) {
                grid.flag(currentPosition, "loop!");
                break;
            }
        }
        return grid;
    }

    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var initialGrid = Grid.<MapObject, String>of(lines, MapObject::charToItem);
        var loopCount = 0;
        for (var point : initialGrid.getRange()) {
            if(point.equals(initialGrid.find(MapObject.GUARD).orElseThrow())){
                continue;
            }
            var copy = initialGrid.copy();
            copy.set(point, MapObject.OBSTACLE);
            var result = march(copy);
            if(result.hasFlag("loop!")){
                loopCount++;
            }
        }
        return loopCount;
    }

}
