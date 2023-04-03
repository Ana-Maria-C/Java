package org.example;

public class Token {
    private int value;

    public Token(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "value=" + value +
                '}';
    }
}