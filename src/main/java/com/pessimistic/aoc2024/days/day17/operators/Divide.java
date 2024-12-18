package com.pessimistic.aoc2024.days.day17.operators;

import com.pessimistic.aoc2024.days.day17.Device;

import java.math.BigInteger;
import java.util.function.BiConsumer;

public class Divide implements Operator {

    private final Device device;
    private final BiConsumer<Device, Long> setter;

    public Divide(Device device, BiConsumer<Device, Long> setter) {
        this.device = device;
        this.setter = setter;
    }


    @Override
    public void apply() {
        var numerator = device.getA();
        var denominator = BigInteger.valueOf(2)
                .pow(BigInteger.valueOf(device.getCombo()).intValueExact())
                .intValueExact();
        setter.accept(device, numerator / denominator);
        device.advance();
    }
}
