package com.pessimistic.aoc2024.days.day21;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day21Test {

    @Test
    void star1Example() {
        var result = Day21.star2(2,"example/day-21.txt");
        assertThat(result).isEqualTo(126384);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day21.star2(2,"actual/day-21.txt");
        assertThat(result).isEqualTo(246990);
    }

    @Test
    void star2Example() {
        var result = Day21.star2(25,"example/day-21.txt");
        assertThat(result).isEqualTo(154115708116294L);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day21.star2(25,"actual/day-21.txt"); // 3758392 3883960 4006772 4016396
        assertThat(result).isEqualTo(306335137543664L);
    }
}