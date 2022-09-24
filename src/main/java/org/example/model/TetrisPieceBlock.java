package org.example.model;

public class TetrisPieceBlock {
    private final int rowOffset;
    private final int colOffset;

    TetrisPieceBlock(int rowOffset, int colOffset) {
        this.rowOffset = rowOffset;
        this.colOffset = colOffset;
    }

    public TetrisPieceBlock rotateLeft() {
        return new TetrisPieceBlock(-colOffset, rowOffset);
    }

    public TetrisPieceBlock rotateRight() {
        return new TetrisPieceBlock(colOffset, -rowOffset);
    }

    public int getColOffset() {
        return colOffset;
    }

    public int getRowOffset() {
        return rowOffset;
    }
}
