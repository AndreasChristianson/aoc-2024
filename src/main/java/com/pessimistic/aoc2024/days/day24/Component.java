package com.pessimistic.aoc2024.days.day24;

import com.pessimistic.aoc2024.util.TextUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public record Component(
        Operation operation,
        List<String> inputs,
        String output
) implements Comparable<Component> {

    // x00 AND y00 -> z00
    public static Component fromLine(String line) {
        var matches = TextUtils.allMatches(line, "(?<left>...) (?<operator>(AND)|(OR)|(XOR)) (?<right>...) -> (?<output>...)")
                .findAny()
                .orElseThrow();
        var right = matches.get("right");
        var left = matches.get("left");
        var operator = Operation.valueOf(matches.get("operator"));
        var output = matches.get("output");
        return new Component(
                operator,
                Stream.of(right, left).sorted().toList(),
                output
        );
    }

    public Component newOutput(String newOutput) {
        return new Component(
                operation(),
                new ArrayList<>(inputs()),
                newOutput
        );

    }

    public Comparator<Component> getComparator() {
        return Comparator.<Component, String>comparing(component -> component.inputs().getFirst())
                .thenComparing(component -> component.inputs().getLast())
                .thenComparing(Component::operation);
    }

    @Override
    public int compareTo(Component o) {
        return getComparator().compare(this, o);
    }

    @Override
    public String toString() {
        return "%s %s %s -> %s".formatted(inputs().getFirst(), operation(), inputs().getLast(), output());
    }

    public String toString(Map<String, String> aliases) {
        var first = inputs().getFirst();
        var newFirst = "%3s (%3s)".formatted(aliases.getOrDefault(first, first), first);
        var second = inputs().getLast();
        var newSecond = "%3s (%3s)".formatted(aliases.getOrDefault(second, second), second);
        var newOutput = "%3s (%3s)".formatted(aliases.getOrDefault(output(), output()), output());
        return "%s %s %s -> %s".formatted(newFirst, operation(), newSecond, newOutput);

    }
}
