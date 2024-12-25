package com.pessimistic.aoc2024.days.day21;

import com.pessimistic.aoc2024.util.FileUtils;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Day21 {
    private static final Logger logger = Logger.getLogger(Day21.class.getName());

    private Day21() {
    }

    public static long star1(long robotCount, String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var sum = 0L;
        for (var line : lines) {
            var numeric = Integer.parseInt(line.substring(0, 3));
            var keyPadEntry = KeyPad.parse(line);
            var dirPadInstructions = KeyPad.encode(keyPadEntry);
            for (int i = 0; i < robotCount; i++) {
                dirPadInstructions = DirPad.encode(dirPadInstructions);
            }
            sum += dirPadInstructions.getMinSize() * numeric;
        }
        return sum;
    }
    /*
    379
    <v<A>>^AvA^A<vA<AA>>^AAvA<^A>AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A 64
    <      A > A  v <<   AA >  ^ AA > A  v  AA ^ A   < v  AAA >  ^ A 28
           ^   A         <<      ^^   A     >>   A        vvv      A
               3                      7          9                 A

    379
    v<<A>>^AvA^Av<<A>>^AAv<A<A>>^AAvAA<^A>Av<A>^AA<A>Av<A<A>>^AAAvA<^A>A 68
       <   A > A   <   AA  v <   AA >>  ^ A  v  AA ^ A  v <   AAA >  ^ A 28
           ^   A       ^^        <<       A     >>   A        vvv      A
               3                          7          9                 A
     */

    public static long star2(long robotCount, String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var sum = 0L;
        for (var line : lines) {
            var numeric = Integer.parseInt(line.substring(0, 3));
            var keyPadEntry = KeyPad.parse(line);
            var sentences = KeyPad.encodeToWords(keyPadEntry);
            for (int i = 0; i < robotCount; i++) {
                trim(sentences);
                sentences = sentences.stream()
                        .map(Sentence::encodeWords)
                        .collect(Collectors.toList());
            }
            var minLength = sentences.stream()
                    .mapToLong(Sentence::size)
                    .min()
                    .orElseThrow();
            sum += minLength * numeric;
        }
        return sum;
    }

    private static void trim(List<Sentence> sentences) {
        var minLength = sentences.stream()
                .mapToLong(Sentence::size)
                .min()
                .orElseThrow();
        sentences.removeIf(curr -> curr.size() != minLength);
    }
}
