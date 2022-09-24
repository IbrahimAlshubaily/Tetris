package org.example.model;

import java.awt.*;
import java.util.Arrays;

public class TetrisPiece {

    TetrisPiecePosition position;
    TetrisPieceBlock[] blocks;

    public TetrisPiece() {
        position = new TetrisPiecePosition();
        this.blocks = TetrisPieceShape.sample().tetrisPieceBlocks;
    }

    public int getMaxRow() {
        return position.getRow() + Arrays.stream(blocks).mapToInt((block) -> block.rowOffset).max().getAsInt();
    }

    public int getMinRow() {
        return position.getRow() + Arrays.stream(blocks).mapToInt((block) -> block.rowOffset).min().getAsInt();
    }

    public int getMaxCol() {
        return position.getCol() + Arrays.stream(blocks).mapToInt((block) -> block.colOffset).max().getAsInt();
    }
    public int getMinCol() {
        return position.getCol() + Arrays.stream(blocks).mapToInt((block) -> block.colOffset).min().getAsInt();
    }


    public void down() {
        position.down();
    }

    public void left() {
        position.left();
    }
    public void right() {
        position.right();
    }


    public boolean isBlocked(TetrisPiece otherPiece, int moveRowOffset, int moveColOffset) {

        if (this == otherPiece) {
            return false;
        }

        int row, col;
        for (TetrisPieceBlock block : blocks) {

            row = position.getRow() + block.rowOffset + moveRowOffset;
            col = position.getCol() + block.colOffset + moveColOffset;

            int otherRow, otherCol;
            for (TetrisPieceBlock otherBlock : otherPiece.blocks) {

                otherRow = otherPiece.position.getRow() + otherBlock.rowOffset;
                otherCol = otherPiece.position.getCol() + otherBlock.colOffset;

                if (row == otherRow && col == otherCol) {
                    return true;
                }
            }
        }
        return false;
    }

    public void draw(Graphics g, int squareSize) {
        int x, y;
        for (TetrisPieceBlock block : blocks) {
            x = (position.getCol() + block.colOffset) * squareSize;
            y = (position.getRow() + block.rowOffset) * squareSize;
            g.fillRect(x, y, squareSize, squareSize);
        }
    }
}


