package org.example.model;

import java.awt.*;
import java.util.Arrays;

public class TetrisPiece {

    private final TetrisPiecePosition position;
    private final TetrisPieceBlock[] blocks;

    public TetrisPiece() {
        position = new TetrisPiecePosition();
        this.blocks = TetrisPieceShape.sample();
    }

    public int getMaxRow() {
        return position.getRow() + Arrays.stream(blocks).mapToInt(TetrisPieceBlock::getRowOffset).max().getAsInt();
    }

    public int getMinRow() {
        return position.getRow() + Arrays.stream(blocks).mapToInt(TetrisPieceBlock::getRowOffset).min().getAsInt();
    }

    public int getMaxCol() {
        return position.getCol() + Arrays.stream(blocks).mapToInt(TetrisPieceBlock::getColOffset).max().getAsInt();
    }
    public int getMinCol() {
        return position.getCol() + Arrays.stream(blocks).mapToInt(TetrisPieceBlock::getColOffset).min().getAsInt();
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

    public void rotateLeft(int nRows, int nCols) {
        boolean reverse = false;
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = blocks[i].rotateLeft();
            reverse = blockIsOutOfBounds(blocks[i], nRows, nCols);
        }
        if (reverse)
            rotateRight(nRows, nCols);
    }

    public void rotateRight(int nRows, int nCols) {
        boolean reverse = false;
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = blocks[i].rotateRight();
            reverse = blockIsOutOfBounds(blocks[i], nRows, nCols);
        }
        if (reverse)
            rotateLeft(nRows, nCols);
    }

    private boolean blockIsOutOfBounds(TetrisPieceBlock block, int nRows, int nCols) {
        int blockCol = position.getCol() + block.getColOffset();
        int blockRow = position.getRow() + block.getRowOffset();
        return blockCol < 0 || blockCol > nCols  ||  blockRow > nRows;
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


