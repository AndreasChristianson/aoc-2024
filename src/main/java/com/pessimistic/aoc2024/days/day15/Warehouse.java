package com.pessimistic.aoc2024.days.day15;

import com.pessimistic.aoc2024.numbers.Range;
import com.pessimistic.aoc2024.twoDimensional.Direction;
import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.twoDimensional.Point;
import com.pessimistic.aoc2024.twoDimensional.Range2D;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.pessimistic.aoc2024.days.day15.WarehouseObject.*;
import static com.pessimistic.aoc2024.twoDimensional.Direction.*;

public class Warehouse extends Grid<WarehouseObject, String> {
    protected Warehouse(Map<Point, WarehouseObject> itemsByPoint, Range2D range) {
        super(itemsByPoint, range);
    }

    public static Warehouse toWarehouse(List<String> lines, Function<Character, WarehouseObject> charClassifier) {
        var itemsMap = classifyToMap(indexChars(lines), charClassifier);
        return new Warehouse(itemsMap, collectRange(lines));
    }

    public void run(String instructionsString) {
        Arrays.stream(instructionsString.split(""))
                .map(Warehouse::instructionToDirection).forEach(this::move);
    }

    private void move(Direction direction) {
        var positions = getItemPoints(ROBOT);
        assert positions.size() == 1;
        var position = positions.getFirst();
        if (canMove(position, direction)) {
            move(position, direction);
        }
    }

    private void move(Point from, Direction direction) {
        var to = from.add(direction.getDelta());
        var atNewPosition = get(to).orElse(null);
        switch (atNewPosition) {
            case null -> super.move(from, to);
            case WALL -> {
            }
            case CRATE -> {
                move(to, direction);
                super.move(from, to);
            }
            case WIDE_LEFT -> {
                switch (direction) {
                    case E, W -> move(to, direction);
                    case N, S -> {
                        move(to, direction);
                        move(to.add(E.getDelta()), direction);
                    }
                }
                super.move(from, to);
            }
            case WIDE_RIGHT -> {
                switch (direction) {
                    case E, W -> move(to, direction);
                    case N, S -> {
                        move(to, direction);
                        move(to.add(W.getDelta()), direction);
                    }
                }
                super.move(from, to);
            }
            case ROBOT -> throw new IllegalStateException("two robots!");
        }
    }

    private boolean canMove(Point from, Direction direction) {
        var to = from.add(direction.getDelta());
        var atNewPosition = get(to).orElse(null);
        return switch (atNewPosition) {
            case null -> true;
            case WALL -> false;
            case CRATE -> canMove(to, direction);
            case WIDE_LEFT -> switch (direction) {
                case E, W -> canMove(to, direction);
                case N, S -> canMove(to, direction)
                        && canMove(to.add(E.getDelta()), direction);
            };
            case WIDE_RIGHT -> switch (direction) {
                case E, W -> canMove(to, direction);
                case N, S -> canMove(to, direction)
                        && canMove(to.add(W.getDelta()), direction);
            };
            case ROBOT -> throw new IllegalStateException("two robots!");
        };
    }

    private static Direction instructionToDirection(String instruction) {
        return switch (instruction) {
            case "v" -> S;
            case "<" -> W;
            case ">" -> E;
            case "^" -> N;
            default -> throw new IllegalArgumentException("Invalid instruction: " + instruction);
        };
    }

    public Warehouse widen() {
        var ret = new HashMap<Point, WarehouseObject>();
        for (var entry : getAllItems().entrySet()) {
            var position = entry.getKey();
            var newCol = position.col() * 2;
            switch (entry.getValue()) {
                case ROBOT -> ret.put(Point.of(position.row(), newCol), ROBOT);
                case WALL -> {
                    ret.put(Point.of(position.row(), newCol), WALL);
                    ret.put(Point.of(position.row(), newCol + 1), WALL);
                }
                case CRATE -> {
                    ret.put(Point.of(position.row(), newCol), WIDE_LEFT);
                    ret.put(Point.of(position.row(), newCol + 1), WIDE_RIGHT);
                }
            }
        }

        var newRange = new Range2D(
                getRange().rowRange(),
                new Range(
                        getRange().colRange().min(),
                        getRange().colRange().max() * 2 + 1
                )
        );
        return new Warehouse(ret, newRange);
    }
}
