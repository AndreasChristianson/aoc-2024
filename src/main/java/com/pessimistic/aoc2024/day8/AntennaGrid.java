package com.pessimistic.aoc2024.day8;

import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.twoDimensional.Line;
import com.pessimistic.aoc2024.twoDimensional.Point;
import com.pessimistic.aoc2024.twoDimensional.Range2D;
import com.pessimistic.aoc2024.util.CombinatoricsUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class AntennaGrid extends Grid<Antenna, String> {


    protected AntennaGrid(Map<Point, Antenna> itemsByPoint, Range2D range) {
        super(itemsByPoint, range);
    }

    public static AntennaGrid toAntennaGrid(List<String> lines, Function<Character, Antenna> charClassifier) {
        var itemsMap = classifyToMap(indexChars(lines), charClassifier);
        return new AntennaGrid(itemsMap, collectRange(lines));
    }

    public Stream<Point> getAntiNodes(Antenna antenna) {
        var antennaPoints = getItemPoints(antenna);
        return CombinatoricsUtils.pick(antennaPoints, 2)
                .map(Stream::toList)
                .flatMap(pointPair -> getAntiNodes(pointPair.getFirst(), pointPair.getLast()));
    }

    public Stream<Point> getAntiNodes(Point left, Point right) {
        var offset = left.delta(right);
        var gridRange = getRange();
        return Stream.of(right.subtract(offset), left.add(offset))
                .filter(gridRange::contains);
    }

    public Stream<Line> getAntiNodeLines(Antenna antenna) {
        var antennaPoints = getItemPoints(antenna);
        return CombinatoricsUtils.pick(antennaPoints, 2)
                .map(Stream::toList)
                .map(pointPair -> new Line(pointPair.getFirst(), pointPair.getLast()));
    }
}
