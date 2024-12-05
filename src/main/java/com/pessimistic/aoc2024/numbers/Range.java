package com.pessimistic.aoc2024.numbers;

public record Range<T extends Number> (
        T min,
        T max
){
}
