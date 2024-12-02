package com.pessimistic.aoc2024;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FileUtilsTest {

    @Test
    void testReadTestFileToStrings() {
        var underTest = FileUtils.readTestFile("example/day-1.test.txt");
        assertThat(underTest)
                .first()
                .isEqualTo("3   4");
    }

    @Test
    void testReadFileToStrings() {
        var underTest = FileUtils.readFile("src/test/resources/example/day-1.test.txt");
        assertThat(underTest)
                .first()
                .isEqualTo("3   4");
    }
    @Test
    void testReadFileToStringsWithProcessing() {
        var underTest = FileUtils.readFile("src/test/resources/example/day-1.test.txt", string -> string.split(" +"));
        assertThat(underTest)
                .first()
                .isEqualTo(new String[]{"3", "4"});
    }
}