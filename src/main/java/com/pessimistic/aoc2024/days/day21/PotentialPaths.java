package com.pessimistic.aoc2024.days.day21;

import java.util.ArrayList;
import java.util.List;

public class PotentialPaths {
    private final List<DirPadEntry> possiblePaths;
    private final long minSize;

    public PotentialPaths(List<DirPadEntry> possibleEntries) {
        var minSize = Long.MAX_VALUE;
        for (DirPadEntry entry : possibleEntries) {
            minSize = Math.min(minSize, entry.size());
        }
        this.minSize = minSize;
        var ret = new ArrayList<DirPadEntry>();
        for (DirPadEntry entry : possibleEntries) {
            if (entry.size() == minSize) {
                ret.add(entry);
            }
        }
        this.possiblePaths = ret;
    }


    public List<DirPadEntry> getPotentialPaths() {
        return possiblePaths;
    }

    public long getMinSize() {
        return minSize;
    }

}
