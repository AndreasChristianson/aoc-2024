package com.pessimistic.aoc2024.days.day18;

import com.pessimistic.aoc2024.twoDimensional.Range2D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day18Test {

    @Test
    void star1Example1() {
        var result = Day18.star1(
                "example/day-18.txt",
                Range2D.of(0, 6, 0, 6),
                12
        );
        assertThat(result).isEqualTo(22);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day18.star1(
                "actual/day-18.txt",
                Range2D.of(0, 70, 0, 70),
                1024);
        assertThat(result).isEqualTo(286);
    }

    @Test
    void star2Example1() {
        var result = Day18.star2(
                "example/day-18.txt",
                Range2D.of(0, 6, 0, 6)
        );
        assertThat(result).isEqualTo("6,1");
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day18.star2(
                "actual/day-18.txt",
                Range2D.of(0, 70, 0, 70)
        );
        assertThat(result).isEqualTo("20,64");
    }
}