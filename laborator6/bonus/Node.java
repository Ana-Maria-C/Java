package com.example.homework;


import java.util.ArrayList;
import java.util.List;

public class Node {
    private int name;
    private List<Node> neighbors;

    public Node(int name) {
        this.name = name;
        this.neighbors = new ArrayList<>();
    }

    public int getName() {
        return name;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    @Override
    public String toString() {
        return "Node: "+ name+"\n";
    }
}