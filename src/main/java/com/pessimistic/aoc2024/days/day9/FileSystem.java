package com.pessimistic.aoc2024.days.day9;

import com.pessimistic.aoc2024.util.NumberUtils;

import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class FileSystem {
    private final SortedMap<Integer, Blocks> positionToFile;

    public FileSystem(SortedMap<Integer, Blocks> positionToFile) {
        this.positionToFile = positionToFile;
    }

    public static FileSystem parse(String line) {
        var position = 0;
        var id = 0;
        var map = new TreeMap<Integer, Blocks>();
        for (int i = 0; i < line.length(); i++) {
            int value = Integer.parseInt(String.valueOf(line.charAt(i)));
            if (NumberUtils.isEven(i)) {
                //file
                map.put(position, new File(id, value));
                id++;
            } else {
                map.put(position, new FreeSpace(value));
            }
            position += value;
        }
        return new FileSystem(map);
    }

    @Override
    public String toString() {
        return positionToFile.sequencedEntrySet()
                .stream().map(entry -> entry.getValue().asString())
                .collect(Collectors.joining());
    }

    public void defragment() {
        var potentialNextSpace = findSpace(0);
        while (potentialNextSpace.isPresent()) {
            var position = potentialNextSpace.get();
            var tailEntry = positionToFile.pollLastEntry();
            if (tailEntry.getValue() instanceof File tail) {
                var space = positionToFile.remove(position);
                if (space.length() >= tail.length()) {//fits!
                    positionToFile.put(position, tail);
                    var remaining = space.length() - tail.length();
                    if (remaining > 0) {
                        positionToFile.put(position + tail.length(), new FreeSpace(remaining));
                    }
                } else {//not enough space
                    positionToFile.put(position, tail.getSlice(space.length()));
                    positionToFile.put(tailEntry.getKey(), tail.getSliced(space.length()));
                }
            }
            potentialNextSpace = findSpace(position);
        }
        System.out.println(this);
    }

    private Optional<Integer> findSpace(int start) {
        for (int i = start; i < positionToFile.lastKey(); i++) {
            var potentialItem = Optional.ofNullable(positionToFile.get(i));
            if (potentialItem.filter(item -> item.getClass() == FreeSpace.class).isPresent()) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    public long checksum() {
        var sum = 0L;
        for (var entry : positionToFile.entrySet()) {
            if (entry.getValue() instanceof File) {
                var file = (File) entry.getValue();
                sum += file.checkSum(entry.getKey());
            }
        }
        return sum;
    }

    public void defragmentFiles() {
        for (int i = positionToFile.lastKey(); i >= 0; i--) {
            var item = positionToFile.get(i);
            if (item == null) {
                continue;
            }
            if (item instanceof File file) {
                var potentialNextSpace = findFirstSpaceOfSize(file.length(), i);
                if (potentialNextSpace.isEmpty()) {
                    continue;
                }
                var position = potentialNextSpace.get();
                var space = positionToFile.remove(position);
                assert space.length() >= file.length();
                positionToFile.put(position, positionToFile.remove(i));

                if (space.length() != file.length()) {// leftover space
                    positionToFile.put(position + file.length(), new FreeSpace(space.length() - file.length()));
                }
            }else{
                positionToFile.remove(i);
            }

        }
        System.out.println(this);
    }

    private Optional<Integer> findFirstSpaceOfSize(int targetLength, int maxPosition) {
        return positionToFile.sequencedEntrySet()
                .stream()
                .filter(entry -> entry.getValue() instanceof FreeSpace)
                .filter(entry -> entry.getValue().length() >= targetLength)
                .filter(entry -> entry.getKey() < maxPosition)
                .map(Map.Entry::getKey)
                .findFirst();
    }
}
