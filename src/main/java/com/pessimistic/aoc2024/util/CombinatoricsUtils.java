package com.pessimistic.aoc2024.util;

import org.apache.commons.numbers.combinatorics.Combinations;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CombinatoricsUtils {
    private CombinatoricsUtils() {
    }

    public static <K> Stream<Stream<K>> pick(List<K> items, int numberToPick) {
        assert numberToPick <= items.size();
        return StreamSupport.stream(Combinations.of(items.size(), numberToPick).spliterator(), false)
                .map(indices -> Arrays.stream(indices)
                        .mapToObj(items::get)
                );
    }

    public static <K> Function<Stream<K>, Stream<Stream<K>>> pickLessThanTotal(int numberLessThanTotal) {
        return stream -> {
            var asList = stream.toList();
            return pick(asList, asList.size() - numberLessThanTotal);
        };
    }

    public static <K> Function<Stream<K>, Stream<Stream<K>>> pick(int numberToPick) {
        return stream -> {
            var asList = stream.toList();
            return pick(asList, numberToPick);
        };
    }

}
