package org.example.controller;

import org.example.model.TetrisPiece;

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
                    down();
                }
            }
        };
        Timer timer = new Timer("GameLoopTimer" );
        timer.scheduleAtFixedRate(gameLoop, 0L, stepInterval);
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
}
