package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int size = 5;
        int tokensPerCell = 2;
        ExplorationMap map = new ExplorationMap(size);
        SharedMemory memory = new SharedMemory(size * size * tokensPerCell);

        // create and start robots
        Robot[] robots = new Robot[3];
        for (int i = 0; i < robots.length; i++) {
            robots[i] = new Robot("Robot " + (i+1), map, memory, tokensPerCell);
            new Thread(robots[i]).start();
        }

        // wait for robots to finish exploring
        for (Robot robot : robots) {
            robot.join();
        }

        // print map cells and tokens
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.println("Cell (" + i + "," + j + "):");
                System.out.println("\tExplored: " + map.isExplored(i, j));
                System.out.println("\tTokens: " + map.getCellTokens(i, j));
            }
        }
    }

}