package com.pessimistic.aoc2024.days.day24;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class WireSystem {
    private final Map<String, List<Component>> componentsByInputId;
    private final Map<String, Component> componentsByOutputId;
    private Map<String, Boolean> values;
    private final Collection<Component> components;
    private final Map<String, String> aliases;

    public WireSystem(List<Component> components) {
        values = new HashMap<>();
        this.components = new TreeSet<>();
        componentsByInputId = new HashMap<>();
        componentsByOutputId = new HashMap<>();
        for (Component component : components) {
            assert component.inputs().size() == 2;
            assert componentsByOutputId.get(component.output()) == null;

            add(component);
        }
        //check for loops ?
        aliases = new HashMap<>();
    }

    public void propagate(long x, long y) {
        setLong(x, "x");
        setLong(y, "y");
        propagate(
                components.stream()
                        .map(Component::inputs)
                        .flatMap(Collection::stream)
                        .filter(id -> id.matches("[yx]\\d\\d"))
                        .toList()
        );
    }

    private void setLong(long longValue, String prefix) {
        var asString = "%s%s".formatted(StringUtils.repeat("0", 45), Long.toBinaryString(longValue));
        asString = StringUtils.reverse(asString);
        for (int i = 0; i < 45; i++) {
            var asBool = asString.charAt(i) == '1';
            values.put("%s%02d".formatted(prefix, i), asBool);
        }
    }

    public void propagate(Map<String, Boolean> newValues) {
        values = newValues;
        propagate(newValues.keySet());
    }

    public void propagate(Collection<String> changedInputs) {
        var toBePropagated = new LinkedList<>(changedInputs);
        while (!toBePropagated.isEmpty()) {
            var current = toBePropagated.poll();
            toBePropagated.addAll(propagate(current));
        }
    }

    private Collection<String> propagate(String current) {
        var affected = new ArrayList<String>();
        for (var component : componentsByInputId.getOrDefault(current, Collections.emptyList())) {
            if (propagate(component)) {
                affected.add(component.output());
            }
        }
        return affected;
    }

    private boolean propagate(Component component) {
        var left = values.get(component.inputs().getFirst());
        var right = values.get(component.inputs().getLast());
        if (left != null && right != null) {
            var result = component.operation().apply(left, right);
            values.put(component.output(), result);
            return true;
        }
        return false;
    }

    public long readZs() {
        var zs = values.entrySet()
                .stream()
                .filter(e -> e.getKey().startsWith("z"))
                .sorted((l, r) -> r.getKey().compareTo(l.getKey()))
                .map(Map.Entry::getValue)
                .map(b -> b ? "1" : "0")
                .collect(Collectors.joining());
        return Long.parseLong(zs, 2);
    }

    //https://en.wikipedia.org/wiki/Adder_(electronics)#Ripple-carry_adder
    public void printWiringDiagram() {
        for (var component : components) {
            System.out.println(component.toString(aliases));
        }
    }

    public void swapOutputs(String leftId, String rightId) {
        System.out.printf("Swapping outputs: %s <---> %s%n", leftId, rightId);
        var left = componentsByOutputId.get(leftId);
        var right = componentsByOutputId.get(rightId);
        assert left != null;
        assert right != null;
        var newLeft = left.newOutput(rightId);
        var newRight = right.newOutput(leftId);
        remove(left);
        remove(right);
        add(newLeft);
        add(newRight);
    }

    private void add(Component component) {
        components.add(component);
        componentsByOutputId.put(component.output(), component);
        component.inputs()
                .forEach(input -> {
                    var list = componentsByInputId.getOrDefault(input, new ArrayList<>());
                    list.add(component);
                    componentsByInputId.put(input, list);
                });
    }

    private void remove(Component component) {
        components.remove(component);
        components.remove(component);
        componentsByOutputId.remove(component.output());
        component.inputs()
                .forEach(input -> componentsByInputId.get(input).remove(component));
    }

    public void renameIds() {
        for (int i = 0; i < 45; i++) {
            var xInput = "x%02d".formatted(i);
            var yInput = "y%02d".formatted(i);
            var list = componentsByInputId.get(xInput).stream()
                    .filter(component -> component.inputs().contains(yInput))
                    .toList();
            for (var component : list) {
                assert aliases.get(component.output()) == null;
                aliases.put(component.output(), "%s%02d".formatted(component.operation(), i));
            }
        }


        for (int i = 0; i < 45; i++) {
            var xInput = "x%02d".formatted(i);
            var yInput = "y%02d".formatted(i);
            var output = "z%02d".formatted(i);
            var and = "AND%02d".formatted(i);
            var xor = "XOR%02d".formatted(i);
            var section = new HashSet<Component>();
            section.addAll(
                    componentsByInputId.get(xInput).stream()
                            .filter(component -> component.inputs().contains(yInput))
                            .toList()
            );
            section.addAll(
                    aliases.entrySet().stream()
                            .filter(alias -> alias.getValue().equals(xor))
                            .map(Map.Entry::getKey)
                            .map(componentsByInputId::get)
                            .map(Optional::ofNullable)
                            .flatMap(Optional::stream)
                            .flatMap(Collection::stream)
                            .toList()
            );
            section.addAll(
                    aliases.entrySet().stream()
                            .filter(alias -> alias.getValue().equals(and))
                            .map(Map.Entry::getKey)
                            .map(componentsByInputId::get)
                            .map(Optional::ofNullable)
                            .flatMap(Optional::stream)
                            .flatMap(Collection::stream)
                            .toList()
            );
            section.add(componentsByOutputId.get(output));
            for (var component : section) {
                System.out.println(component.toString(aliases));
            }
            System.out.println("---");

        }
        System.out.println(componentsByOutputId.get("z45").toString(aliases));
    }
}
