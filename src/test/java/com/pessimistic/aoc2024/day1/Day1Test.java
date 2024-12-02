package com.pessimistic.aoc2024.day1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    @Test
    void star1Example() {
        var result = Day1.similarityStar1("day-1.test.txt");
        assertThat(result).isEqualTo(11);
    }

    @Test
    void star1() {
        var result = Day1.similarityStar1("day-1.txt");
        assertThat(result).isEqualTo(2164381);
    }

    @Test
    void star2Example() {
        var result = Day1.similarityStar2("day-1.test.txt");
        assertThat(result).isEqualTo(31);
    }

    @Test
    void star2() {
        var result = Day1.similarityStar2("day-1.txt");
        assertThat(result).isEqualTo(31);
    }
}