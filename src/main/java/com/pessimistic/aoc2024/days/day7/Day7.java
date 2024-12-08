package com.pessimistic.aoc2024.day7;

import com.pessimistic.aoc2024.numbers.Operator;
import com.pessimistic.aoc2024.util.FileUtils;


public class Day7 {
    private Day7() {
    }

    public static long star1(String fileName) {
        return FileUtils.readTestFile(fileName).stream()
                .map(Equation::parse)
                .filter(equation -> equation.validWithOperators(Operator.ADD, Operator.MULTIPLY))
                .mapToLong(Equation::getTarget)
                .sum();
    }

    public static long star2(String fileName) {
        return FileUtils.readTestFile(fileName).stream()
                .parallel()
                .map(Equation::parse)
                .filter(equation -> equation.validWithOperators(Operator.ADD, Operator.MULTIPLY, Operator.CONCAT))
                .mapToLong(Equation::getTarget)
                .sum();
    }
}
