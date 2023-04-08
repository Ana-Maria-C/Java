package org.example;


import java.util.concurrent.TimeUnit;

public class Timekeeper implements Runnable {
    private final int timeLimit;
    private final ExplorationMap map;

    public Timekeeper(int timeLimit, ExplorationMap map) {
        this.timeLimit = timeLimit;
        this.map = map;
    }

    public void run() {
        long startTime = System.currentTimeMillis();
        while (true) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Elapsed time: " + TimeUnit.MILLISECONDS.toSeconds(elapsedTime) + " seconds");

            if (elapsedTime >= timeLimit) {
                System.out.println("Time limit reached. Exploration aborted.");
                for(int i=0;i< map.getSize();i++)
                    for(int j=0;j< map.getSize();j++)
                         map.setExplored(i,j);
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
