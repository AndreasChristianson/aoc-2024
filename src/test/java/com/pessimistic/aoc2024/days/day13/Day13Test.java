package com.pessimistic.aoc2024.days.day13;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day13Test {

    @Test
    void star1Example() {
        var result = Day13.star1("example/day-13.txt");
        assertThat(result).isEqualTo(480);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day13.star1("actual/day-13.txt");
        assertThat(result).isEqualTo(29187);
    }

    @Test
    void star2Example() {
        var result = Day13.star2("example/day-13.txt");
        assertThat(result).isEqualTo(875318608908L);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day13.star2("actual/day-13.txt");
        assertThat(result).isEqualTo(99968222587852L);
    }
}