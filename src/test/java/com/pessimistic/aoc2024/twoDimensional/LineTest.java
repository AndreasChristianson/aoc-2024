package com.pessimistic.aoc2024.twoDimensional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LineTest {

    @Test
    void hopsToIntersection() {
        Assertions.assertThat(new Line(new Point(0, 0), new Point(1, 1))
                        .hopsToIntersection(new Point(10, 10)))
                .get()
                .isEqualTo(10L);

        Assertions.assertThat(new Line(new Point(0, 0), new Point(1, 1))
                        .hopsToIntersection(new Point(-10, -10)))
                .get()
                .isEqualTo(10L);

        Assertions.assertThat(new Line(new Point(0, 0), new Point(-1, -1))
                        .hopsToIntersection(new Point(10, 10)))
                .get()
                .isEqualTo(10L);

        Assertions.assertThat(new Line(new Point(0, 0), new Point(1, 1))
                        .hopsToIntersection(new Point(0, 0)))
                .get()
                .isEqualTo(0L);


        Assertions.assertThat(new Line(new Point(0, 0), new Point(1, 1))
                        .hopsToIntersection(new Point(0, 1)))
                .isEmpty();


        Assertions.assertThat(new Line(new Point(0, 0), new Point(5, 5))
                        .hopsToIntersection(new Point(71, 71)))
                .isEmpty();
    }

    @Test
    void intersects() {
        Assertions.assertThat(new Line(new Point(0, 0), new Point(5, 5))
                        .intersect(new Line(new Point(0, 0), new Point(5, 5))))
                .isEmpty();

        Assertions.assertThat(new Line(new Point(0, 0), new Point(5, 5))
                        .intersect(new Line(new Point(1, 1), new Point(0, 0))))
                .isEmpty();

        Assertions.assertThat(new Line(new Point(0, 0), new Point(5, 5))
                        .intersect(new Line(new Point(5, 0), new Point(0, 5))))
                .get()
                .isEqualTo(new FloatPoint(2.5, 2.5));

        Assertions.assertThat(new Line(new Point(-60, 17), new Point(20, 21))
                        .intersect(new Line(new Point(0, -34), new Point(2, 34))))
                .get()
                .isEqualTo(new FloatPoint(1.59057, 20.07953));
    }
}