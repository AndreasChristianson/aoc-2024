package com.pessimistic.aoc2024.days.day17.operators;

import com.pessimistic.aoc2024.days.day17.Device;

public class Bst implements Operator {
    private final Device device;

    public Bst(Device device) {
        this.device = device;
    }

    @Override
    public void apply() {
        device.setB(device.getCombo() % 8);
        device.advance();
    }
}
