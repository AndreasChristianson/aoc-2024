package com.pessimistic.aoc2024.days.day21;

import java.util.List;
import java.util.stream.Collectors;

public record KeyPadEntry(List<KeyPadInstruction> instructions) {
    @Override
    public String toString() {
        return instructions.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}

