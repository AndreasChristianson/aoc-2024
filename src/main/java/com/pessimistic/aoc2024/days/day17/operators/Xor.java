package com.pessimistic.aoc2024.days.day17.operators;

import com.pessimistic.aoc2024.days.day17.Device;

import java.util.function.Function;

public class Xor implements Operator {
    private final Device device;
    private final Function<Device, Long> left;
    private final Function<Device, Long> right;

    public Xor(Device device, Function<Device, Long> left, Function<Device, Long> right) {
        this.device = device;
        this.left = left;
        this.right = right;
    }


    @Override
    public void apply() {
        device.setB(left.apply(device) ^ right.apply(device));
        device.advance();
    }
}
