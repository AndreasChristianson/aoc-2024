package com.pessimistic.aoc2024.day5;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RuleSet {
    private final Map<Page, List<Rule>> rules;

    public RuleSet(List<Rule> rules) {
        this.rules = rules.stream()
                .collect(Collectors.groupingBy(Rule::first, Collectors.toList()));
    }

    public boolean validate(Printing printing) {
        return printing.getPages().stream().allMatch(page -> {
            var pagePosition = printing.getPageIndex(page)
                    .orElseThrow();
            var rules = this.rules.getOrDefault(page, Collections.emptyList());
            return rules.stream().allMatch(rule -> {
                var second = rule.second();
                var secondPosition = printing.getPageIndex(second);
                return secondPosition.map(pos -> pos > pagePosition)
                        .orElse(true);
            });
        });
    }

    public Printing fixOrder(Printing printing) {
        var clone = printing.copy();
        for (var i = 0; i < clone.getPages().size(); i++) {
            var page = clone.getPages().get(i);
            var rules = this.rules.getOrDefault(page, Collections.emptyList());
            for (var rule : rules) {
                var second = rule.second();
                int secondPosition = clone.getPageIndex(second).orElse(Integer.MAX_VALUE);
                if (secondPosition < i) {
                    clone.swap(i, secondPosition);
                    i = 0;
                    break;
                }
            }
        }
        return clone;
    }
}
