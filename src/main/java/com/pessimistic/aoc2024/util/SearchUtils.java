package com.pessimistic.aoc2024.util;

import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;
import java.util.function.Predicate;

public class SearchUtils {
    private SearchUtils() {
    }

    /**
     * find where the function turns true
     */
    public static long binarySearch(Predicate<Long> funct, long start, long end) {
        assert !funct.test(start);
        assert funct.test(end);
        var min = start;
        var max = end;
        while (max - min > 1) {
            var half = (max - min) / 2 + min;
            var result = funct.test(half);
            if (result) {
                max = half;
            } else {
                min = half;
            }
        }
        return max;
    }

    public static Pair<Long, Long> bandSearch(Predicate<Long> funct, long start, long end) {
        assert start < end;
        var inBand = sampleUntil(funct, start, end);
        if (funct.test(start)) {
            return Pair.of(
                    start,
                    binarySearch(funct.negate(), inBand, end)
            );
        }
        if (funct.test(end)) {
            return Pair.of(
                    binarySearch(funct, start, inBand),
                    end
            );
        }
        return Pair.of(
                binarySearch(funct, start, inBand),
                binarySearch(funct.negate(), inBand, end)
        );
    }

    private static long sampleUntil(Predicate<Long> funct, long start, long end) {
        var queue = new LinkedList<Pair<Long, Long>>();
        queue.add(Pair.of(start, end));
        while (!queue.isEmpty()) {
            var bounds = queue.remove();
            var half = bounds.getLeft() + (bounds.getRight() - bounds.getLeft()) / 2;
            if (funct.test(half)) {
                return half;
            } else if (bounds.getRight() - bounds.getLeft() > 2) {
                queue.add(Pair.of(half, bounds.getRight()));
                queue.add(Pair.of(bounds.getLeft(), half));

            }
        }
        throw new IllegalStateException("No band found in range");
    }
}
