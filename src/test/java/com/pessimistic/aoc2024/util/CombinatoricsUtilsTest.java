package com.pessimistic.aoc2024.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

class CombinatoricsUtilsTest {

    @Test
    void pick2of5() {
        var result = CombinatoricsUtils.pick(List.of("a", "b", "c", "d", "e"), 2)
                .map(Stream::toList);
        Assertions.assertThat(result)
                .hasSize(10) // 5 items, pick 2 => 5! / (2! * (5 - 2)!) -> 10
                .last()
                .isEqualTo(List.of("d", "e"));
    }

    @Test
    void pick7of7() {
        var result = CombinatoricsUtils.pick(List.of("a", "b", "c", "d", "e", "f", "g"), 7)
                .map(Stream::toList);
        Assertions.assertThat(result)
                .hasSize(1);
    }

    @Test
    void permutations2x1() {
        var result = CombinatoricsUtils.generatePermutations(List.of("a", "b"), 1);
        Assertions.assertThat(result)
                .hasSize(2)
                .containsExactly(List.of("a"), List.of("b"));
    }

    @Test
    void permutations2x2() {
        var result = CombinatoricsUtils.generatePermutations(List.of("a", "b"), 2);
        Assertions.assertThat(result)
                .hasSize(4)
                .containsExactly(
                        List.of("a", "a"),
                        List.of("a", "b"),
                        List.of("b", "a"),
                        List.of("b", "b")
                );
    }

    @Test
    void permutations2x5() {
        var result = CombinatoricsUtils.generatePermutations(List.of("a", "b"), 5);
        Assertions.assertThat(result)
                .hasSize((int) Math.pow(2, 5))
                .last()
                .isEqualTo(List.of("b","b","b","b","b"))
        ;
    }

    @Test
    void permutations6x6() {
        var result = CombinatoricsUtils.generatePermutations(List.of(1,2,3,4,5,6), 6);
        Assertions.assertThat(result)
                .hasSize((int) Math.pow(6, 6))
        ;
    }

    @Test
    void permutations3x11() {
        var result = CombinatoricsUtils.generatePermutations(List.of(1,2,3), 11);
        Assertions.assertThat(result)
                .hasSize(177147)
        ;
    }
}