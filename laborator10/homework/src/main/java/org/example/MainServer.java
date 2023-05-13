package org.example;
public class MainServer {
    public static void main(String[] args) {
        int port = 8100;
        GameServer server = new GameServer(port);
        server.start();
    }
}