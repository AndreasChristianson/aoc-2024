package com.pessimistic.aoc2024.graph;

import com.pessimistic.aoc2024.twoDimensional.Direction;
import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.twoDimensional.Point;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DirectedGraph<K> {
    private final Map<K, Node<K>> nodeMap;

    private DirectedGraph(Map<K, Node<K>> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public static <K, I, F extends Comparable<F>> DirectedGraph<K> fromGrid(Grid<I, F> grid,
                                                                            BiFunction<K, K, Optional<Long>> weightFunction,
                                                                            Function<Pair<Point, I>, List<K>> converter,
                                                                            List<I> itemTypes
    ) {

        var nodes = itemTypes.stream()
                .flatMap(item ->
                        grid.getItemPoints(item).stream()
                                .map(point -> Pair.of(point, item))
                )
                .map(pair -> {
                    var neighbors = Direction.cardinalDirections().stream()
                            .map(Direction::getDelta)
                            .map(pair.getLeft()::add)
                            .flatMap(neighbor -> grid.get(neighbor)
                                    .stream().map(neighborItem -> Pair.of(neighbor, neighborItem)))
                            .map(converter)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList());
                    var currentNodes = converter.apply(pair);
                    neighbors.addAll(currentNodes);
                    return Pair.of(currentNodes, neighbors);
                })
                .toList();
        var nodeMap = new HashMap<K, Node<K>>();
        for (Pair<List<K>, List<K>> nodeData : nodes) {
            for (var nodeItem : nodeData.getLeft()) {
                var node = nodeMap.getOrDefault(nodeItem, new Node<>(nodeItem, new HashMap<>()));
                nodeMap.put(nodeItem, node);
                for (var otherItem : nodeData.getRight()) {
                    weightFunction.apply(nodeItem, otherItem)
                            .ifPresent(weight -> node.edges.put(otherItem, weight));
                }
            }
        }
        return new DirectedGraph<>(nodeMap);
    }

    public long findMinDistance(K from, Predicate<K> until) {
        var weights = traverseFrom(from);
        return minDistance(until, weights);
    }

    private static <K> long minDistance(Predicate<K> until, HashMap<K, Pair<Long, Set<K>>> weights) {
        return weights.entrySet()
                .stream()
                .filter(entry -> until.test(entry.getKey()))
                .map(Map.Entry::getValue)
                .mapToLong(Pair::getLeft)
                .min()
                .orElseThrow(() -> new RuntimeException("No route found"));
    }

    public List<List<K>> findPaths(K from, Predicate<K> until) {
        var weights = traverseFrom(from);
        var minDistance = minDistance(until, weights);
        return weights.entrySet()
                .stream()
                .filter(entry -> until.test(entry.getKey()))
                .filter(entry -> entry.getValue().getLeft() == minDistance)
                .map(entry -> extractPaths(entry.getKey(), entry.getValue().getRight(), weights))
                .flatMap(Collection::stream)
                .toList();
    }

    private List<List<K>> extractPaths(K head, Set<K> tails, HashMap<K, Pair<Long, Set<K>>> weights) {
        var ret = new ArrayList<List<K>>();
        if (tails.isEmpty()) {
            ret.add(new ArrayList<>());
        }
        for (var tail : tails) {
            var segments = extractPaths(tail, weights.get(tail).getRight(), weights);
            segments.forEach(list -> list.add(head));
            ret.addAll(segments);
        }
        return ret;
    }


    private HashMap<K, Pair<Long, Set<K>>> traverseFrom(K from) {
        var ret = new HashMap<K, Pair<Long, Set<K>>>();
        var discovered = new LinkedList<K>();
        discovered.add(from);
        ret.put(from, Pair.of(0L, new HashSet<>()));
        while (!discovered.isEmpty()) {
            var location = discovered.poll();
            var node = nodeMap.get(location);
            var distance = ret.get(location).getLeft();
            assert node != null;
            for (var edge : node.edges().keySet()) {
                var edgeWeight = node.edges().get(edge);
                var oldWeight = ret.getOrDefault(edge, Pair.of(Long.MAX_VALUE, null)).getLeft();
                var newWeight = edgeWeight + distance;
                if (newWeight < oldWeight) {
                    var history = new HashSet<K>();
                    history.add(location);
                    ret.put(edge, Pair.of(newWeight, history));
                    discovered.add(edge);
                } else if (newWeight == oldWeight) {
                    ret.get(edge).getRight().add(location);
                }
            }
        }


        return ret;
    }

    private record Node<K>(
            K item,
            Map<K, Long> edges
    ) {
    }
}
