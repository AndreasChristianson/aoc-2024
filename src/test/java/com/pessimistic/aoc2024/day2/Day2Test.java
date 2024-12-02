package com.pessimistic.aoc2024.day2;

import com.pessimistic.aoc2024.day1.Day1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {

    @Test
    void star1Example() {
        var result = Day2.doTheThing("example/day-2.txt");
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day2.doTheThing("example/day-2.txt");
        assertThat(result).isEqualTo(1);
    }

    @Test
    void star2Example() {
        var result = Day2.doTheOtherThing("example/day-2.txt");
        assertThat(result).isEqualTo(1);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day2.doTheOtherThing("example/day-2.txt");
        assertThat(result).isEqualTo(1);
    }
}