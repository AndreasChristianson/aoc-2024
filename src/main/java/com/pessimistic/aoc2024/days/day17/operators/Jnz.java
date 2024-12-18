package com.pessimistic.aoc2024.days.day17.operators;

import com.pessimistic.aoc2024.days.day17.Device;

import java.math.BigInteger;

public class Jnz implements Operator {
    private final Device device;

    public Jnz(Device device) {
        this.device = device;
    }

    @Override
    public void apply() {
        if (device.getA() != 0) {
            device.setInstruction(BigInteger.valueOf(device.getLiteral()).intValueExact());
        } else {
            device.advance();
        }
    }
}
