package com.pessimistic.aoc2024.days.day5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day5Test {

    @Test
    void star1Example() {
        var result = Day5.star1("example/day-5.txt");
        assertThat(result).isEqualTo(143);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day5.star1("actual/day-5.txt");
        assertThat(result).isEqualTo(7365);
    }

    @Test
    void star2Example() {
        var result = Day5.star2("example/day-5.txt");
        assertThat(result).isEqualTo(123);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day5.star2("actual/day-5.txt");
        assertThat(result).isEqualTo(5770);
    }
}