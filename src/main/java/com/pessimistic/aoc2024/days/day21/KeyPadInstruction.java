package com.pessimistic.aoc2024.days.day21;

import com.pessimistic.aoc2024.twoDimensional.Direction;
import com.pessimistic.aoc2024.twoDimensional.Point;

import java.util.*;

public enum KeyPadInstruction {
    KP_1(Point.of(2, 0)),
    KP_2(Point.of(2, 1)),
    KP_3(Point.of(2, 2)),

    KP_4(Point.of(1, 0)),
    KP_5(Point.of(1, 1)),
    KP_6(Point.of(1, 2)),

    KP_7(Point.of(0, 0)),
    KP_8(Point.of(0, 1)),
    KP_9(Point.of(0, 2)),

    KP_0(Point.of(3, 1)),
    KP_A(Point.of(3, 2)),

    ;
    private static final Point OFF_LIMITS = Point.of(3, 0);

    private final Point location;

    KeyPadInstruction(Point location) {
        this.location = location;
    }

    public static KeyPadInstruction fromString(String s) {
        assert s.length() == 1;
        return switch (s) {
            case "A" -> KP_A;
            case "1" -> KP_1;
            case "2" -> KP_2;
            case "3" -> KP_3;
            case "4" -> KP_4;
            case "5" -> KP_5;
            case "6" -> KP_6;
            case "7" -> KP_7;
            case "8" -> KP_8;
            case "9" -> KP_9;
            case "0" -> KP_0;
            default -> throw new IllegalArgumentException();
        };
    }

    public Collection<DirPadEntry> encode(KeyPadInstruction lastPosition) {
        var possiblePaths = travel(lastPosition.location);
        possiblePaths.forEach(list -> list.addLast(DirPadInstruction.ENTER));
        return possiblePaths.stream()
                .map(DirPadEntry::new)
                .toList();
    }

    private Collection<LinkedList<DirPadInstruction>> travel(Point currentLocation) {
        var target = this.location;
        if (currentLocation.equals(target)) {
            return List.of(new LinkedList<>());
        }
        if (currentLocation.equals(OFF_LIMITS)) {
            return Collections.emptyList();
        }
        var ret = new ArrayList<LinkedList<DirPadInstruction>>();
        if (currentLocation.col() < target.col()) {
            var tails = travel(currentLocation.add(Direction.E.getDelta()));
            tails.forEach(list -> list.addFirst(DirPadInstruction.RIGHT));
            ret.addAll(tails);
        }
        if (currentLocation.row() > target.row()) {
            var tails = travel(currentLocation.add(Direction.N.getDelta()));
            tails.forEach(list -> list.addFirst(DirPadInstruction.UP));
            ret.addAll(tails);
        }
        if (currentLocation.col() > target.col()) {
            var tails = travel(currentLocation.add(Direction.W.getDelta()));
            tails.forEach(list -> list.addFirst(DirPadInstruction.LEFT));
            ret.addAll(tails);
        }
        if (currentLocation.row() < target.row()) {
            var tails = travel(currentLocation.add(Direction.S.getDelta()));
            tails.forEach(list -> list.addFirst(DirPadInstruction.DOWN));
            ret.addAll(tails);
        }
        return ret;
    }

    @Override
    public String toString() {
        return switch (this) {
            case KP_1 -> "1";
            case KP_2 -> "2";
            case KP_3 -> "3";
            case KP_4 -> "4";
            case KP_5 -> "5";
            case KP_6 -> "6";
            case KP_7 -> "7";
            case KP_8 -> "8";
            case KP_9 -> "9";
            case KP_0 -> "0";
            case KP_A -> "A";
        };
    }

    public Collection<Word> encodeToWords(KeyPadInstruction lastPosition) {
        var possiblePaths = travel(lastPosition.location);
        possiblePaths.forEach(list -> list.addLast(DirPadInstruction.ENTER));
        return possiblePaths.stream()
                .map(Word::new)
                .toList();
    }
}
