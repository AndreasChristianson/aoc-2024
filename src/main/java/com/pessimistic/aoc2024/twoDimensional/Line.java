package com.pessimistic.aoc2024.twoDimensional;

import java.util.ArrayList;
import java.util.stream.Stream;

public record Line(
        Point p1,
        Point p2
) {
    /**
     * assuming this line is composed of two wave crest points, deduce all other wave crest points in a range
     *
     * @param range
     * @return
     */
    public Stream<Point> propagateWaveCrestsWithinRange(Range2D range) {
        var collectedPoints = new ArrayList<Point>();
        collectedPoints.add(p1);
        collectedPoints.add(p2);
        var offset = p1.delta(p2);
        //forward
        var nextPoint = p2.subtract(offset);
        while (range.contains(nextPoint)) {
            collectedPoints.add(nextPoint);
            nextPoint = nextPoint.subtract(offset);
        }
        //backwards
        nextPoint = p1.add(offset);
        while (range.contains(nextPoint)) {
            collectedPoints.add(nextPoint);
            nextPoint = nextPoint.add(offset);
        }
        return collectedPoints.stream();
    }
}
