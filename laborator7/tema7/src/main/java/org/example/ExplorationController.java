package org.example;


import java.util.Scanner;

public class ExplorationController {
    private Exploration exploration;
    private Scanner scanner;

    public ExplorationController(int mapSize, int numRobots, int tokensPerCell) {
        this.exploration = new Exploration(mapSize, numRobots, tokensPerCell);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        exploration.start();
        System.out.println("Exploration started.");
        while (true) {
            System.out.println("Enter command (start/pause/resume/quit):");
            String command = scanner.nextLine();
            if (command.equals("start")) {
                exploration.start();
                System.out.println("Exploration resumed.");
            } else if (command.startsWith("pause")) {
                String[] tokens = command.split(" ");
                int robotIndex = Integer.parseInt(tokens[1]);
                long pauseTime = Long.parseLong(tokens[2]);
                pauseRobot(robotIndex, pauseTime);
            } else if (command.equals("resume")) {
                exploration.resume();
                System.out.println("Exploration resumed.");
            } else if (command.equals("quit")) {
                exploration.pause();
                System.out.println("Exploration paused. Goodbye!");
                break;
            } else {
                System.out.println("Invalid command.");
            }
        }
    }

    private void pauseRobot(int index, long pauseTime) {
        Thread thread = exploration.getThread(index);
        if (thread != null) {
            thread.interrupt();
            if (pauseTime > 0) {
                try {
                    Thread.sleep(pauseTime);
                } catch (InterruptedException e) {
                    // ignore
                }
            }
            exploration.start();
            System.out.println("Robot " + index + " paused.");
        } else {
            System.out.println("Invalid robot index.");
        }
    }

    public static void main(String[] args) {
        ExplorationController controller = new ExplorationController(10, 5, 2);
        controller.start();
    }
}
