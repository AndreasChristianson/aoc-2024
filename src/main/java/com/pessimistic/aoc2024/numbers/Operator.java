package com.pessimistic.aoc2024.numbers;

import java.util.function.BiFunction;

public enum Operator implements BiFunction<Long, Long, Long> {
    MULTIPLY((l, r) -> l * r),
    ADD(Long::sum),
    CONCAT((l, r) -> Long.parseLong(l + String.valueOf(r)));
    private final BiFunction<Long, Long, Long> applier;

    Operator(BiFunction<Long, Long, Long> applier) {
        this.applier = applier;
    }

    public Long apply(Long l, Long r) {
        return applier.apply(l, r);
    }
}
