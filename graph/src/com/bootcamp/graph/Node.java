package com.bootcamp.graph;

import java.util.HashSet;
import java.util.Set;

public class Node {
    private String name;
    private Set<Node> neighbors;

    public Node(String name) {
        this.name = name;
        this.neighbors = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public boolean canReach(Node targetNode) {
        if (isSelf(targetNode)) return true;

        for (Node neighbor : neighbors) {
            if (neighbor.canReach(targetNode)) return true;
        }
        return false;
    }

    private boolean isSelf(Node anotherNode) {
        return this == anotherNode;
    }


    public void addNeighbor(Node neighbor) {
        this.neighbors.add(neighbor);
    }
}
