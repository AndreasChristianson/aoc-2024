package com.pessimistic.aoc2024.days.day23;

import com.pessimistic.aoc2024.days.day23.Day23;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day23Test {

    @Test
    void star1Example() {
        var result = Day23.star1("example/day-23.txt");
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day23.star1("actual/day-23.txt");
        assertThat(result).isEqualTo(0);
    }

    @Test
    void star2Example() {
        var result = Day23.star2("example/day-23.txt");
        assertThat(result).isEqualTo(0);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day23.star2("actual/day-23.txt");
        assertThat(result).isEqualTo(0);
    }
}