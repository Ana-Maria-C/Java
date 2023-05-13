package org.example;

public class MainClient {
    public static void main(String[] args) {
        String serverHost = "localhost";
        int serverPort = 8100;

        GameClient client = new GameClient(serverHost, serverPort);
        client.start();
    }
}
