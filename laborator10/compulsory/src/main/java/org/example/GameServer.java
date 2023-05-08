package org.example;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private ServerSocket serverSocket;
    private int port;
    private boolean stopped;
    private List<ClientThread> clients;

    public GameServer(int port) {
        this.port = port;
        this.stopped = false;
        this.clients = new ArrayList<>();
    }

    public void start() {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (!stopped) {
                Socket socket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(this, socket);
                clients.add(clientThread);
                clientThread.start();
            }

            System.out.println("Server stopped");
            serverSocket.close();

            for (ClientThread client : clients) {
                client.close();
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }

    public synchronized void stop() {
        stopped = true;
    }

    public synchronized boolean isStopped() {
        return stopped;
    }

    public synchronized void removeClient(ClientThread client) {
        clients.remove(client);
    }

}
