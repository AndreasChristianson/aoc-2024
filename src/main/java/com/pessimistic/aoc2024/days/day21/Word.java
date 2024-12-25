package com.pessimistic.aoc2024.days.day21;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record Word(List<DirPadInstruction> instructions) {
    @Override
    public String toString() {
        return instructions.stream()
                .map(DirPadInstruction::toString)
                .collect(Collectors.joining());
    }

    public long size() {
        return instructions.size();
    }

    public List<Word> encode() {
        var ret = new ArrayList<Word>();
        var lastPosition = DirPadInstruction.ENTER;
        for (var instruction : instructions()) {
            var word = new Word(instruction.encode(lastPosition));
            ret.add(word);
            lastPosition = instruction;
        }
        return ret;
    }
}
