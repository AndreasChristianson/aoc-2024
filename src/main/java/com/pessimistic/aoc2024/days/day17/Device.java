package com.pessimistic.aoc2024.days.day17;

import com.pessimistic.aoc2024.days.day17.operators.Operator;
import com.pessimistic.aoc2024.util.TextUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Device {
    private final List<Long> program;
    private long initialA;
    private final long initialB;
    private final long initialC;
    private long a;
    private long b;
    private long c;
    private int instruction;
    private List<Long> output;

    public Device(long a, long b, long c, List<Long> program) {
        this.initialA = a;
        this.initialB = b;
        this.initialC = c;
        this.program = program;
        reset();
    }

    public Device(String a, String b, String c, List<Long> program) {
        this(Long.parseLong(a), Long.parseLong(b), Long.parseLong(c), program);
    }

    @Override
    public String toString() {
        var programText = "";
        if (complete()) {
            programText = program.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            programText = "\nprogram: %s".formatted(programText);
        } else {
            var programBefore = program.subList(0, instruction).stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            var programAfter = program.subList(instruction + 1, program.size() - 1).stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            var current = String.valueOf(program.get(instruction));
            var line2 = "program: %s |%s| %s".formatted(programBefore, current, programAfter);
            var line1 = "         %s  %s  %s".formatted(
                    StringUtils.repeat(" ", programBefore.length()),
                    StringUtils.repeat("v", current.length()),
                    StringUtils.repeat(" ", programAfter.length())
            );
            programText = "%s\n%s".formatted(line1, line2);
        }

        var outputText = output.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        outputText = "output: %s".formatted(outputText);


        var registers = "register A:  %d \t\t(initially %d)\nregister B:  %d \t\t(initially %d)\nregister C:  %d \t\t(initially %d)"
                .formatted(
                        a,
                        initialA,
                        b,
                        initialB,
                        c,
                        initialC
                );
        var instructionText = "instruction: %d \t\t(complete: %b)"
                .formatted(
                        instruction,
                        complete()
                );
        return "Device\n%s\n%s\n%s\n%s"
                .formatted(
                        registers,
                        instructionText,
                        programText,
                        outputText
                );
    }

    private boolean complete() {
        return instruction >= program.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return initialA == device.initialA && initialB == device.initialB && initialC == device.initialC && Objects.equals(program, device.program);
    }

    @Override
    public int hashCode() {
        return Objects.hash(program, initialA, initialB, initialC);
    }

    public static Device fromLines(List<String> lines) {
        var a = TextUtils.allMatches(lines.get(0), "Register A: (?<a>-?\\d+)")
                .findFirst()
                .orElseThrow()
                .get("a");

        var b = TextUtils.allMatches(lines.get(1), "Register B: (?<b>-?\\d+)")
                .findFirst()
                .orElseThrow()
                .get("b");

        var c = TextUtils.allMatches(lines.get(2), "Register C: (?<c>-?\\d+)")
                .findFirst()
                .orElseThrow()
                .get("c");

        return new Device(a, b, c, parseProgram(lines.get(4)));
    }

    private static List<Long> parseProgram(String line) {
        var program = TextUtils.allMatches(line, "Program: (?<program>(-?\\d+,?)+)")
                .findFirst()
                .orElseThrow()
                .get("program")
                .split(",");
        return Arrays.stream(program)
                .map(Long::parseLong)
                .toList();
    }

    private void reset() {
        a = initialA;
        b = initialB;
        c = initialC;
        instruction = 0;
        output = new ArrayList<>();
    }

    public Long getA() {
        return a;
    }

    public Long getB() {
        return b;
    }

    public Long getC() {
        return c;
    }

    public int getInstruction() {
        return instruction;
    }

    public Long getLiteral() {
        return program.get(instruction + 1);
    }

    public Long getCombo() {
        var operand = program.get(instruction + 1);
        return switch (operand.intValue()) {
            case 0, 1, 2, 3 -> operand;
            case 4 -> getA();
            case 5 -> getB();
            case 6 -> getC();
            case 7 -> throw new IllegalArgumentException("invalid operand 7");
            default -> throw new IllegalArgumentException("invalid operand %d".formatted(operand));
        };
    }

    public void addOutput(Long out) {
        this.output.add(out);
    }

    public void advance() {
        setInstruction(getInstruction() + 2);
    }

    public void setInstruction(int instruction) {
        this.instruction = instruction;
    }

    public void setA(Long a) {
        this.a = a;
    }

    public void setB(Long b) {
        this.b = b;
    }

    public void setC(Long c) {
        this.c = c;
    }

    public void setInitialA(long i) {
        initialA = i;
    }

    public List<Long> getProgram() {
        return program;
    }

    public List<Long> run(Long l) {
        setInitialA(l);
        reset();
        while (!complete()) {
            var operatorCode = program.get(instruction);
            Operator.of(operatorCode.intValue(), this).apply();
        }
        return Collections.unmodifiableList(output);
    }

    public List<Long> run() {
        return run(initialA);
    }
}
