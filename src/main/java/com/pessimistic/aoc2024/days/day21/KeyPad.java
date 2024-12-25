package com.pessimistic.aoc2024.days.day21;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class KeyPad {

    public static KeyPadEntry parse(String line) {
        return new KeyPadEntry(Arrays.stream(line.split(""))
                .map(KeyPadInstruction::fromString)
                .toList());
    }

    public static PotentialPaths encode(KeyPadEntry entry) {
        var ret = List.of(new DirPadEntry(new LinkedList<>()));
        var lastPosition = KeyPadInstruction.KP_A;
        for (var instruction : entry.instructions()) {
            var possiblePaths = instruction.encode(lastPosition);
            ret = ret.stream()
                    .flatMap(head -> possiblePaths.stream()
                            .map(head::append))
                    .toList();
            lastPosition = instruction;
        }
        return new PotentialPaths(ret);
    }

    public static List<Sentence> encodeToWords(KeyPadEntry entry) {
        var ret = List.of(new Sentence(Collections.emptyList()));
        var lastPosition = KeyPadInstruction.KP_A;
        for (var instruction : entry.instructions()) {
            var words = instruction.encodeToWords(lastPosition);
            ret = ret.stream()
                    .flatMap(head -> words.stream()
                            .map(word -> {
                                var newSentence = head.copy();
                                newSentence.add(word);
                                return newSentence;
                            }))
                    .collect(Collectors.toList());
            lastPosition = instruction;
        }
        return ret;
    }
}
