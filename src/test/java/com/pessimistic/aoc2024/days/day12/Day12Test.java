package com.pessimistic.aoc2024.days.day12;

import com.pessimistic.aoc2024.days.day12.Day12;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day12Test {

    @Test
    void star1Example() {
        var result = Day12.star1("example/day-12.txt");
        assertThat(result).isEqualTo(1930);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day12.star1("actual/day-12.txt");
        assertThat(result).isEqualTo(1456082);
    }

    @Test
    void star2Example() {
        var result = Day12.star2("example/day-12.txt");
        assertThat(result).isEqualTo(1206);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day12.star2("actual/day-12.txt");
        assertThat(result).isEqualTo(872382);
    }
}