package com.pessimistic.aoc2024.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

class PatternUtilsTest {

    @Test
    void isSameSignOrZero_true() {
        Assertions.assertThat(PatternUtils.isSameSignOrZero(List.of(1, 2, 3, 4)))
                .isTrue();
        Assertions.assertThat(PatternUtils.isSameSignOrZero(List.of()))
                .isTrue();
        Assertions.assertThat(PatternUtils.isSameSignOrZero(List.of(1, 0, 1, 0, 1)))
                .isTrue();
        Assertions.assertThat(PatternUtils.isSameSignOrZero(List.of(-111, -11, -1, 0)))
                .isTrue();
        Assertions.assertThat(PatternUtils.isSameSignOrZero(List.of(0, 0)))
                .isTrue();
    }

    @Test
    void isSameSignOrZero_false() {
        Assertions.assertThat(PatternUtils.isSameSignOrZero(List.of(1, -2, 3, 4)))
                .isFalse();
        Assertions.assertThat(PatternUtils.isSameSignOrZero(List.of(1, 0, -1)))
                .isFalse();
    }

    @Test
    void deltas() {
        Assertions.assertThat(PatternUtils.deltas(IntStream.of(1, 0, -1)))
                .isEqualTo(List.of(-1, -1));
        Assertions.assertThat(PatternUtils.deltas(IntStream.of(1, 2, 3, 4, 5)))
                .isEqualTo(List.of(1, 1, 1, 1));
        Assertions.assertThat(PatternUtils.deltas(IntStream.of(10, 20, 30, 20, 10)))
                .isEqualTo(List.of(10, 10, -10, -10));
    }
}