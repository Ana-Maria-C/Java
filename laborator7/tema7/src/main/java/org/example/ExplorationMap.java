package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ExplorationMap {
    private int size;
    private boolean[][] explored;
    private List<Token>[][] tokens;
    private Lock lock;

    public ExplorationMap(int size) {
        this.size = size;
        this.explored = new boolean[size][size];
        this.tokens = new ArrayList[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tokens[i][j] = new ArrayList<>();
            }
        }
        this.lock = new ReentrantLock();
    }

    public int getSize() {
        return size;
    }

    public Lock getLock() {
        return lock;
    }

    public boolean isExplored() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!explored[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isExplored(int x, int y) {
        return explored[x][y];
    }

    public void setExplored(int x, int y) {
        explored[x][y] = true;
    }

    public List<Token> getCellTokens(int x, int y) {
        lock.lock();
        try {
            return tokens[x][y];
        } finally {
            lock.unlock();
        }
    }

    public void setCellTokens(int x, int y, List<Token> tokens) {
        lock.lock();
        try {
            this.tokens[x][y] = tokens;
        } finally {
            lock.unlock();
        }
    }

    public int getTotalTokens() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                count += tokens[i][j].size();
            }
        }
        return count;
    }
}