package org.example;

public class Main {
    public static void main(String[] args) {
        int mapSize = 10;
        int numRobots = 5;
        int tokensPerCell = 2;

        Exploration exploration = new Exploration(mapSize, numRobots, tokensPerCell);
        exploration.start();
        exploration.toString();
        //exploration.pause();
    }
}