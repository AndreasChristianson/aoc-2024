package com.pessimistic.aoc2024.twoDimensional;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PointTest {

    @Test
    void wrap() {
        assertThat(Point.of(10, 9)
                .wrap(Range2D.of(0, 5, 0, 5)))
                .isEqualTo(Point.of(4, 3));
        assertThat(Point.of(10, 10)
                .wrap(Range2D.of(0, 5, 0, 5)))
                .isEqualTo(Point.of(4, 4));
        assertThat(Point.of(3, 3)
                .wrap(Range2D.of(0, 5, 0, 5)))
                .isEqualTo(Point.of(3, 3));
    }

    @Test
    void wrapNegative() {
        assertThat(Point.of(-2, -2)
                .wrap(Range2D.of(0, 3, 0, 3)))
                .isEqualTo(Point.of(2, 2));

        assertThat(Point.of(-6, -6)
                .wrap(Range2D.of(0, 3, 0, 3)))
                .isEqualTo(Point.of(2, 2));
    }

    @Test
    void wrapNegativeWithOffset() {
        assertThat(Point.of(2, 2)
                .wrap(Range2D.of(5, 9, 5, 9)))
                .isEqualTo(Point.of(7, 7));
    }

    @Test
    void wrapWithOffset() {
        assertThat(Point.of(9, 9)
                .wrap(Range2D.of(3, 5, 3, 5)))
                .isEqualTo(Point.of(3, 3));

        assertThat(Point.of(100, 999)
                .wrap(Range2D.of(40, 49, 33, 65)))
                .isEqualTo(Point.of(40 + 100 % 10, 33 + 999 % 33));
    }

    @Test
    void atManhattanDistance() {
        assertThat(Point.of(0, 0)
                .atManhattanDistance(1))
                .hasSize(4);

        assertThat(Point.of(0, 0)
                .atManhattanDistance(2))
                .hasSize(8);

        assertThat(Point.of(0, 0)
                .atManhattanDistance(3))
                .containsExactly(
                        Point.of(3, 0),
                        Point.of(2, 1),
                        Point.of(1, 2),

                        Point.of(0, 3),
                        Point.of(-1, 2),
                        Point.of(-2, 1),

                        Point.of(-3, 0),
                        Point.of(-2, -1),
                        Point.of(-1, -2),

                        Point.of(0, -3),
                        Point.of(1, -2),
                        Point.of(2, -1)
                );
    }

    @Test
    void withinManhattanDistance() {
        assertThat(Point.of(0, 0)
                .withinManhattanDistance(1))
                .hasSize(4);

        assertThat(Point.of(0, 0)
                .withinManhattanDistance(2))
                .hasSize(12);

        assertThat(Point.of(0, 0)
                .withinManhattanDistance(3))
                .hasSize(24);
    }
}