package com.pessimistic.aoc2024.days.day22;

public record FourChanges(Integer first, Integer second, Integer third, Integer fourth) {
    public boolean isValid() {
        return first!=null;
    }
}
