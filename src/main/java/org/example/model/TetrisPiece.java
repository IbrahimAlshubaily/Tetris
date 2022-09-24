package org.example.model;

public class TetrisPiece {

    TetrisPieceBlock[] myBlocks;

    TetrisPiece() {
        this.myBlocks = TetrisPieceShape.sample().tetrisPieceBlocks;
    }


}


