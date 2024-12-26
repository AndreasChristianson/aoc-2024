package com.pessimistic.aoc2024.days.day23;

import java.util.Objects;
import java.util.Set;

public record Triplet(Set<String> ids) {
    public boolean startsWith(String letter) {
        return ids.stream().anyMatch(id -> id.startsWith(letter));
    }

    public Triplet {
        assert ids.size() == 3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triplet triplet = (Triplet) o;
        return ids.containsAll(triplet.ids) && ids.size() == triplet.ids.size();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ids);
    }
}
