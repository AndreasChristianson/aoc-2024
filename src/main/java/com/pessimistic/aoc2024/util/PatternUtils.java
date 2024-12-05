package com.pessimistic.aoc2024.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PatternUtils {
    private PatternUtils() {
    }

    /**
     * useful for checking if the difference in elements is monotonic
     *
     * @param ints
     * @return
     */
    public static boolean isSameSignOrZero(List<Integer> ints) {
        var nonZeroSigns = ints.stream()
                .map(Math::signum)
                .filter(sign -> sign != 0)
                .toList();
        return nonZeroSigns.stream()
                .allMatch(sign -> nonZeroSigns.stream()
                        .findAny()
                        .orElse(0F)
                        .equals(sign)
                );
    }

    /**
     * gets the change between elements of an int stream
     *
     * @param intStream
     * @return
     */
    public static List<Integer> deltas(IntStream intStream) {
        return deltas(intStream.boxed());
    }

    /**
     * gets the change between elements of an int stream
     *
     * @param intStream
     * @return
     */
    public static List<Integer> deltas(Stream<Integer> intStream) {
        var asList = intStream.toList();
        if (asList.size() < 2) {
            return Collections.emptyList();
        }
        var ret = new ArrayList<Integer>();
        for (int i = 1; i < asList.size(); i++) {
            ret.add(asList.get(i) - asList.get(i - 1));
        }
        return ret;
    }
}
