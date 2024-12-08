package com.pessimistic.aoc2024.twoDimensional;

import java.util.ArrayList;
import java.util.stream.Stream;

public record Line(
        Point originPoint,
        Point unitPoint
) {

    public Point delta() {
        return unitPoint.subtract(originPoint);
    }
    /**
     * note that n=1 always returns the unit point
     * @param n
     * @return
     */
    public Point getNthPoint(int n) {
        return originPoint.add(delta().scale(n));
    }
    /**
     * assuming this line is composed of two wave crest points, deduce all other wave crest points in a range
     *
     * @param range
     * @return
     */
    public Stream<Point> propagateWaveCrestsWithinRange(Range2D range) {
        var collectedPoints = new ArrayList<Point>();
        collectedPoints.add(originPoint);
        collectedPoints.add(unitPoint);
//        var offset = originPoint.delta(unitPoint);
        //backwards
        var distance = -1;
        while (range.contains(getNthPoint(distance))) {
            collectedPoints.add(getNthPoint(distance));
            distance--;
        }
        //forwards
        distance = 2;
        while (range.contains(getNthPoint(distance))) {
            collectedPoints.add(getNthPoint(distance));
            distance++;
        }
        return collectedPoints.stream();
    }
}
