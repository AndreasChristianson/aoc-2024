package com.pessimistic.aoc2024.days.day11;

import com.pessimistic.aoc2024.util.Memoization;
import com.pessimistic.aoc2024.util.NumberUtils;

import java.util.List;
import java.util.function.Function;

public record Stone(long value) {
    private final static Function<Stone, List<Stone>> memoizedBlink = Memoization.memoize(Stone::blink_internal);

    private static List<Stone> blink_internal(Stone stone) {
        if (stone.value == 0) {
            return List.of(new Stone(1));
        }
        var asString = String.valueOf(stone.value);
        if (NumberUtils.isEven(asString.length())) {
            return List.of(
                    new Stone(Long.parseLong(asString.substring(0, asString.length() / 2))),
                    new Stone(Long.parseLong(asString.substring(asString.length() / 2)))
            );
        }
        return List.of(new Stone(stone.value * 2024));
    }

    public List<Stone> blink() {
        return memoizedBlink.apply(this);
    }
}
