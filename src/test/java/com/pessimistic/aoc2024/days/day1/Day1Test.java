package com.pessimistic.aoc2024.days.day1;

import com.pessimistic.aoc2024.days.day1.Day1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    @Test
    void star1Example() {
        var result = Day1.similarityStar1("example/day-1.txt");
        assertThat(result).isEqualTo(11);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day1.similarityStar1("actual/day-1.txt");
        assertThat(result).isEqualTo(2164381);
    }

    @Test
    void star2Example() {
        var result = Day1.similarityStar2("example/day-1.txt");
        assertThat(result).isEqualTo(31);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day1.similarityStar2("actual/day-1.txt");
        assertThat(result).isEqualTo(20719933);
    }
}