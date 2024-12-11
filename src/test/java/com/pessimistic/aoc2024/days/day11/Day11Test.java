package com.pessimistic.aoc2024.days.day11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day11Test {

    @Test
    void star1Example() {
        var result = Day11.star1("example/day-11.txt");
        assertThat(result).isEqualTo(55312);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day11.star1("actual/day-11.txt");
        assertThat(result).isEqualTo(209412L);
    }

    @Test
    void star2Example() {
        var result = Day11.star2("example/day-11.txt");
        assertThat(result).isEqualTo(65601038650482L);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day11.star2("actual/day-11.txt");
        assertThat(result).isEqualTo(248967696501656L);
    }
}