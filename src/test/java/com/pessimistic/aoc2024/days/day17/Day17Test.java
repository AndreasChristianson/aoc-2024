package com.pessimistic.aoc2024.days.day17;

import com.pessimistic.aoc2024.days.day17.Day17;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day17Test {

    @Test
    void star1Example1() {
        var result = Day17.star1("example/day-17.txt");
        assertThat(result).isEqualTo(7036);
    }
    @Test
    void star1Example0() {
        assertThat(Day17.star1("example/day-17.2.txt")).isEqualTo("012");
        assertThat(Day17.star1("example/day-17.3.txt")).isEqualTo("42567777310");
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day17.star1("actual/day-17.txt");
        assertThat(result).isEqualTo(82460);
    }

    @Test
    void star2Example1() {
        var result = Day17.star2("example/day-17.txt");
        assertThat(result).isEqualTo(45);
    }


    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day17.star2("actual/day-17.txt");
        assertThat(result).isEqualTo(590);
    }
}