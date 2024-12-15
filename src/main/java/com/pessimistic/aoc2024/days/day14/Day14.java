package com.pessimistic.aoc2024.days.day14;

import com.pessimistic.aoc2024.numbers.Range;
import com.pessimistic.aoc2024.twoDimensional.Range2D;
import com.pessimistic.aoc2024.util.FileUtils;

public class Day14 {
    private Day14() {
    }

    public static long star1(String fileName, int height, int width, int rounds, boolean print) {
        var lines = FileUtils.readTestFile(fileName);
        var robots = lines.stream()
                .filter(line -> !line.isEmpty())
                .map(Robot::fromString)
                .toList();
        var range = new Range2D(
                new Range(0, width - 1),
                new Range(0, height - 1)
        );
        var bathroomSecurity = new BathroomSecurity(robots, range);
        System.out.println(bathroomSecurity);
        for (int i = 0; i < rounds; i++) {
            if (print) {
                System.out.println(i);
                System.out.println(bathroomSecurity);
            }

            bathroomSecurity.increment();
        }
        System.out.println(bathroomSecurity);
        return bathroomSecurity.getSafetyFactor();
    }
    //((i-81)%101==0 && (i-133)%103==0)
    // 81, 182, 283
    // vertical
    // 133, 236, 339
    // horizontal

    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        return -1;
    }
}
