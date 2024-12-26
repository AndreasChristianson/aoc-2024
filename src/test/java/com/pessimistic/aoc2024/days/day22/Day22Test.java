package com.pessimistic.aoc2024.days.day22;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

class Day22Test {

    @Test
    void star1Example() {
        var result = Day22.star1("example/day-22.txt");
        assertThat(result).isEqualTo(37327623);
    }

    @Test
    void star1Debug() {
        var gen = new Generator(123L);
        var result1 = gen.next();
        assertThat(result1).isEqualTo(15887950);
        var result2 = gen.next();
        assertThat(result2).isEqualTo(16495136);
        var result3 = gen.next();
        assertThat(result3).isEqualTo(527345);
        var result4 = gen.next();
        assertThat(result4).isEqualTo(704524);
        var result5 = gen.next();
        assertThat(result5).isEqualTo(1553684);
        var result6 = gen.next();
        assertThat(result6).isEqualTo(12683156);
        var result7 = gen.next();
        assertThat(result7).isEqualTo(11100544);
        var result8 = gen.next();
        assertThat(result8).isEqualTo(12249484);
        var result9 = gen.next();
        assertThat(result9).isEqualTo(7753432);
        var result10 = gen.next();
        assertThat(result10).isEqualTo(5908254);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void star1() {
        var result = Day22.star1("actual/day-22.txt");
        assertThat(result).isEqualTo(19458130434L);
    }

    @Test
    void star2Debug() {
        var gen = new Generator(123L);
        var result0 = gen.getBananas();
        assertThat(result0).isEqualTo(3);
        gen.next();
        var result1 = gen.getBananas();
        assertThat(result1).isEqualTo(0);
        gen.next();
        var result2 = gen.getBananas();
        assertThat(result2).isEqualTo(6);
        gen.next();
        var result3 = gen.getBananas();
        assertThat(result3).isEqualTo(5);
        gen.next();
        var result4 = gen.getBananas();
        assertThat(result4).isEqualTo(4);
        gen.next();
        var result5 = gen.getBananas();
        assertThat(result5).isEqualTo(4);
        gen.next();
        var result6 = gen.getBananas();
        assertThat(result6).isEqualTo(6);
        gen.next();
        var result7 = gen.getBananas();
        assertThat(result7).isEqualTo(4);
        gen.next();
        var result8 = gen.getBananas();
        assertThat(result8).isEqualTo(4);
        gen.next();
        var result9 = gen.getBananas();
        assertThat(result9).isEqualTo(2);

        assertThat(gen.history().get(new FourChanges(2, -2, 0, -2)))
                .isEqualTo(2);
    }


    @Test
    void star2Example() {
        var result = Day22.star2("example/day-22.1.txt");
        assertThat(result).isEqualTo(23);
    }

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    void star2() {
        var result = Day22.star2("actual/day-22.txt");
        assertThat(result).isEqualTo(2130);
    }
}