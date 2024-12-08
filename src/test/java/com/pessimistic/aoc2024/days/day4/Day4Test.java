package com.pessimistic.aoc2024.day4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day4Test {

    @Test
    void star1Example() {
        var result = Day4.star1("example/day-4.txt");
        assertThat(result).isEqualTo(18);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day4.star1("actual/day-4.txt");
        assertThat(result).isEqualTo(2532);
    }

    @Test
    void star2Example() {
        var result = Day4.star2("example/day-4.txt");
        assertThat(result).isEqualTo(9);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day4.star2("actual/day-4.txt");
        assertThat(result).isEqualTo(1941);
    }
}