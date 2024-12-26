package com.pessimistic.aoc2024.days.day20;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day20Test {

    @Test
    void star1Example() {
        var result = Day20.star2(2, 1, "example/day-20.txt");
        assertThat(result).isEqualTo(44);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day20.star2(2, 100, "actual/day-20.txt");
        assertThat(result).isEqualTo(1406);
    }

    @Test
    void star2Example() {
        var result = Day20.star2(20, 50, "example/day-20.txt");
        assertThat(result).isEqualTo(285);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day20.star2(20, 100, "actual/day-20.txt");
        assertThat(result).isEqualTo(1006101);
    }
}