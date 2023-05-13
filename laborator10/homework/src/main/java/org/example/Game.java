package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private Player current_player;
    private int time_limit;
    private String name;

    public Game(String name, int time_limit) {
        this.name=name;
        this.board = new Board();
        this.players = new ArrayList<>();
        this.current_player = null;
        this.time_limit = time_limit;
    }

    public void add_player(String name, char symbol) {
        Player player = new Player(name, symbol);
        this.players.add(player);
    }

    public void start() {
        for (Player player : this.players) {
            player.timeRemaining = this.time_limit;
        }
        this.current_player = this.players.get(0);
    }

    public void next_player() {
        int current_index = this.players.indexOf(this.current_player);
        int next_index = (current_index + 1) % this.players.size();
        this.current_player = this.players.get(next_index);
    }

    public String submit_move(int i, int j) {
        if (this.board.getBoardGame()[i][j] != ' ') {
            return "full";
        }
        this.board.getBoardGame()[i][j] = this.current_player.getSymbol();
        if (this.board.isWin(this.current_player.getSymbol())) {
            return "true";
        }
        if (this.board.isFull()) {
            return "tie";
        }
        this.next_player();
        return "false";
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrent_player() {
        return current_player;
    }

    public int getTime_limit() {
        return time_limit;
    }

    public String getName() {
        return name;
    }
}
