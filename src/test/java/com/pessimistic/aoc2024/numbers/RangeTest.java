package com.pessimistic.aoc2024.numbers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RangeTest {

    @Test
    void midPoint() {
        Assertions.assertThat(new Range(0, 4).midPoint())
                .isEqualTo(2);
        Assertions.assertThatThrownBy(() -> new Range(0, 3).midPoint());

        Assertions.assertThat(new Range(-5, 5).midPoint())
                .isEqualTo(0);
        Assertions.assertThat(new Range(100, 200).midPoint())
                .isEqualTo(150);
    }
}