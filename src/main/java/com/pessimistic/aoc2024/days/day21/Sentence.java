package com.pessimistic.aoc2024.days.day21;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Sentence {
    private final Map<Word, Long> words;

    public Sentence(List<Word> words) {
        this.words = words
                .stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
    }

    public Sentence(Map<Word, Long> words) {
        this.words = words;
    }

    public void add(Word word) {
        words.put(word, words.getOrDefault(word, 0L) + 1);
    }

    @Override
    public String toString() {
        return words.entrySet().stream()
                .map(entry -> "%d: |%s|".formatted(entry.getValue(), entry.getKey()))
                .collect(Collectors.joining(" "));
    }

    public Sentence copy() {
        return new Sentence(new HashMap<>(words));
    }

    public long size() {
        return words.entrySet().stream()
                .mapToLong(entry -> entry.getKey().size() * entry.getValue())
                .sum();
    }

    public Sentence encodeWords() {
        var ret = new HashMap<Word, Long>();
        for (var entry : words.entrySet()) {
            var word = entry.getKey();
            for (var newWord : word.encode()) {
                ret.put(newWord, ret.getOrDefault(newWord, 0L) + entry.getValue());
            }
        }
        return new Sentence(ret);
    }
}
