package com.pessimistic.aoc2024.days.day9;

import com.pessimistic.aoc2024.util.FileUtils;


public class Day9 {
    private Day9() {
    }

    public static long star1(String fileName) {
        var line = FileUtils.readWholeTestFile(fileName);
        var fileSystem = FileSystem.parse(line);
        fileSystem.defragment();
        return fileSystem.checksum();
    }


    public static long star2(String fileName) {
        var line = FileUtils.readWholeTestFile(fileName);
        var fileSystem = FileSystem.parse(line);
        fileSystem.defragmentFiles();
        return fileSystem.checksum();
    }
}
