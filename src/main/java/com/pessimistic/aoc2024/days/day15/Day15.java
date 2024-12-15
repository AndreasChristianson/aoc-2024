package com.pessimistic.aoc2024.days.day15;

import com.pessimistic.aoc2024.util.FileUtils;

public class Day15 {
    private Day15() {
    }

    public static long star1(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var split = lines.indexOf("");
        var warehouseLines = lines.subList(0, split);

        var warehouse = Warehouse.toWarehouse(warehouseLines, WarehouseObject::fromChar);
        System.out.println(warehouse);
        var instructions =
                String.join("", lines.subList(split, lines.size()));
        System.out.println(instructions);

        warehouse.run(instructions);

        System.out.println(warehouse);

        return warehouse.getItemPoints(WarehouseObject.CRATE)
                .stream()
                .mapToLong(WarehouseObject::gps)
                .sum();
    }


    public static long star2(String fileName) {
        var lines = FileUtils.readTestFile(fileName);
        var split = lines.indexOf("");
        var warehouseLines = lines.subList(0, split);

        var warehouse = Warehouse.toWarehouse(warehouseLines, WarehouseObject::fromChar)
                .widen();
        System.out.println(warehouse);
        var instructions = String.join("", lines.subList(split, lines.size()));
        System.out.println(instructions);

        warehouse.run(instructions);

        System.out.println(warehouse);

        return warehouse.getItemPoints(WarehouseObject.WIDE_LEFT)
                .stream()
                .mapToLong(WarehouseObject::gps)
                .sum();
    }
}
