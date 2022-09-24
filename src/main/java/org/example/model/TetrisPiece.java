package org.example.model;

import java.awt.*;
import java.util.Arrays;

public class TetrisPiece {

    private TetrisPiecePosition position;
    private TetrisPieceBlock[] blocks;

    public TetrisPiece() {
        position = new TetrisPiecePosition();
        this.blocks = TetrisPieceShape.sample();
    }

    public int getMaxRow() {
        return position.getRow() + Arrays.stream(blocks).mapToInt((block) -> block.getRowOffset()).max().getAsInt();
    }

    public int getMinRow() {
        return position.getRow() + Arrays.stream(blocks).mapToInt((block) -> block.getRowOffset()).min().getAsInt();
    }

    public int getMaxCol() {
        return position.getCol() + Arrays.stream(blocks).mapToInt((block) -> block.getColOffset()).max().getAsInt();
    }
    public int getMinCol() {
        return position.getCol() + Arrays.stream(blocks).mapToInt((block) -> block.getColOffset()).min().getAsInt();
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

    public void rotateLeft() {
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = blocks[i].rotateLeft();
        }
    }


    public boolean isBlocked(TetrisPiece otherPiece, int moveRowOffset, int moveColOffset) {

        if (this == otherPiece) {
            return false;
        }

        int row, col;
        for (TetrisPieceBlock block : blocks) {

            row = position.getRow() + block.getRowOffset() + moveRowOffset;
            col = position.getCol() + block.getColOffset() + moveColOffset;

            int otherRow, otherCol;
            for (TetrisPieceBlock otherBlock : otherPiece.blocks) {

                otherRow = otherPiece.position.getRow() + otherBlock.getRowOffset();
                otherCol = otherPiece.position.getCol() + otherBlock.getColOffset();

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
            x = (position.getCol() + block.getColOffset()) * squareSize;
            y = (position.getRow() + block.getRowOffset()) * squareSize;
            g.fillRect(x, y, squareSize, squareSize);
        }
    }

    public TetrisPieceBlock[] getBlocks() {
        return blocks;
    }

    public int getCol() {
        return position.getCol();
    }

    public int getRow() {
        return position.getRow();
    }
}


