package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedMemory {
    private List<Token> tokens;
    private Lock lock;

    public SharedMemory(int size) {
        this.tokens = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            tokens.add(new Token(i));
        }
        Collections.shuffle(tokens);
        this.lock = new ReentrantLock();
    }

    public Lock getLock() {
        return lock;
    }

    public List<Token> extractTokens(int n) {
        lock.lock();
        try {
            List<Token> extracted = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                extracted.add(tokens.remove(0));
            }
            return extracted;
        } finally {
            lock.unlock();
        }
    }
}