package org.example;

import java.util.*;
import java.util.concurrent.locks.*;

public class Robot implements Runnable {
    private String name;
    private ExplorationMap map;
    private SharedMemory memory;
    private Lock mapLock;
    private Lock memoryLock;
    private int tokensPerCell;
    private Deque<int[]> stack; // stack of cells to visit

    public Robot(String name, ExplorationMap map, SharedMemory memory, int tokensPerCell) {
        this.name = name;
        this.map = map;
        this.memory = memory;
        this.mapLock = map.getLock();
        this.memoryLock = memory.getLock();
        this.tokensPerCell = tokensPerCell;
        this.stack = new ArrayDeque<>();
    }

    public void run() {
        Random random = new Random();
        int x = random.nextInt(map.getSize());
        int y = random.nextInt(map.getSize());
        stack.push(new int[]{x, y});

        while (!map.isExplored()) {
            if (stack.isEmpty()) {
                x = random.nextInt(map.getSize());
                y = random.nextInt(map.getSize());
                stack.push(new int[]{x, y});
            }
            int[] cell = stack.pop();
            x = cell[0];
            y = cell[1];
            visitCell(x, y);

            // add unexplored neighboring cells to the stack
            if (x > 0 && !map.isExplored(x-1, y)) {
                stack.push(new int[]{x-1, y});
            }
            if (y > 0 && !map.isExplored(x, y-1)) {
                stack.push(new int[]{x, y-1});
            }
            if (x < map.getSize()-1 && !map.isExplored(x+1, y)) {
                stack.push(new int[]{x+1, y});
            }
            if (y < map.getSize()-1 && !map.isExplored(x, y+1)) {
                stack.push(new int[]{x, y+1});
            }

            List<Token> tokens = extractTokens();
            map.setCellTokens(x, y, tokens);
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
            List<Token> tokens = memory.extractTokens(tokensPerCell);
            System.out.println(name + " extracted tokens: " + tokens);
            return tokens;
        } finally {
            memoryLock.unlock();
        }
    }

    public void join() {
    }
}
