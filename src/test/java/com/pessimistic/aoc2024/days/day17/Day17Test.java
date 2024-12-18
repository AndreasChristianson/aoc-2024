package com.pessimistic.aoc2024.days.day17;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day17Test {

    @Test
    void star1Example1() {
        var result = Day17.star1("example/day-17.txt");
        assertThat(result).isEqualTo("4,6,3,5,6,3,5,2,1,0");
    }

    @Test
    void star1Example0() {
        assertThat(Day17.star1("example/day-17.2.txt")).isEqualTo("0,1,2");
        assertThat(Day17.star1("example/day-17.3.txt")).isEqualTo("4,2,5,6,7,7,7,7,3,1,0");
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day17.star1("actual/day-17.txt");
        assertThat(result).isEqualTo("2,3,6,2,1,6,1,2,1");
    }


    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day17.star2("actual/day-17.txt");
        assertThat(result).isEqualTo(90938893811949L);
    }
}