package org.example;


import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Robot implements Runnable {
    private String name;
    private ExplorationMap map;
    private SharedMemory memory;
    private Lock mapLock;
    private Lock memoryLock;
    private int tokensPerCell;

    public Robot(String name, ExplorationMap map, SharedMemory memory, int tokensPerCell) {
        this.name = name;
        this.map = map;
        this.memory = memory;
        this.mapLock = map.getLock();
        this.memoryLock = memory.getLock();
        this.tokensPerCell = tokensPerCell;
    }

    public void run() {
        Random random = new Random();
        int x = random.nextInt(map.getSize());
        int y = random.nextInt(map.getSize());
        while (!map.isExplored()) {
            visitCell(x, y);
            List<Token> tokens = extractTokens();
            map.setCellTokens(x, y, tokens);
            x = random.nextInt(map.getSize());
            y = random.nextInt(map.getSize());
        }
    }

    private void visitCell(int x, int y) {
        mapLock.lock();
        try {
            if (!map.isExplored(x, y)) {
                System.out.println(name + " exploring cell (" + x + "," + y + ")");
                map.setExplored(x, y);
            }
        } finally {
            mapLock.unlock();
        }
    }

    private List<Token> extractTokens() {
        memoryLock.lock();
        try {
            int numTokens = Math.min(memory.size(), tokensPerCell);
            List<Token> tokens = memory.extractTokens(numTokens);
            System.out.println(name + " extracted tokens: " + tokens);
            return tokens;
        } finally {
            memoryLock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Robot{" +
                "name='" + name + '\'' +
                ", map=" + map +
                ", memory=" + memory +
                ", mapLock=" + mapLock +
                ", memoryLock=" + memoryLock +
                ", tokensPerCell=" + tokensPerCell +
                '}';
    }
}