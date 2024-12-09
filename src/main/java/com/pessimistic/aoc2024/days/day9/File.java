package com.pessimistic.aoc2024.days.day9;

import java.util.stream.LongStream;

public record File(
        int id,
        int length
) implements Blocks {

    @Override
    public String idString() {
        return String.valueOf(id);
    }

    public File getSlice(int cutAmount) {
        assert cutAmount < this.length();
        assert cutAmount > 0;
        return new File(this.id, cutAmount);
    }

    public File getSliced(int cutAmount) {
        assert cutAmount < this.length();
        assert cutAmount > 0;
        return new File(this.id, length - cutAmount);
    }

    public long checkSum(int position) {
        return LongStream.range(position, position + length())
                .map(pos -> pos * id())
                .sum();
    }
}
