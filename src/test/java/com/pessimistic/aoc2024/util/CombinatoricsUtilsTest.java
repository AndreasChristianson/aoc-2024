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
}