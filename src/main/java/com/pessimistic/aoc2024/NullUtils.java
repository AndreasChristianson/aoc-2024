package com.pessimistic.aoc2024;

import java.util.Arrays;
import java.util.Objects;

public class NullUtils {
    private NullUtils() {
    }

    @SafeVarargs
    public static <T> T coalesce(T... values) {
        return Arrays.stream(values)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
