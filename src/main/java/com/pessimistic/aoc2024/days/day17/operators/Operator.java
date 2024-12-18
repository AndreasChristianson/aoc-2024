package com.pessimistic.aoc2024.days.day17.operators;

import com.pessimistic.aoc2024.days.day17.Device;

public interface Operator {

    static Operator of(int operatorCode, Device device) {
        return switch (operatorCode) {
            case 0 -> new Divide(device, Device::setA);
            case 1 -> new Xor(device, Device::getB, Device::getLiteral);
            case 2 -> new Bst(device);
            case 3 -> new Jnz(device);
            case 4 -> new Xor(device, Device::getB, Device::getCombo);
            case 5 -> new Out(device);
            case 6 -> new Divide(device, Device::setB);
            case 7 -> new Divide(device, Device::setC);
            default -> throw new IllegalArgumentException("Invalid operator code: " + operatorCode);
        };
    }

    void apply();
}
