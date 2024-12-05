package com.pessimistic.aoc2024.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public class TwoDimensionalUtils {
    /**
     * ==>>        1
     * 123         42
     * 456  ==>>   753
     * 789         86
     * ==>>        9
     * <p>
     * ==>>        a
     * ==>>        eb
     * abcd        ifc
     * efgh  ==>>  mjgd
     * ijkl        nkh
     * mnop        ol
     * ==>>        p
     *
     * @param items
     * @param <K>
     * @return
     */
    public static <K> List<List<K>> rotate45(List<List<K>> items) {
        BiFunction<Integer, Integer, Optional<K>> attemptGet = (row, col) -> {
            if (col < 0) {
                return Optional.empty();
            }
            if (row < 0) {
                return Optional.empty();
            }
            if (items.size() <= row) {
                return Optional.empty();
            }
            if (items.get(row).size() <= col) {
                return Optional.empty();
            }
            return Optional.of(items.get(row).get(col));
        };

        var ret = new ArrayList<List<K>>();
        var max = items.size() + items.getFirst().size() - 1;
        for (int i = 0; i < max; i++) {
            var line = new ArrayList<K>();
            for (int j = 0; j < max; j++) {
                attemptGet.apply(i - j, j).ifPresent(line::add);
            }
            ret.add(line);
        }
        return ret;
    }

    public static <K> List<List<K>> rotate90(List<List<K>> items) {
        var ret = new ArrayList<List<K>>();
        var max = items.size();
        for (int i = 0; i < max; i++) {
            var line = new ArrayList<K>();
            for (int j = max - 1; j >= 0; j--) {
                line.add(items.get(j).get(i));
            }
            ret.add(line);
        }
        return ret;
    }
}
