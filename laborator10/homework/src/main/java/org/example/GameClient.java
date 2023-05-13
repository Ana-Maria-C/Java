package org.example;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
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
            /**
             * creem o lista de jocuri pentru a putea identifica fiecare joc prin numele sau;
             */
            List<Game> games = new ArrayList<>();
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
                            /**
                             * verificam daca numele dat nu este deja atribuit unui alt joc
                             */
                            int rightname=1;
                            for(int i=0;i<games.size();i++)
                            {
                                if(games.get(i).getName()==gameName)
                                {
                                    rightname=0;
                                    break;
                                }
                            }
                            if(rightname!=0)
                            {
                            System.out.print("Enter player name: ");
                            playerName = scanner.nextLine();
                            sendMessage(socket, "create:" + gameName + ":" + playerName);
                            /**
                             * creem primul player caruia i se va atribui simbolul'a', adica va fi representat in joc de culoarea alb;
                             */
                            Player player1=new Player(playerName,'a');
                            /**
                             * creem un nou joc cu o tabla goala de 15/15 si o limita de timp de 30 de secunde pentru fiecare comanda
                             * la care adaugam soi playerul ce a creat jocul;
                             */
                            Game game=new Game(gameName,30);
                            games.add(game);
                            game.add_player(playerName,'a');
                            }
                            else
                            {
                                System.out.print("This name is already taken by another game, please choose another one!"+'\n');
                                System.out.print("Enter game name: ");
                                gameName = scanner.nextLine();
                                sendMessage(socket, "wrong name");
                            }
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
                            /**
                             * verificam daca jocul ales nu are deja doi jucatori, caz in care playerul curent va trebui sa aleaga alt joc
                             * sau sa creeze unul in caz ca nu exista al joc
                             */
                            int rightname=0;
                            int rightgame=0;
                            int id=0;
                            for(int i=0;i<games.size();i++)
                            {
                                if(games.get(i).getName()==gameName)
                                {
                                    rightname=1;
                                    if(games.get(i).getPlayers().size()==1)
                                    {
                                        id=i;
                                        rightgame=1;
                                        break;
                                    }

                                }
                            }
                            if(rightname!=0 && rightgame!=0)
                            {
                            System.out.print("Enter player name: ");
                            playerName = scanner.nextLine();
                            sendMessage(socket, "join:" + gameName + ":" + playerName);
                            /**
                             * creem al doilea player al jocului (este al doilea deoarece s-a alaturat unui joc existent), ce va fi reprezentat de culoarea negru
                             */
                            Player player2=new Player(playerName,'n');
                            games.get(id).add_player(playerName,'n');
                                /**
                                 * dupca ce s-au alaturat cei doi jucatori, incepe jocul
                                 */
                                games.get(id).start();
                            }
                            else
                            {
                                if(rightname==0)
                                {
                                    System.out.print("This is no game with this name, please choose another one!"+'\n');
                                    System.out.print("Enter game name: ");
                                    gameName = scanner.nextLine();
                                    sendMessage(socket, "wrong name");
                                }
                            }
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
                            System.out.print("Enter the game name: ");
                            String currentgame = scanner.nextLine();
                            System.out.print("Enter x coordinate: ");
                            int x = scanner.nextInt();
                            System.out.print("Enter y coordinate: ");
                            int y = scanner.nextInt();
                            scanner.nextLine();
                            /**
                             * executam miscarea in jocul currentgame
                             */
                            for(int i=0;i<games.size();i++)
                            {
                                if(games.get(i).getName()==currentgame)
                                {
                                    String answer=games.get(i).submit_move(x,y);
                                    if(answer=="true")
                                    {
                                        System.out.print( games.get(i).getCurrent_player()+" won!"+'\n');
                                        break;
                                    }
                                    else
                                        if(answer=="tie")
                                        {
                                            System.out.print( "Game over!"+'\n');
                                            break;
                                        }
                                        else
                                            if(answer=="full")
                                            {
                                                while(games.get(i).submit_move(x,y)=="full") {
                                                    System.out.print(" Wrong coordinates!");
                                                    System.out.print("Enter x coordinate: ");
                                                    x = scanner.nextInt();
                                                    System.out.print("Enter y coordinate: ");
                                                    y = scanner.nextInt();
                                                    scanner.nextLine();
                                                }

                                            }
                                }

                            }
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
