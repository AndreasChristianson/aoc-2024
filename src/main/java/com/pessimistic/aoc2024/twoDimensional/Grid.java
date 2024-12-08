package com.pessimistic.aoc2024.twoDimensional;

import com.pessimistic.aoc2024.numbers.Range;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Grid<K, F> {
    private static final String PLACEHOLDER = " ";
    private final Map<Point, K> itemsByPoint;
    private final Map<K, List<Point>> pointsByItem;
    private final Map<F, Set<Point>> pointsByFlag;
    private final Map<Point, Set<F>> flagsByPoint;
    private final Range2D range;

    protected Grid(
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
        pointsByFlag = new HashMap<>();
    }

    @Override
    public String toString() {
        return toString(_ -> null);
    }

    public static <K, F> Grid<K, F> of(List<String> lines, Function<Character, K> charClassifier) {
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

    public List<Point> find(K item) {
        return pointsByItem.getOrDefault(item, Collections.emptyList());
    }

    public Range2D getRange() {
        return range;
    }

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
        return new Grid<>(new HashMap<>(itemsByPoint), range);
    }

    public void set(Point point, K item) {
        itemsByPoint.put(point, item);
        pointsByItem.putIfAbsent(item, new ArrayList<>());
        pointsByItem.get(item).add(point);
    }

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
}
