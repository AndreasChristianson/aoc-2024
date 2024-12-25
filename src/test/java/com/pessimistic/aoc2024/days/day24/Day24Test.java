package com.pessimistic.aoc2024.days.day24;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day24Test {

    @Test
    void star1Example() {
        var result = Day24.star1("example/day-24.txt");
        assertThat(result).isEqualTo(2024);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day24.star1("actual/day-24.txt");
        assertThat(result).isEqualTo(61495910098126L);
    }

    @Test
    void star2Debug() {
        var x = 17_592_186_044_415L; // 2^44-1
        var y = 1L;

        var result = Day24.star2("actual/day-24.txt", x, y);
        var result2 = Day24.star2("actual/day-24.txt", y, x);

        assertThat(result).isEqualTo(17_592_186_044_416L); // no swaps 11111110000000000000000011111111111000000000
        assertThat(result2).isEqualTo(17_592_186_044_416L);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Stream.of("gdd", "z05", "cwt", "z09", "css", "jmv", "pqt", "z37")
                .sorted()
                .collect(Collectors.joining(","));
        assertThat(result).isEqualTo("css,cwt,gdd,jmv,pqt,z05,z09,z37");
    }
}