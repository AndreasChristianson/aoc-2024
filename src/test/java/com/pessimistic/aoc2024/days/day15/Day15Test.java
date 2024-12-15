package com.pessimistic.aoc2024.days.day15;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day15Test {

    @Test
    void star1Example1() {
        var result = Day15.star1("example/day-15.1.txt");
        assertThat(result).isEqualTo(2028);
    }

    @Test
    void star1Example2() {
        var result = Day15.star1("example/day-15.2.txt");
        assertThat(result).isEqualTo(10092);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day15.star1("actual/day-15.txt");
        assertThat(result).isEqualTo(1371036);
    }

    @Test
    void star2Example() {
        var result = Day15.star2("example/day-15.2.txt");
        assertThat(result).isEqualTo(9021);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day15.star2("actual/day-15.txt");
        assertThat(result).isEqualTo(1392847);
    }
}