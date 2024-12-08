package com.pessimistic.aoc2024.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.LongStream;

class PatternUtilsTest {

    @Test
    void isSameSignOrZero_true() {
        Assertions.assertThat(PatternUtils.isSameSignOrZero(List.of(1L, 2L, 3L, 4L)))
                .isTrue();
        Assertions.assertThat(PatternUtils.isSameSignOrZero(List.of()))
                .isTrue();
        Assertions.assertThat(PatternUtils.isSameSignOrZero(List.of(1L, 0L, 1L, 0L, 1L)))
                .isTrue();
        Assertions.assertThat(PatternUtils.isSameSignOrZero(List.of(-111L, -11L, -1L, 0L)))
                .isTrue();
        Assertions.assertThat(PatternUtils.isSameSignOrZero(List.of(0L, 0L)))
                .isTrue();
    }

    @Test
    void isSameSignOrZero_false() {
        Assertions.assertThat(PatternUtils.isSameSignOrZero(List.of(1L, -2L, 3L, 4L)))
                .isFalse();
        Assertions.assertThat(PatternUtils.isSameSignOrZero(List.of(1L, 0L, -1L)))
                .isFalse();
    }

    @Test
    void deltas() {
        Assertions.assertThat(PatternUtils.deltas(LongStream.of(1L, 0L, -1L)))
                .isEqualTo(List.of(-1, -1));
        Assertions.assertThat(PatternUtils.deltas(LongStream.of(1L, 2L, 3L, 4L, 5L)))
                .isEqualTo(List.of(1, 1, 1, 1));
        Assertions.assertThat(PatternUtils.deltas(LongStream.of(10L, 20L, 30L, 20L, 10L)))
                .isEqualTo(List.of(10, 10, -10, -10));
    }
}