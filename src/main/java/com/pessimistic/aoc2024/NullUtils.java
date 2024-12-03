package com.pessimistic.aoc2024;

public class NullUtils {
    private NullUtils() {}
    public static <T> T coalesce(T... values) {
        for (T value : values) {
            if (value != null) {
                return value;
            }
        }
        return null;
    }
}
