package com.pessimistic.aoc2024.days.day19;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day19Test {

    @Test
    void star1Example() {
        var result = Day19.star1("example/day-19.txt");
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day19.star1("actual/day-19.txt");
        assertThat(result).isEqualTo(336);
    }

    @Test
    void star2Example() {
        var result = Day19.star2("example/day-19.txt");
        assertThat(result).isEqualTo(16);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day19.star2("actual/day-19.txt");
        assertThat(result).isEqualTo(758890600222015L);
    }
}