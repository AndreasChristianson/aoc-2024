package com.pessimistic.aoc2024;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TextUtilsTest {

    @Test
    void splitOnWhitespace() {
        Assertions.assertThat(TextUtils.splitOnWhitespace("  \t\t\r\n 2 5  \n\n\t\t  10\thello  \r\n "))
                .hasSize(4)
                .last()
                .asString()
                .isEqualTo("hello");
    }
}