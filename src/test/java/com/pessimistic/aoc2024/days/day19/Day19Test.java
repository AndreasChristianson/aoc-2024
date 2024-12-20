package com.pessimistic.aoc2024.days.day19;

import com.pessimistic.aoc2024.days.day19.Day19;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day19Test {

    @Test
    void star1Example() {
        var result = Day19.star1("example/day-19.txt");
        assertThat(result).isEqualTo(480);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day19.star1("actual/day-19.txt");
        assertThat(result).isEqualTo(29187);
    }

    @Test
    void star2Example() {
        var result = Day19.star2("example/day-19.txt");
        assertThat(result).isEqualTo(875318608908L);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day19.star2("actual/day-19.txt");
        assertThat(result).isEqualTo(99968222587852L);
    }
}