package com.pessimistic.aoc2024.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Memoization {
    public static <F, T> Function<F, T> memoize(Function<F, T> function) {
        var memos = new ConcurrentHashMap<F, T>();
        return (F input) -> memos.computeIfAbsent(input, function);
    }
}
