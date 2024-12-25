package com.pessimistic.aoc2024.days.day22;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Generator {

    private final Long initial;
    private long current;
    private final Map<FourChanges,Integer> history;
    private FourChanges recentChanges;

    private static final long MOD = 16777216;

    public Generator(Long initial) {
        this.initial = initial;
        this.current = initial;
        this.history = new HashMap<>();
        recentChanges = new FourChanges(null,null,null,null);
    }

    private void iterate() {
        var lastBananas = getBananas();
        var round1 = current * 64;
        current ^= round1;
        current %= MOD;
        var round2 = current / 32;
        current ^= round2;
        current %= MOD;
        var round3 = current * 2048;
        current ^= round3;
        current %= MOD;
        var changeInPrice = getBananas() - lastBananas;
        recentChanges = new FourChanges(recentChanges.second(), recentChanges.third(),recentChanges.fourth(), changeInPrice);
        if(!history.containsKey(recentChanges)&&recentChanges.isValid()) {
            history.put(recentChanges, getBananas());
        }
    }

    public Long next() {
        iterate();
        return get();
    }

    public int nextBananas() {
        iterate();
        return getBananas();
    }

    public long get() {
        return current;
    }

    public int getBananas() {
        return (int) (get() % 10);
    }

    public void next(int count) {
        for (int i = 0; i < count; i++) {
            iterate();
        }
    }

    public Map<FourChanges, Integer> history() {
        return history;
    }
}
