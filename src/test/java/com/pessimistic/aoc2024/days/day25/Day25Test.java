package com.pessimistic.aoc2024.days.day25;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day25Test {

    @Test
    void star1Example() {
        var result = Day25.star1("example/day-25.txt");
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day25.star1("actual/day-25.txt");
        assertThat(result).isEqualTo(3196);
    }

    @Test
    void star2Example() {
        var result = Day25.star2("example/day-25.txt");
        assertThat(result).isEqualTo(0);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day25.star2("actual/day-25.txt");
        assertThat(result).isEqualTo(0);
    }
}