package com.pessimistic.aoc2024;

import org.apache.commons.numbers.combinatorics.Combinations;

import java.util.Arrays;
import java.util.List;
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
}
