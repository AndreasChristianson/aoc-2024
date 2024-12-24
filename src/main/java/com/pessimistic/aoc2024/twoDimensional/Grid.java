package com.pessimistic.aoc2024.twoDimensional;

import com.pessimistic.aoc2024.numbers.Range;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Collections.disjoint;
import static java.util.Collections.emptySet;

public class Grid<K, F extends Comparable<F>> {
    private static final String PLACEHOLDER = " ";
    private final Map<Point, K> itemsByPoint;
    private final Map<K, List<Point>> pointsByItem;
    private final SortedMap<F, Set<Point>> pointsByFlag;
    private final Map<Point, Set<F>> flagsByPoint;
    private final Range2D range;

    public Grid(
            Map<Point, K> itemsByPoint,
            Range2D range
    ) {
        this.itemsByPoint = itemsByPoint;
        this.pointsByItem = itemsByPoint
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                ));
        this.range = range;
        flagsByPoint = new HashMap<>();
        pointsByFlag = new TreeMap<>();
    }

    @Override
    public String toString() {
        return toString(_ -> null);
    }

    public static <K, F extends Comparable<F>> Grid<K, F> of(List<String> lines, Function<Character, K> charClassifier) {
        var itemsMap = classifyToMap(indexChars(lines), charClassifier);
        return new Grid<>(itemsMap, collectRange(lines));
    }

    public static <K> Map<Point, K> classifyToMap(List<Map.Entry<Point, Character>> entries, Function<Character, K> charClassifier) {
        return entries.stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), charClassifier.apply(entry.getValue())))
                .filter(entry -> Objects.nonNull(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static List<Map.Entry<Point, Character>> indexChars(List<String> lines) {
        var itemsMap = new ArrayList<Map.Entry<Point, Character>>();
        for (var row = 0; row < lines.size(); row++) {
            var line = lines.get(row);
            for (var col = 0; col < line.length(); col++) {
                itemsMap.add(new AbstractMap.SimpleEntry<>(Point.of(row, col), line.charAt(col)));
            }
        }
        return itemsMap;
    }

    public static Range2D collectRange(List<String> lines) {
        var maxCol = 0;
        for (String line : lines) {
            maxCol = Math.max(maxCol, line.length() - 1);
        }
        return new Range2D(new Range(0, lines.size() - 1), new Range(0, maxCol));
    }

    /**
     * finds a point occupied by the given item
     */
    public List<Point> find(K item) {
        return pointsByItem.getOrDefault(item, Collections.emptyList());
    }

    public Range2D getRange() {
        return range;
    }

    /**
     * gets the item at a given point
     */
    public Optional<K> get(Point point) {
        return Optional.ofNullable(itemsByPoint.get(point));
    }

    public String toString(Function<Point, String> customOverlay) {
        var ret = new StringBuilder();
        for (var row : range.rowRange()) {
            for (var col : range.colRange()) {
                var customProcessing = customOverlay.apply(Point.of(row, col));
                if (Objects.nonNull(customProcessing)) {
                    assert customProcessing.length() == 1;
                    ret.append(customProcessing);
                } else {
                    ret.append(
                            Optional.ofNullable(itemsByPoint.get(Point.of(row, col)))
                                    .map(Object::toString)
                                    .orElse(PLACEHOLDER)
                    );
                }
            }
            ret.append("\n");
        }
        return ret.toString();
    }

    public String toStringWithFlag(F flag, String s) {
        return toString((point) -> hasFlag(flag, point) ? s : null);
    }

    /**
     * checks if a given point has a given flag
     */
    public boolean hasFlag(F flag, Point point) {
        return Optional.ofNullable(pointsByFlag.get(flag))
                .map(set -> set.contains(point))
                .orElse(false);
    }

    /**
     * counts the number of unique points with a given flag
     */
    public long flagCount(F flag) {
        return Optional.ofNullable(pointsByFlag.get(flag))
                .map(Set::size)
                .orElse(0);
    }

    /**
     * sets a flag for a given point
     */
    public void flag(Point point, F flag) {
        validatePoint(point);
        pointsByFlag.putIfAbsent(flag, new HashSet<>());
        pointsByFlag.get(flag).add(point);
        flagsByPoint.putIfAbsent(point, new HashSet<>());
        flagsByPoint.get(point).add(flag);
    }

    private void validatePoint(Point point) {
        assert point != null;
        assert getRange().contains(point);
    }

    /**
     * creates a deep copy of this grid
     */
    public Grid<K, F> copy() {
        return new Grid<>(new HashMap<>(itemsByPoint), range);
    }

    /**
     * mutates this grid setting one item at a point
     */
    public void set(Point point, K item) {
        validatePoint(point);
        itemsByPoint.put(point, item);
        pointsByItem.putIfAbsent(item, new ArrayList<>());
        pointsByItem.get(item).add(point);

        var previousFlags = flagsByPoint.getOrDefault(point, emptySet());
        flagsByPoint.put(point, new HashSet<>());
        previousFlags.forEach(flag -> pointsByFlag.get(flag).remove(point));
    }

    /**
     * check if a grid has a flag, anywhere
     */

    public boolean hasFlag(F flag) {
        return pointsByFlag.containsKey(flag);
    }

    public Set<Point> getFlagPoints(F flag) {
        return pointsByFlag.getOrDefault(flag, Collections.emptySet());
    }

    public Set<K> getUniqueItems() {
        return pointsByItem.keySet();
    }

    public List<Point> getItemPoints(K item) {
        return pointsByItem.get(item);
    }

    public Set<Point> traverse(Point start, Iterable<Direction> adjacentDirections, BiFunction<K, K, Boolean> traverseAllowed, F markerFlag) {
        if (hasFlag(markerFlag, start)) {
            return emptySet();
        }
        var current = get(start).orElseThrow();

        var ret = new HashSet<Point>();
        flag(start, markerFlag);
        ret.add(start);
        for (Direction direction : adjacentDirections) {
            var nextPoint = start.add(direction.getDelta());
            get(nextPoint)
                    .filter(next -> traverseAllowed.apply(next, current))
                    .ifPresent(_ -> ret.addAll(traverse(nextPoint, adjacentDirections, traverseAllowed, markerFlag)));
        }
        return ret;
    }

    protected void move(Point from, Point to) {
        remove(from).ifPresent(item -> set(to, item));
    }

    private Optional<K> remove(Point point) {
        validatePoint(point);
        var oldItem = get(point);
        itemsByPoint.remove(point);
        oldItem.map(pointsByItem::get).ifPresent(list -> list.remove(point));

        var previousFlags = flagsByPoint.getOrDefault(point, emptySet());
        flagsByPoint.put(point, new HashSet<>());
        previousFlags.forEach(flag -> pointsByFlag.get(flag).remove(point));
        return oldItem;
    }

    protected Map<Point, K> getAllItems() {
        return new HashMap<>(itemsByPoint);
    }

    public void fill(K filler) {
        for (var point : getRange()) {
            if (get(point).isEmpty()) {
                set(point, filler);
            }
        }
    }

    public List<Pair<Point, K>> getWithin(Point from, int distance) {
        var range = Range2D.of(
                from.row() - distance,
                from.row() + distance,
                from.col() - distance,
                from.col() + distance
        );
        return range.stream()
                .filter(point -> !point.equals(from))
                .map(point -> Pair.of(point, itemsByPoint.get(point)))
                .filter(pair -> pair.getRight() != null)
                .toList();
    }
    public List<Pair<Point, K>> getWithinManhattan(Point from, int distance) {
        return getWithin(from, distance)
                .stream().filter(pair -> pair.getLeft().manhattanDistance(from)<=distance)
                .toList();
    }

    public List<Pair<Point, K>> getAtManhattan(Point from, int distance) {
        return getWithin(from, distance)
                .stream().filter(pair -> pair.getLeft().manhattanDistance(from)==distance)
                .toList();
    }

}
