package org.example;

public class Board {
    private int[][] boardGame;
    private int size;

    public Board() {
        this.size = 15;
        boardGame=new int[size][size];
    }
    public boolean isFull()
    {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(boardGame[i][j]==0)
                {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isWin(char symbol) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (boardGame[i][j] == symbol) {
                    if (checkHorizontal(i, j, symbol) ||
                            checkVertical(i, j, symbol) ||
                            checkDiagonal1(i, j, symbol) ||
                            checkDiagonal2(i, j, symbol)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkHorizontal(int i, int j, char symbol) {
        if (j + 4 >= size) {
            return false;
        }
        for (int k = 1; k < 5; k++) {
            if (boardGame[i][j+k] != symbol) {
                return false;
            }
        }
        return true;
    }

    public boolean checkVertical(int i, int j, char symbol) {
        if (i + 4 >= size) {
            return false;
        }
        for (int k = 1; k < 5; k++) {
            if (boardGame[i+k][j] != symbol) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDiagonal1(int i, int j, char symbol) {
        if (i + 4 >= size || j + 4 >= size) {
            return false;
        }
        for (int k = 1; k < 5; k++) {
            if (boardGame[i+k][j+k] != symbol) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDiagonal2(int i, int j, char symbol) {
        if (i - 4 < 0 || j + 4 >= size) {
            return false;
        }
        for (int k = 1; k < 5; k++) {
            if (boardGame[i-k][j+k] != symbol) {
                return false;
            }
        }
        return true;
    }

    public int[][] getBoardGame() {
        return boardGame;
    }

    public int getSize() {
        return size;
    }
}
