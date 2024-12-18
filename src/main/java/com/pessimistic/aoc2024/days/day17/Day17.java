package com.pessimistic.aoc2024.days.day17;

import com.pessimistic.aoc2024.util.FileUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day17 {
    private static final Logger logger = Logger.getLogger(Day17.class.getName());

    private Day17() {
    }

    public static String star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var device = Device.fromLines(lines);

        logger.info(device.toString());

        var output = device.run();
        return output
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    /**
     * find where the function turns true
     */
    public static long binarySearch(Predicate<Long> funct, long start, long end) {
        assert !funct.test(start);
        assert funct.test(end);
        var min = start;
        var max = end;
        while (max - min > 1) {
            var half = (max - min) / 2 + min;
            var result = funct.test(half);
            if (result) {
                max = half;
            } else {
                min = half;
            }
        }
        return max;
    }

    public static Pair<Long, Long> bandSearch(Predicate<Long> funct, long start, long end) {
        assert start < end;
        var inBand = sampleUntil(funct, start, end);
        if (funct.test(start)) {
            return Pair.of(
                    start,
                    binarySearch(funct.negate(), inBand, end)
            );
        }
        if (funct.test(end)) {
            return Pair.of(
                    binarySearch(funct, start, inBand),
                    end
            );
        }
        return Pair.of(
                binarySearch(funct, start, inBand),
                binarySearch(funct.negate(), inBand, end)
        );
    }

    private static long sampleUntil(Predicate<Long> funct, long start, long end) {
        var queue = new LinkedList<Pair<Long, Long>>();
        queue.add(Pair.of(start, end));
        while (!queue.isEmpty()) {
            var bounds = queue.remove();
            var half = bounds.getLeft() + (bounds.getRight() - bounds.getLeft()) / 2;
            if (funct.test(half)) {
                return half;
            } else if (bounds.getRight() - bounds.getLeft() > 2) {
                queue.add(Pair.of(half, bounds.getRight()));
                queue.add(Pair.of(bounds.getLeft(), half));

            }
        }
        throw new IllegalStateException("No band found in range");
    }

    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var device = Device.fromLines(lines);
        var target = device.getProgram();
        var bounds = bandSearch(l -> device.run(l).size() == target.size(), 0L, Long.MAX_VALUE);
        for (var i : IntStream.range(0, target.size()).boxed().toList().reversed()) {
            bounds = bandSearch(
                    l -> Objects.equals(device.run(l).subList(i, 16), target.subList(i, 16)),
                    bounds.getLeft(),
                    bounds.getRight()
            );
        }


        for (var i = bounds.getLeft(); i < bounds.getRight(); i++) {
            device.setInitialA(i);
            var output = device.run();
            System.out.printf("%d ->%n%s%n%s%n",
                    i,
                    output.stream().map(String::valueOf).collect(Collectors.joining()),
                    device.getProgram().stream().map(String::valueOf).collect(Collectors.joining())
            );
            if (output.equals(device.getProgram())) {
                return i;
            }

        }
        throw new RuntimeException("Error");
    }
}
