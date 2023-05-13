package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

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
            clientSocket.setSoTimeout(30 * 1000);  // 30 de secunde pentru fiecare client
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
        } catch (IOException e){
            if(e instanceof SocketTimeoutException)
                System.err.println("GAME OVER:timeout expired!");
            else
                System.err.println("Communication error: " + e);
        } finally {
            try{
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                out.println("Server stopped");
                out.flush();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error: " + e);
            }
        }
    }

    public void close() {
        try {
            clientSocket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println("Error closing client socket: " + e.getMessage());
        }
        server.removeClient(this);
    }

    public synchronized boolean isStopped() {
        return stopped;
    }
}
