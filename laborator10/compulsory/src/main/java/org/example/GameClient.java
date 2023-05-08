package org.example;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * lista de comenzi
 * commands[0]=create
 * commands[1]=join
 * commands[2]=move
 * commands[3]=exit
 */
public class GameClient {
    private String host;
    private int port;
    private Scanner scanner;
    private int[] commands;

    public GameClient(String host, int port) {
        this.host = host;
        this.port = port;
        scanner = new Scanner(System.in);
        commands=new int[4];
    }

    public void start() {
        try (Socket socket = new Socket(host, port)) {
            String playerName="";
            String gameName="";
            System.out.println("Connected to server");
            while (true) {
                System.out.print("Enter command (create/join/move/exit): ");
                String command = scanner.nextLine();
                if (command.equals("exit")) {
                    commands[0]=0;
                    commands[1]=0;
                    break;
                }
                switch (command) {
                    case "create":
                        if(commands[1]==0 && commands[0]==0)
                        {
                            commands[0]=1;
                            System.out.print("Enter game name: ");
                            gameName = scanner.nextLine();
                            System.out.print("Enter player name: ");
                            playerName = scanner.nextLine();
                            sendMessage(socket, "create:" + gameName + ":" + playerName);

                        }
                        else
                        {
                            System.out.print("You already are in a game!"+'\n');
                            sendMessage(socket, "wrong command");

                        }
                          break;
                    case "join":
                        if(commands[0]==0 && commands[1]==0)
                        {
                            commands[1]=1;
                            System.out.print("Enter game name: ");
                            gameName = scanner.nextLine();
                            System.out.print("Enter player name: ");
                            playerName = scanner.nextLine();
                            sendMessage(socket, "join:" + gameName + ":" + playerName);

                        }
                        else
                        {
                            System.out.print("You already are in a game!"+'\n');
                            sendMessage(socket, "wrong command");
                        }
                          break;
                    case "move":
                        if(commands[0]+commands[1]>0)
                        {
                            System.out.print("Enter x coordinate: ");
                            int x = scanner.nextInt();
                            System.out.print("Enter y coordinate: ");
                            int y = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            sendMessage(socket, "move:" + gameName + ":" + playerName + ":" + x + ":" + y);
                        }
                        else
                        {
                            System.out.print("You can't make a move if you're not in a game!"+'\n');
                            sendMessage(socket, "wrong command");
                        }
                         break;
                    default:
                        System.out.println("Invalid command");
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void sendMessage(Socket socket, String message) {
        try {
            socket.getOutputStream().write((message + "\n").getBytes());
        } catch (IOException e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
    }

   /* public static void main(String[] args) {
        GameClient client = new GameClient("localhost", 1234);
        client.start();
    }
    */
}
