package com.pessimistic.aoc2024.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TwoDimensionalTextUtilsTest {

    @Test
    void rotate45() {

        var result = TwoDimensionalTextUtils.rotate45(List.of(
                List.of(1, 2, 3, 4),
                List.of(5, 6, 7, 8),
                List.of(9, 10, 11, 12),
                List.of(13, 14, 15, 16)
        ));
        Assertions.assertThat(result)
                .hasSize(7)
                .containsExactly(
                        List.of(1),
                        List.of(5, 2),
                        List.of(9, 6, 3),
                        List.of(13, 10, 7, 4),
                        List.of(14, 11, 8),
                        List.of(15, 12),
                        List.of(16)
                );
    }

    @Test
    void rotate90() {

        var result = TwoDimensionalTextUtils.rotate90(List.of(
                List.of(1, 2, 3, 4),
                List.of(5, 6, 7, 8),
                List.of(9, 10, 11, 12),
                List.of(13, 14, 15, 16)
        ));
        Assertions.assertThat(result)
                .hasSize(4)
                .containsExactly(
                        List.of(13, 9, 5, 1),
                        List.of(14, 10, 6, 2),
                        List.of(15, 11, 7, 3),
                        List.of(16, 12, 8, 4)
                );
    }

    @Test
    void rotateEmpty() {

        var result = TwoDimensionalTextUtils.rotate90(List.of(
        ));
        Assertions.assertThat(result)
                .isEmpty();
    }
}