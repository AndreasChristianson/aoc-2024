package com.pessimistic.aoc2024.days.day23;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class LanParty {
    private final Map<String, Node> nodesById;

    public LanParty() {
        this.nodesById = new HashMap<>();
    }

    public static LanParty fromLines(List<String> lines) {
        var lanParty = new LanParty();
        for (String line : lines) {
            String[] parts = line.split("-");
            assert parts.length == 2;
            lanParty.addConnection(parts[0], parts[1]);
        }
        return lanParty;
    }

    private void addConnection(String from, String to) {
        nodesById.merge(from, new Node(from, Set.of(to)), Node::addAllConnection);
        nodesById.merge(to, new Node(to, Set.of(from)), Node::addAllConnection);
    }

    public Set<Triplet> findTriplets() {
        return nodesById.values()
                .stream()
                .map(node -> node.getCycle(3))
                .flatMap(Collection::stream)
                .map(Triplet::new)
                .collect(Collectors.toSet());
    }

    public List<String> findLargestCluster() {
        return Collections.emptyList();
    }

    private class Node {
        private final String id;
        private final Set<String> connections;

        private Node(String id, Set<String> connections) {
            this.id = id;
            this.connections = new HashSet<>(connections);
        }

        private Node addAllConnection(Node other) {
            assert other.id.equals(id);
            connections.addAll(other.connections);
            return this;
        }

        public List<Set<String>> getCycle(int size) {
            var discovered = new LinkedList<>(
                    connections.stream()
                            .map(connectionId -> Pair.of(connectionId, new HashSet<>(List.of(id))))
                            .toList()
            );
            var ret = new ArrayList<Set<String>>();
            while (!discovered.isEmpty()) {
                var pair = discovered.poll();
                if (pair.getLeft().equals(id) && pair.getRight().size() == size) {
                    ret.add(pair.getRight());
                }
                if (pair.getRight().size() < size && !pair.getRight().contains(pair.getLeft())) {
                    var newDiscovery = nodesById.get(pair.getLeft());
                    assert newDiscovery != null;
                    newDiscovery.connections
                            .forEach(nodeId -> {
                                var newSet = new HashSet<>(pair.getRight());
                                newSet.add(pair.getLeft());
                                discovered.push(Pair.of(nodeId, newSet));
                            });
                }
            }
            return ret;
        }
    }
}
