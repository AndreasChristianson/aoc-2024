package com.pessimistic.aoc2024.days.day21;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public record DirPadEntry(List<DirPadInstruction> instructions) {
    @Override
    public String toString() {
        return instructions.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public long size() {
        return instructions.size();
    }

    public DirPadEntry append(DirPadEntry tail) {
        var ret = new LinkedList<DirPadInstruction>(this.instructions());
        ret.addAll(tail.instructions());
        return new DirPadEntry(ret);
    }

}
