package com.pessimistic.aoc2024.days.day12;

import com.pessimistic.aoc2024.twoDimensional.Direction;
import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.twoDimensional.Point;
import com.pessimistic.aoc2024.twoDimensional.Range2D;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class GardenGrid extends Grid<Plant, String> {


    protected GardenGrid(Map<Point, Plant> itemsByPoint, Range2D range) {
        super(itemsByPoint, range);
    }

    public static GardenGrid fromLines(List<String> lines, Function<Character, Plant> charClassifier) {
        var itemsMap = classifyToMap(indexChars(lines), charClassifier);
        return new GardenGrid(itemsMap, collectRange(lines));
    }

    public Stream<Group> group(Iterable<Direction> adjacentDirections, BiFunction<Plant, Plant, Boolean> traverseAllowed) {
        return StreamSupport.stream(getRange().spliterator(), false)
                .filter(point -> !this.hasFlag("grouped", point))
                .map(point -> traverse(point, adjacentDirections, traverseAllowed, "grouped"))
                .filter(list -> !list.isEmpty())
                .map(Group::new);
    }

    public class Group {
        private final Set<Point> positions;
        private final Plant plantType;

        public Group(Set<Point> positions) {
            assert !positions.isEmpty();
            this.positions = positions;
            this.plantType = get(
                    positions.stream()
                            .findAny()
                            .orElseThrow()
            ).orElseThrow();
        }

        public long fenceCost() {
            return area() * perimeter();
        }

        private long perimeter() { // could be faster
            return positions.stream()
                    .flatMap(point -> StreamSupport.stream(Direction.cardinalDirections().spliterator(), false)
                            .map(Direction::getDelta)
                            .map(delta -> point.add(delta))
                    )
                    .filter(point -> !positions.contains(point))
                    .count();
        }

        private long area() {
            return positions.size();
        }

        public long bulkFenceCost() {
            return area() * sides();
        }

        private long sides() {
            return Direction.cardinalDirections()
                    .stream()
                    .mapToLong(direction -> {
                        var edges = positions.stream()
                                .map(point -> point.add(direction.getDelta()))
                                .filter(point -> !positions.contains(point))
                                .collect(Collectors.toSet());
                        var relatedEdges = 0;
                        for (var edge : edges) {
                            if(edges.contains(edge.add(direction.rotateClockwise90().getDelta()))){
                                relatedEdges++;
                            }
                        }
                        return edges.size()-relatedEdges;
                    })
                    .sum();
        }
    }
}
