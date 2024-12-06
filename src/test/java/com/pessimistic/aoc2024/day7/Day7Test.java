package com.pessimistic.aoc2024.day7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day7Test {

    @Test
    void star1Example() {
        var result = Day7.star1("example/day-7.txt");
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day7.star1("actual/day-7.txt");
        assertThat(result).isEqualTo(1);
    }

    @Test
    void star2Example() {
        var result = Day7.star2("example/day-7.txt");
        assertThat(result).isEqualTo(1);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day7.star2("actual/day-7.txt");
        assertThat(result).isEqualTo(1);
    }
}