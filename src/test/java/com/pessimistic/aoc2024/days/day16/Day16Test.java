package com.pessimistic.aoc2024.days.day16;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day16Test {

    @Test
    void star1Example() {
        var result = Day16.star1("example/day-16.txt");
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day16.star1("actual/day-16.txt");
        assertThat(result).isEqualTo(0);
    }

    @Test
    void star2Example() {
        var result = Day16.star2("example/day-16.txt");
        assertThat(result).isEqualTo(0);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day16.star2("actual/day-16.txt");
        assertThat(result).isEqualTo(0);
    }
}