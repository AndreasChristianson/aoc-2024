package com.pessimistic.aoc2024.days.day17.operators;

import com.pessimistic.aoc2024.days.day17.Device;

public class Out implements Operator {
    private final Device device;

    public Out(Device device) {
        this.device = device;
    }

    @Override
    public void apply() {
        device.addOutput(device.getCombo() % 8);
        device.advance();
    }
}
