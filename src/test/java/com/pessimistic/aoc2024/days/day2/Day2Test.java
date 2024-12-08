package com.pessimistic.aoc2024.days.day2;

import com.pessimistic.aoc2024.days.day2.Day2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {

    @Test
    void star1Example() {
        var result = Day2.safeCount("example/day-2.txt");
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day2.safeCount("actual/day-2.txt");
        assertThat(result).isEqualTo(332);
    }

    @Test
    void star2Example() {
        var result = Day2.safeCountWithDamper("example/day-2.txt");
        assertThat(result).isEqualTo(4);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day2.safeCountWithDamper("actual/day-2.txt");
        assertThat(result).isEqualTo(398);
    }
}