package com.pessimistic.aoc2024.days.day23;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class LanParty {
    private final Map<String, Node> nodesById;
    private final List<Set<String>> cliques;

    public LanParty() {
        this.nodesById = new HashMap<>();
        this.cliques = new ArrayList<Set<String>>();
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

    public List<Set<String>> findCliques() {
        var r = new HashSet<String>();
        var x = new HashSet<String>();
        var p = new HashSet<>(nodesById.keySet());
        bronKerbosch(r, p, x);
        return cliques;
    }

    // https://en.wikipedia.org/wiki/Bron%E2%80%93Kerbosch_algorithm#Without_pivoting
    private void bronKerbosch(Set<String> r, Set<String> p, Set<String> x) {
        if (p.isEmpty() && x.isEmpty()) {
            cliques.add(r);
        }
        var remainingP = new HashSet<>(p);
        for (var id : p) {
            var node = nodesById.get(id);
            assert node != null;
            var newR = new HashSet<>(r);
            newR.add(id);
            var newP = new HashSet<>(remainingP);
            newP.retainAll(node.connections);
            var newX = new HashSet<>(x);
            newX.retainAll(node.connections);
            bronKerbosch(newR,newP,newX);
            remainingP.remove(id);
            x.add(id);
        }
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
