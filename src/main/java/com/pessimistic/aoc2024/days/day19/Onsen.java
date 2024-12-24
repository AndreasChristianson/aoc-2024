package com.pessimistic.aoc2024.days.day19;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Onsen {
    private final Set<Towel> towels;
    private final int maxTowelLength;
    private final Map<Towel, Long> knownPatterns = new HashMap<>();

    public Onsen(Set<Towel> towels) {
        this.towels = towels;
        maxTowelLength = towels.stream()
                .mapToInt(Towel::colorCount)
                .max()
                .orElseThrow();
    }

    public boolean canMake(Towel pattern) {
        return permutationCount(pattern) > 0;
    }

    public long permutationCount(Towel pattern) {
        var ret = 0L;
        ret += towels.contains(pattern) ? 1 : 0;
        var biggest = Math.min(pattern.colorCount(), maxTowelLength + 1);
        for (int i = 1; i < biggest; i++) {
            var head = new Towel(pattern.colors().subList(0, i));
            if (!towels.contains(head)) {
                continue;
            }
            var tail = new Towel(pattern.colors().subList(i, pattern.colors().size()));
            var memoized = knownPatterns.get(tail);
            if (memoized != null) {
                ret += memoized;
            } else {
                var calculated = permutationCount(tail);
                knownPatterns.put(tail, calculated);
                ret += calculated;
            }
        }
        return ret;
    }
}
