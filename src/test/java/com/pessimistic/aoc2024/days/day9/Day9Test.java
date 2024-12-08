package com.pessimistic.aoc2024.days.day9;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day9Test {

    @Test
    void star1Example() {
        var result = Day9.star1("example/day-9.txt");
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day9.star1("actual/day-9.txt");
        assertThat(result).isEqualTo(1);
    }

    @Test
    void star2Example() {
        var result = Day9.star2("example/day-9.txt");
        assertThat(result).isEqualTo(1);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day9.star2("actual/day-9.txt");
        assertThat(result).isEqualTo(1);
    }
}