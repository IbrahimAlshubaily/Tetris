package org.example.model;

import java.util.HashMap;

public class Engine {
    int tick;
    HashMap<TetrisPiecePosition, TetrisPiece> tetrisPieces;
    Engine() {
        tick = 0;
        tetrisPieces = new HashMap<>();
    }

    public void step() {
        if (tick % 10 == 0) {
            tick = 0;
            tetrisPieces.put(new TetrisPiecePosition(), new TetrisPiece());
        }

        for (TetrisPiecePosition piece : tetrisPieces.keySet()) {
            piece.down();
        }
    }
}
