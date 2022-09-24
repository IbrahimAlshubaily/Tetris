package org.example.model;

public class TetrisPiecePosition {
    private int row = -5;
    private int col = 3;

    public void down() {
        this.row++;
    }

    public void left() {
        this.col--;
    }
    public void right() {
        this.col++;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
