package org.example;

public class Player {
    private String name;
    private char symbol;
    int timeRemaining;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.timeRemaining=0;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
