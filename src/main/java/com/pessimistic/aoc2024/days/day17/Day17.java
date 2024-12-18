package com.pessimistic.aoc2024.days.day17;

import com.pessimistic.aoc2024.util.FileUtils;
import com.pessimistic.aoc2024.util.SearchUtils;

import java.util.Objects;
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

    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var device = Device.fromLines(lines);
        var target = device.getProgram();
        var bounds = SearchUtils.bandSearch(l -> device.run(l).size() == target.size(), 0L, Long.MAX_VALUE);
        for (var i : IntStream.range(0, target.size()).boxed().toList().reversed()) {
            bounds = SearchUtils.bandSearch(
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
