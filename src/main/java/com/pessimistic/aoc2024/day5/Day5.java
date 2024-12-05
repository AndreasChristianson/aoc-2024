package com.pessimistic.aoc2024.day5;

import com.pessimistic.aoc2024.util.FileUtils;

import java.util.ArrayList;
import java.util.List;


public class Day5 {
    private Day5() {
    }


    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var rules = getRuleSet(lines);
        var printings = getPrintings(lines);
        return printings.stream().filter(rules::validate).map(Printing::getMiddle).mapToInt(Page::number).sum();
    }

    private static List<Printing> getPrintings(List<String> lines) {
        List<Printing> printings= new ArrayList<>();

        for (var line : lines) {
            if(line.matches("(\\d+,)*\\d+")){
                printings.add(Printing.parse(line));
            }
        }
        return  printings;
    }

    private static RuleSet getRuleSet(List<String> lines) {
        List<Rule> rules = new ArrayList<>();
        for (var line : lines) {
            if(line.matches("\\d+\\|\\d+")){
                rules.add(Rule.parse(line));
            }
        }
        return new RuleSet(rules);
    }


    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var rules = getRuleSet(lines);
        var printings = getPrintings(lines);
        return printings.stream().filter(printing -> !rules.validate(printing))
                .map(rules::fixOrder).map(Printing::getMiddle).mapToInt(Page::number).sum();
    }

}
