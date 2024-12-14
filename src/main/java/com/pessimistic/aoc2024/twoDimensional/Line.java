package com.pessimistic.aoc2024.twoDimensional;

import com.pessimistic.aoc2024.util.FloatUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
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
     */
    public Point getNthPoint(int n) {
        return originPoint.add(delta().scale(n));
    }

    /**
     * assuming this line is composed of two wave crest points, deduce all other wave crest points in a range
     */
    public Stream<Point> propagateWaveCrestsWithinRange(Range2D range) {
        var collectedPoints = new ArrayList<Point>();
        collectedPoints.add(originPoint);
        collectedPoints.add(unitPoint);
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


    public double slope() {
        return (originPoint.col() - unitPoint.col()) / (double) (originPoint.row() - unitPoint.row());
    }

    public Optional<Long> hopsToIntersection(Point target) {
        var diff = target.subtract(originPoint);
        var numRowHops = diff.row() / delta().row();
        var numColHops = diff.col() / delta().col();
        if (numRowHops != numColHops) {
            return Optional.empty();
        }
        if (originPoint.add(delta().scale(numRowHops)).equals(target)) {
            return Optional.of(Math.abs(numRowHops));
        }
        return Optional.empty();
    }

    public boolean isParallel(Line other) {
        return FloatUtils.equalFloats(slope(), other.slope());
    }

    public Optional<Point> intersect(Line other) {
        if (isParallel(other)) {
            return Optional.empty();
        }

        var x1 = BigDecimal.valueOf(originPoint.row());
        var y1 = BigDecimal.valueOf(originPoint.col());
        var x2 = BigDecimal.valueOf(unitPoint.row());
        var y2 = BigDecimal.valueOf(unitPoint.col());
        var x3 = BigDecimal.valueOf(other.originPoint.row());
        var y3 = BigDecimal.valueOf(other.originPoint.col());
        var x4 = BigDecimal.valueOf(other.unitPoint.row());
        var y4 = BigDecimal.valueOf(other.unitPoint.col());
        //https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection#Given_two_points_on_each_line

        var denom = ((x1.subtract(x2)).multiply(y3.subtract(y4))).subtract((y1.subtract(y2)).multiply(x3.subtract(x4)));
        var xNumer = ((x1.multiply(y2)).subtract(y1.multiply(x2))).multiply(x3.subtract(x4))
                .subtract(((x3.multiply(y4)).subtract(y3.multiply(x4))).multiply(x1.subtract(x2)));
        var yNumer = ((x1.multiply(y2)).subtract(y1.multiply(x2))).multiply(y3.subtract(y4))
                .subtract(((x3.multiply(y4)).subtract(y3.multiply(x4))).multiply(y1.subtract(y2)));

        var x = xNumer.divideToIntegralValue(denom);
        var y = yNumer.divideToIntegralValue(denom);
        return Optional.of(
                new Point(
                        x.longValueExact(),
                        y.longValueExact()
                ));
    }
}
