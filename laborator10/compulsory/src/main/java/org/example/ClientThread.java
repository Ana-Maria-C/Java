package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private GameServer server;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean stopped;

    public ClientThread(GameServer server, Socket socket) {
        this.server = server;
        this.clientSocket = socket;
        this.stopped = false;
    }

    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while (!stopped && (inputLine = in.readLine()) != null) {
                System.out.println("Received command: " + inputLine);
                // Process the command and send a response back to the client
                if (inputLine.equals("stop")) {
                    stopped = true;
                    out.println("Server stopped");
                } else {
                    out.println("Server received the request: " + inputLine);
                }
            }

            close();
        } catch (IOException e) {
            System.out.println("Error handling client request: " + e.getMessage());
        }
    }

    public void close() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error closing client socket: " + e.getMessage());
        }
        server.removeClient(this);
    }

    public synchronized boolean isStopped() {
        return stopped;
    }
}
