package com.pessimistic.aoc2024.days.day7;

import com.pessimistic.aoc2024.numbers.Operator;
import com.pessimistic.aoc2024.util.CombinatoricsUtils;
import com.pessimistic.aoc2024.util.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Equation {
    private final long target;
    private final List<Long> operands;

    private Equation(long target, List<Long> operands) {
        this.target = target;
        this.operands = new ArrayList<>(operands);
    }

    public static Equation parse(String line) {
        var parts = line.split(":");
        var target = Long.parseLong(parts[0]);
        var operands = TextUtils.toLongStream(parts[1]).boxed().toList();
        return new Equation(target, operands);
    }

    public boolean validWithOperators(Operator... operators) {
        var neededOperators = operands.size() - 1;
        var operatorCombinations = CombinatoricsUtils.generatePermutations(Arrays.asList(operators), neededOperators);
        return operatorCombinations.anyMatch(this::test);
    }

    private boolean test(List<Operator> operators) {
        var operatorsIterator = operators.iterator();
        var operandsIterator = operands.iterator();
        long accumulator = operandsIterator.next();

        while (operatorsIterator.hasNext() && operandsIterator.hasNext()) {
            var operator = operatorsIterator.next();
            var operand = operandsIterator.next();
            accumulator = operator.apply(accumulator, operand);
        }
        return accumulator == target;
    }

    public long getTarget() {
        return target;
    }
}
