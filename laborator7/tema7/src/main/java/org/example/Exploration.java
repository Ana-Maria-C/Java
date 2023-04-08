package org.example;

import java.util.*;

public class Exploration {
    private ExplorationMap map;
    private SharedMemory memory;
    private int numRobots;
    private int tokensPerCell;
    private List<Thread> threads;
    public Exploration(int mapSize, int numRobots, int tokensPerCell) {
        this.map = new ExplorationMap(mapSize);
        this.memory = new SharedMemory(mapSize * mapSize);
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

    public Thread getThread(int index) {
        for(int i=0;i<threads.size();i++)
        {
            if(i==index)
            {
                return threads.get(i);
            }
        }
        return null;
    }
}
