package com.pessimistic.aoc2024.days.day3;

import com.pessimistic.aoc2024.days.day3.Day3;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day3Test {

    @Test
    void star1Example() {
        var result = Day3.star1("example/day-3.1.txt");
        assertThat(result).isEqualTo(161);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day3.star1("actual/day-3.txt");
        assertThat(result).isEqualTo(183788984);
    }

    @Test
    void star2Example() {
        var result = Day3.star2("example/day-3.2.txt");
        assertThat(result).isEqualTo(48);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day3.star2("actual/day-3.txt");
        assertThat(result).isEqualTo(62098619);
    }
}