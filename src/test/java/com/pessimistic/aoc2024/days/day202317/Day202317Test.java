package com.pessimistic.aoc2024.days.day202317;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Day202317Test {

    @Test
    void star1Example() {
        var result = Day202317.star1("example/day-202317.txt");
        assertThat(result).isEqualTo(102);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day202317.star1("actual/day-202317.txt");
        assertThat(result).isEqualTo(0);
    }

    @Test
    void star2Example() {
        var result = Day202317.star2("example/day-202317.txt");
        assertThat(result).isEqualTo(0);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day202317.star2("actual/day-202317.txt");
        assertThat(result).isEqualTo(0);
    }
}