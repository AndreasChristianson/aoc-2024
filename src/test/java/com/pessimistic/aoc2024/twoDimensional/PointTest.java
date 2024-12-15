package com.pessimistic.aoc2024.twoDimensional;

import org.junit.jupiter.api.Test;

class PointTest {

    @Test
    void wrap() {
        org.assertj.core.api.Assertions.assertThat(Point.of(10, 9)
                        .wrap(Range2D.of(0, 5, 0, 5)))
                .isEqualTo(Point.of(4, 3));
        org.assertj.core.api.Assertions.assertThat(Point.of(10, 10)
                        .wrap(Range2D.of(0, 5, 0, 5)))
                .isEqualTo(Point.of(4, 4));
        org.assertj.core.api.Assertions.assertThat(Point.of(3, 3)
                        .wrap(Range2D.of(0, 5, 0, 5)))
                .isEqualTo(Point.of(3, 3));
    }

    @Test
    void wrapNegative() {
        org.assertj.core.api.Assertions.assertThat(Point.of(-2, -2)
                        .wrap(Range2D.of(0, 3, 0, 3)))
                .isEqualTo(Point.of(2, 2));

        org.assertj.core.api.Assertions.assertThat(Point.of(-6, -6)
                        .wrap(Range2D.of(0, 3, 0, 3)))
                .isEqualTo(Point.of(2, 2));
    }

    @Test
    void wrapNegativeWithOffset() {
        org.assertj.core.api.Assertions.assertThat(Point.of(2, 2)
                        .wrap(Range2D.of(5, 9, 5, 9)))
                .isEqualTo(Point.of(7, 7));
    }

    @Test
    void wrapWithOffset() {
        org.assertj.core.api.Assertions.assertThat(Point.of(9, 9)
                        .wrap(Range2D.of(3, 5, 3, 5)))
                .isEqualTo(Point.of(3, 3));

        org.assertj.core.api.Assertions.assertThat(Point.of(100, 999)
                        .wrap(Range2D.of(40, 49, 33, 65)))
                .isEqualTo(Point.of(40 + 100 % 10, 33 + 999 % 33));
    }
}