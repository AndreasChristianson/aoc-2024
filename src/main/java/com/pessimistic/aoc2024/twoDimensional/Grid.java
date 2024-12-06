package com.pessimistic.aoc2024.twoDimensional;

import com.pessimistic.aoc2024.numbers.Range;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;

public class Grid<K, F> {
    private static final String PLACEHOLDER = " ";
    private final Map<Point, K> items;
    private final Map<F, Set<Point>> pointsByFlag;
    private final Map<Point, Set<F>> flagsByPoint;
    private final Range2D range;

    private Grid(
            Map<Point, K> items,
            Range2D range
    ) {
        this.items = items;
        this.range = range;
        flagsByPoint = new HashMap<>();
        pointsByFlag = new HashMap<>();
    }

    @Override
    public String toString() {
        return toString(Point.of(Integer.MAX_VALUE, Integer.MAX_VALUE), "");
    }

    public static <K, F> Grid<K, F> of(Stream<Stream<K>> items) {
        var itemsMap = new HashMap<Point, K>();
        var rowNum = new AtomicInteger();
        var colNum = new AtomicInteger();
        items.forEachOrdered(row -> {
            colNum.set(0);
            row.forEachOrdered(item ->
                    itemsMap.put(
                            new Point(rowNum.get(), colNum.getAndIncrement()),
                            item
                    )
            );
            rowNum.getAndIncrement();
        });
        return new Grid<>(itemsMap, new Range2D(new Range(0, rowNum.intValue() - 1), new Range(0, colNum.intValue() - 1)));
    }

    public static <K, F> Grid<K, F> of(List<List<K>> items) {
        return of(items.stream().map(Collection::stream));
    }

    public static <K, F> Grid<K, F> of(K[][] items) {
        return of(Arrays.stream(items).map(Arrays::stream));
    }

    public static <K, F> Grid<K, F> of(List<String> lines, Function<Character, K> charClassifier) {
        var maxCol = 0;
        var itemsMap = new HashMap<Point, K>();
        for (var row = 0; row < lines.size(); row++) {
            var line = lines.get(row);
            maxCol = Math.max(maxCol, line.length() - 1);
            for (var col = 0; col < line.length(); col++) {
                var classified = charClassifier.apply(line.charAt(col));
                if (classified != null) {
                    itemsMap.put(Point.of(row, col), classified);
                }
            }
        }
        return new Grid<>(itemsMap, new Range2D(new Range(0, lines.size() - 1), new Range(0, maxCol)));
    }

    public Optional<Point> find(K item) {
        return items.entrySet().stream()
                .filter(entry -> entry.getValue().equals(item))
                .map(Map.Entry::getKey)
                .findAny();
    }

    public Range2D getRange() {
        return range;
    }

    public Optional<K> get(Point nextPosition) {
        return Optional.ofNullable(items.get(nextPosition));
    }

    public String toString(Point currentPosition, String s) {
        var ret = new StringBuilder();
        for (var row : range.rowRange()) {
            for (var col : range.colRange()) {
                if (currentPosition.equals(Point.of(row, col))) {
                    ret.append(s);
                } else {
                    ret.append(
                            Optional.ofNullable(items.get(Point.of(row, col)))
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
        var ret = new StringBuilder();
        for (var row : range.rowRange()) {
            for (var col : range.colRange()) {
                if (hasFlag(flag, Point.of(row, col))) {
                    ret.append(s);
                } else {
                    ret.append(PLACEHOLDER);
                }
            }
            ret.append("\n");
        }
        return ret.toString();
    }

    public boolean hasFlag(F flag, Point point) {
        return Optional.ofNullable(pointsByFlag.get(flag))
                .map(set -> set.contains(point))
                .orElse(false);
    }

    public long flagCount(F flag) {
        return Optional.ofNullable(pointsByFlag.get(flag))
                .map(Set::size)
                .orElse(0);
    }

    public void flag(Point point, F flag) {
        pointsByFlag.putIfAbsent(flag, new HashSet<>());
        pointsByFlag.get(flag).add(point);
        flagsByPoint.putIfAbsent(point, new HashSet<>());
        flagsByPoint.get(point).add(flag);
    }

    public Grid<K, F> copy() {
        return new Grid<>(new HashMap<>(items), range);
    }

    public void set(Point point, K item) {
        items.put(point, item);
    }

    public boolean hasFlag(F flag) {
        return pointsByFlag.containsKey(flag);
    }

    public Set<Point> getFlagPoints(F flag) {
        return pointsByFlag.getOrDefault(flag, Collections.emptySet());
    }
}
