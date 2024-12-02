package com.pessimistic.aoc2024.day2;

import com.pessimistic.aoc2024.FileUtils;
import com.pessimistic.aoc2024.TextUtils;
import org.apache.commons.numbers.combinatorics.Combinations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
                .map(Day2::deltas)
                .filter(isMagnitudeSmallerThan(4))
                .filter(isMagnitudeLargerThan(0))
                .filter(Day2::isMonotonic)
                .count();
    }

    private static Predicate<List<Integer>> isMagnitudeSmallerThan(int max) {
        return ints -> ints.stream().allMatch(i -> Math.abs(i)<max);
    }

    private static Predicate<List<Integer>> isMagnitudeLargerThan(int max) {
        return ints -> ints.stream().allMatch(i -> Math.abs(i)>0);
    }

    private static boolean isMonotonic(List<Integer> ints) {
        var lastSign = 0F;
        for (var i : ints) {
            var currentSign = Math.signum(i);
            if (lastSign != 0 && currentSign != lastSign) {
                return false;
            }
            lastSign = currentSign;
        }
        return true;
    }

    private static List<Integer> deltas(IntStream intStream) {
        var asList = intStream.boxed().toList();
        if (asList.size() < 2) {
            return Collections.emptyList();
        }
        var ret = new ArrayList<Integer>();
        for (int i = 1; i < asList.size(); i++) {
            ret.add(asList.get(i - 1) - asList.get(i));
        }
        return ret;
    }

    public static long safeCountWithDamper(String fileName) {
        return readFile(fileName)
                .map(Day2::damper)
                .filter(intStreamStream -> intStreamStream
                        .map(Day2::deltas)
                        .filter(isMagnitudeSmallerThan(4))
                        .filter(isMagnitudeLargerThan(0))
                        .anyMatch(Day2::isMonotonic))
                .count();
    }

    private static Stream<IntStream> damper(IntStream intStream) {
        var asList = intStream.boxed().toList();
        return StreamSupport.stream(Combinations.of(asList.size(),asList.size()-1).spliterator(),false)
                .map(indices -> Arrays.stream(indices).map(asList::get));
    }
}
