package com.pessimistic.aoc2024.days.day18;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day18Test {

    @Test
    void star1Example1() {
        var result = Day18.star1("example/day-18.1.txt");
        assertThat(result).isEqualTo(7036);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day18.star1("actual/day-18.txt");
        assertThat(result).isEqualTo(82460);
    }

    @Test
    void star2Example1() {
        var result = Day18.star2("example/day-18.1.txt");
        assertThat(result).isEqualTo(45);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day18.star2("actual/day-18.txt");
        assertThat(result).isEqualTo(590);
    }
}