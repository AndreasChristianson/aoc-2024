package com.pessimistic.aoc2024.days.day21;

import java.util.Collections;
import java.util.LinkedList;

public class DirPad {
    public static PotentialPaths encode(PotentialPaths paths) {
        var ret = new LinkedList<DirPadEntry>();
        for (var path : paths.getPotentialPaths()) {
            ret.add(DirPad.encode(path));
        }
        return new PotentialPaths(ret);
    }

    private static DirPadEntry encode(DirPadEntry path) {
        var ret = new DirPadEntry(Collections.emptyList());
        var lastPosition = DirPadInstruction.ENTER;
        for (var instruction : path.instructions()) {
            ret = ret.append(new DirPadEntry(instruction.encode(lastPosition)));
            lastPosition = instruction;
        }
        return ret;
    }
}
