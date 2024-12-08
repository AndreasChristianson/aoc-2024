package com.pessimistic.aoc2024.day2;

import com.pessimistic.aoc2024.util.CombinatoricsUtils;
import com.pessimistic.aoc2024.util.FileUtils;
import com.pessimistic.aoc2024.util.PatternUtils;
import com.pessimistic.aoc2024.util.TextUtils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Day2 {
    private Day2() {
    }

    private static Stream<LongStream> readFile(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        return lines.stream()
                .map(TextUtils::toLongStream);
    }

    public static long safeCount(String fileName) {
        return readFile(fileName)
                .map(LongStream::boxed)
                .map(PatternUtils::deltas)
                .filter(isMagnitudeSmallerThan(4))
                .filter(isMagnitudeLargerThan(0))
                .filter(PatternUtils::isSameSignOrZero)
                .count();
    }

    private static Predicate<List<Long>> isMagnitudeSmallerThan(long max) {
        return ints -> ints.stream().allMatch(i -> Math.abs(i) < max);
    }

    private static Predicate<List<Long>> isMagnitudeLargerThan(long min) {
        return ints -> ints.stream().allMatch(i -> Math.abs(i) > min);
    }

    public static long safeCountWithDamper(String fileName) {
        return readFile(fileName)
                .map(LongStream::boxed)
                .map(CombinatoricsUtils.pickLessThanTotal(1))
                .filter(intStreamStream -> intStreamStream
                        .map(PatternUtils::deltas)
                        .filter(isMagnitudeSmallerThan(4))
                        .filter(isMagnitudeLargerThan(0))
                        .anyMatch(PatternUtils::isSameSignOrZero))
                .count();
    }
}
