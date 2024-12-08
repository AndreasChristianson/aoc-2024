package com.pessimistic.aoc2024.day8;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day8Test {

    @Test
    void star1Example() {
        var result = Day8.star1("example/day-8.txt");
        assertThat(result).isEqualTo(14);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day8.star1("actual/day-8.txt");
        assertThat(result).isEqualTo(332);
    }

    @Test
    void star2Example() {
        var result = Day8.star2("example/day-8.txt");
        assertThat(result).isEqualTo(34);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day8.star2("actual/day-8.txt");
        assertThat(result).isEqualTo(1174);
    }
}