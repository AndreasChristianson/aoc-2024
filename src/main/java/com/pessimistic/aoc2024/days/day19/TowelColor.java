package com.pessimistic.aoc2024.days.day19;

public enum TowelColor {
    r,g,u,b,w;


    public static TowelColor fromString(String input) {
        return valueOf(input.toLowerCase());
    }
}
