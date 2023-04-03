package org.example;

import java.util.ArrayList;
import java.util.List;

public class Exploration {
    private ExplorationMap map;
    private SharedMemory memory;
    private int numRobots;
    private int tokensPerCell;
    private List<Thread> threads;
    public Exploration(int mapSize, int numRobots, int tokensPerCell) {
        this.map = new ExplorationMap(mapSize);
        this.memory = new SharedMemory(mapSize * mapSize+1);
        this.numRobots = numRobots;
        this.tokensPerCell = tokensPerCell;
        this.threads = new ArrayList<>(numRobots);
        for (int i = 0; i < numRobots; i++) {
            Robot robot = new Robot("Robot " + i, map, memory, tokensPerCell);
            Thread thread = new Thread(robot);
            threads.add(thread);
        }
    }

    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void pause() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public void resume() {
        start();
    }

    @Override
    public String toString() {
        return "Exploration{" +
                "map=" + map +
                ", memory=" + memory +
                ", numRobots=" + numRobots +
                ", tokensPerCell=" + tokensPerCell +
                ", threads=" + threads +
                '}';
    }
}