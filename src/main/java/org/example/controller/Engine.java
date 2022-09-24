package org.example.controller;

import org.example.model.TetrisPiece;
import org.example.model.TetrisPieceBlock;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Engine {

    private boolean gameOver;

    TetrisPiece currTetrisPiece;
    ArrayList<TetrisPiece> frozenTetrisPieces;

    int nRows, nCols;
    public Engine(int nRows, int nCols, int stepInterval) {
        this.nRows = nRows;
        this.nCols = nCols;

        gameOver = false;
        currTetrisPiece = new TetrisPiece();
        frozenTetrisPieces = new ArrayList<>();


        TimerTask gameLoop = new TimerTask() {
            public void run() {
                if (!gameOver) {
                    step();
                }
            }
        };
        Timer timer = new Timer("GameLoopTimer" );
        timer.scheduleAtFixedRate(gameLoop, 0L, stepInterval);
    }

    private void step() {
        clearCompleteRows();
        down();
    }

    private void clearCompleteRows() {
        for (int row = nRows; row > 0; row--) {
            if(isCompleteRow(row)) {
                for (TetrisPiece piece: frozenTetrisPieces) {
                    if (piece.getMinRow() < row) {
                        piece.down();
                    }
                }
            }
        }
    }

    private boolean isCompleteRow(int row) {
        boolean[] cellIsOccupied = new boolean[nCols];
        int blockRow, blockCol;
        for (TetrisPiece piece: frozenTetrisPieces) {
            for (TetrisPieceBlock block: piece.getBlocks()) {
                blockRow = piece.getRow() + block.getRowOffset();
                if (blockRow == row) {
                    blockCol = piece.getCol() + block.getColOffset();
                    cellIsOccupied[blockCol] = true;
                }
            }
        }
        for (boolean isOccupied : cellIsOccupied) {
            if (!isOccupied)
                return false;
        }
        return true;
    }

    public void down() {
        if (canMoveDown()) {
            currTetrisPiece.down();
        } else {
            if (currTetrisPiece.getMinRow() < 0) {
                gameOver = true;
            }
            frozenTetrisPieces.add(currTetrisPiece);
            currTetrisPiece = new TetrisPiece();
        }
    }

    private boolean canMoveDown() {
        return currTetrisPiece.getMaxRow() < nRows - 1 && !isBlocked(1, 0);
    }

    public void left() {
        if (currTetrisPiece.getMinCol() > 0 && !isBlocked(0, -1)){
            currTetrisPiece.left();
        }
    }

    public void right() {
        if (currTetrisPiece.getMaxCol() < nCols - 1 && !isBlocked(0, 1)) {
            currTetrisPiece.right();
        }
    }

    private boolean isBlocked(int moveRowOffset, int moveColOffset) {

        for (TetrisPiece otherPiece : frozenTetrisPieces) {

            if (currTetrisPiece.isBlocked(otherPiece, moveRowOffset, moveColOffset)) {
                return true;
            }
        }
        return false;
    }


    public void drawPieces(Graphics g, int squareSize) {
        System.out.println(currTetrisPiece.getMaxRow());

        currTetrisPiece.draw(g, squareSize);
        for (TetrisPiece piece: frozenTetrisPieces) {
            piece.draw(g, squareSize);
        }
    }

    public boolean isGameOver() {
        return  gameOver;
    }

    public void rotate() {
        currTetrisPiece.rotateLeft();
    }
}
