package com.pessimistic.aoc2024.days.day24;

import com.pessimistic.aoc2024.util.FileUtils;

import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Day24 {
    private static final Logger logger = Logger.getLogger(Day24.class.getName());

    private Day24() {
    }

    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var split = lines.indexOf("");
        var input = lines.subList(0, split)
                .stream()
                .map(line -> line.split(":"))
                .collect(Collectors.toMap(item -> item[0], item -> item[1].trim().equals("1")));
        var components = lines.subList(split + 1, lines.size())
                .stream()
                .map(Component::fromLine)
                .toList();
        var wireSystem = new WireSystem(components);
        wireSystem.propagate(input);
        return wireSystem.readZs();
    }

    public static long star2(String fileName, long x, long y) {
        var lines = FileUtils.readTestFile(fileName);
        var split = lines.indexOf("");
        var components = lines.subList(split + 1, lines.size())
                .stream()
                .map(Component::fromLine)
                .toList();
        var wireSystem = new WireSystem(components);
        wireSystem.swapOutputs("gdd", "z05"); // 11111110000000000000000011111111111000000000
        wireSystem.swapOutputs("cwt", "z09"); // 11111110000000000000000100000000000000000000
        wireSystem.swapOutputs("css", "jmv"); // 11111110000000000000000000000000000000000000
        wireSystem.swapOutputs("pqt", "z37"); // 11111110000000000000000000000000000000000000
        wireSystem.renameIds();

//        wireSystem.printWiringDiagram();
        wireSystem.propagate(x, y);
        return wireSystem.readZs();
    }
}
