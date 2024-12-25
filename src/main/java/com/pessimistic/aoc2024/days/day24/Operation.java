package com.pessimistic.aoc2024.days.day24;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum Operation implements BiFunction<Boolean,Boolean, Boolean> {
    AND,
    OR,
    XOR;

    @Override
    public Boolean apply(Boolean l, Boolean r) {
        return switch (this){
            case AND -> l && r;
            case OR -> l || r;
            case XOR -> l ^ r;
        };
    }
}
