package com.pessimistic.aoc2024.days.day15;

import com.pessimistic.aoc2024.twoDimensional.Point;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WarehouseObjectTest {

    @Test
    void gps() {
        Assertions.assertThat(WarehouseObject.gps(Point.of(1, 4)))
                .isEqualTo(104);
    }
}