package com.pessimistic.aoc2024.days.day11;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Stones {
    private final Map<Stone, Long> stoneCounts;

    @Override
    public String toString() {
        var ret = new StringBuilder();
        ret.append("%d Stones: \n".formatted(count()));
        for (var entry : stoneCounts.entrySet()) {
            ret.append("%d stones with %d written on them\n".formatted(entry.getValue(), entry.getKey().value()));
        }
        return ret.toString();
    }

    public Stones(LongStream numbers) {
//        var stoneCountMap = numbers
//                .mapToObj(Stone::new)
//                .collect(
//                        Collectors.groupingBy(
//                                Function.identity(),
//                                Collectors.counting()
//                        )
//                );
        this(numbers
                .mapToObj(Stone::new)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                ));
    }

    public Stones(Map<Stone, Long> stoneCounts) {
        this.stoneCounts = stoneCounts;
    }

    public long count() {
        return stoneCounts.values().stream()
                .mapToLong(l -> l)
                .sum();
    }

    public Stones blink() {
        var newStoneCounts = new HashMap<Stone, Long>();
        for (var entry : stoneCounts.entrySet()) {
            var stone = entry.getKey();
            var count = entry.getValue();
            var newStones = stone.blink();
            for (var newStone : newStones) {
                var currentStoneCount = newStoneCounts.getOrDefault(newStone, 0L);
                newStoneCounts.put(newStone, currentStoneCount + count);
            }
        }
        return new Stones(newStoneCounts);
    }
}
