package com.pessimistic.aoc2024.util;

public class FloatUtils {

    public static final double EPSILON = 0.00001D;

    private FloatUtils() {}

    public static boolean equalFloats(double left, double right, double epsilon) {
        return Math.abs(left - right) < epsilon;
    }

    public static boolean equalFloats(double left, double right) {
        return equalFloats(left, right, EPSILON);
    }
}
