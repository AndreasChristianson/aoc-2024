package com.pessimistic.aoc2024.days.day20;

import com.pessimistic.aoc2024.graph.DirectedGraph;
import com.pessimistic.aoc2024.twoDimensional.Direction;
import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.twoDimensional.Point;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RaceTrack extends DirectedGraph<Point> {
    public RaceTrack(Grid<RacetrackSquare, ?> grid) {
        var points = Stream.of(RacetrackSquare.SPACE, RacetrackSquare.END, RacetrackSquare.START)
                .map(grid::getItemPoints)
                .flatMap(Collection::stream)
                .toList();
        var nodeMap = new HashMap<Point, Node<Point>>();
        for (var from : points) {
            var neighbors = Direction.cardinalDirections().stream()
                    .map(Direction::getDelta)
                    .map(from::add)
                    .filter(point -> grid.get(point).isPresent())
                    .collect(Collectors.toMap(Function.identity(), _ -> 1L));
            nodeMap.put(from, new Node<>(from, neighbors));
        }
        super(nodeMap);
    }

    public Map<Pair<Point, Point>, Long> findCheats(int allowedCheatLength) {
        var ret = new HashMap<Pair<Point, Point>, Long>();
        for (var from : nodeMap.keySet()) {

            for (var to : from.withinManhattanDistance(allowedCheatLength)) {
                if (nodeMap.get(to) == null) {
                    continue;
                }
                var distance = from.manhattanDistance(to);
                var cheatAmount = weights.get(to).getLeft() - (weights.get(from).getLeft() + distance);
                if (cheatAmount > 0) {
                    ret.put(Pair.of(from, to), cheatAmount);
                }
            }

        }
        return ret;
    }
}
