package com.pessimistic.aoc2024.days.day6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day6Test {

    @Test
    void star1Example() {
        var result = Day6.star1("example/day-6.txt");
        assertThat(result).isEqualTo(41);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day6.star1("actual/day-6.txt");
        assertThat(result).isEqualTo(5145);
    }

    @Test
    void star2Example() {
        var result = Day6.star2("example/day-6.txt");
        assertThat(result).isEqualTo(6);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day6.star2("actual/day-6.txt");
        assertThat(result).isEqualTo(1523);
    }
}