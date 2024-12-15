package com.pessimistic.aoc2024.days.day14;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day14Test {

    @Test
    void star11Example() {
        Day14.star1("example/day-14.1.txt", 7,11, 10,true);
    }

    @Test
    void star1Example() {
        var result = Day14.star1("example/day-14.txt", 7,11, 100,false);
        assertThat(result).isEqualTo(12);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day14.star1("actual/day-14.txt",103,101,100,false);
        assertThat(result).isEqualTo(221655456);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        Day14.star1("actual/day-14.txt",103,101,7858,false);
    }
}