package com.pessimistic.aoc2024.days.day11;

import com.pessimistic.aoc2024.util.FileUtils;
import com.pessimistic.aoc2024.util.TextUtils;


public class Day11 {
    private Day11() {
    }

    public static long star1(String fileName) {
        var numbers = TextUtils.toLongStream(FileUtils.readWholeTestFile(fileName));
        var stones = new Stones(numbers);
        for (int i = 0; i < 25; i++) {
            stones = stones.blink();
        }
        System.out.println(stones);
        return stones.count();
    }


    public static long star2(String fileName) {
        var numbers = TextUtils.toLongStream(FileUtils.readWholeTestFile(fileName));
        var stones = new Stones(numbers);
        for (int i = 0; i < 75; i++) {
            stones = stones.blink();
        }
        System.out.println(stones);
        return stones.count();
    }
}
