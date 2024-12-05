package com.pessimistic.aoc2024.day2;

import com.pessimistic.aoc2024.util.CombinatoricsUtils;
import com.pessimistic.aoc2024.util.FileUtils;
import com.pessimistic.aoc2024.util.PatternUtils;
import com.pessimistic.aoc2024.util.TextUtils;
import org.apache.commons.numbers.combinatorics.Combinations;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Day2 {
    private Day2() {
    }

    private static Stream<IntStream> readFile(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        return lines.stream()
                .map(TextUtils::toIntStream);
    }

    public static long safeCount(String fileName) {
        return readFile(fileName)
                .map(PatternUtils::deltas)
                .filter(isMagnitudeSmallerThan(4))
                .filter(isMagnitudeLargerThan(0))
                .filter(PatternUtils::isSameSignOrZero)
                .count();
    }

    private static Predicate<List<Integer>> isMagnitudeSmallerThan(int max) {
        return ints -> ints.stream().allMatch(i -> Math.abs(i) < max);
    }

    private static Predicate<List<Integer>> isMagnitudeLargerThan(int min) {
        return ints -> ints.stream().allMatch(i -> Math.abs(i) > min);
    }

    public static long safeCountWithDamper(String fileName) {
        return readFile(fileName)
                .map(IntStream::boxed)
                .map(CombinatoricsUtils.pickLessThanTotal(1))
                .filter(intStreamStream -> intStreamStream
                        .map(PatternUtils::deltas)
                        .filter(isMagnitudeSmallerThan(4))
                        .filter(isMagnitudeLargerThan(0))
                        .anyMatch(PatternUtils::isSameSignOrZero))
                .count();
    }

    private static Stream<IntStream> damper(IntStream intStream) {
        var asList = intStream.boxed().toList();
        return StreamSupport.stream(Combinations.of(asList.size(), asList.size() - 1).spliterator(), false)
                .map(indices -> Arrays.stream(indices).map(asList::get));
    }
}
