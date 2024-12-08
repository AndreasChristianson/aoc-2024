package com.pessimistic.aoc2024.days.day5;

import java.util.*;

public class Printing {
    private final List<Page> pages;
    private final Map<Page, Integer> indexByPage;

    public Printing(List<Page> pages) {
        this.pages = new ArrayList<>(pages);
        this.indexByPage = new HashMap<>();
        for (var i = 0; i < pages.size(); i++) {
            indexByPage.put(pages.get(i), i);
        }
    }

    public static Printing parse(String line) {
        var pages = new ArrayList<Page>();
        var parts = line.split(",");
        for (var part : parts) {
            pages.add(Page.parse(part));
        }

        return new Printing(pages);
    }

    public List<Page> getPages() {
        return pages;
    }

    public Optional<Integer> getPageIndex(Page page) {
        return Optional.ofNullable(indexByPage.get(page));
    }

    public Page getMiddle() {
        return pages.get(pages.size() / 2);
    }

    public Printing copy() {
        return new Printing(pages);
    }

    public void swap(int firstPosition, int secondPosition) {
        var first = pages.get(firstPosition);
        var second = pages.get(secondPosition);
        indexByPage.put(first, secondPosition);
        indexByPage.put(second, firstPosition);
        pages.set(firstPosition, second);
        pages.set(secondPosition, first);
    }
}
