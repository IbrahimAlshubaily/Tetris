package org.example.model;


public enum TetrisPieceShape {
    I(new TetrisPieceBlock[] {
            new TetrisPieceBlock(0, 0),
            new TetrisPieceBlock(1, 0),
            new TetrisPieceBlock(2, 0),
            new TetrisPieceBlock(3, 0)}),

    L(new TetrisPieceBlock[]{
            new TetrisPieceBlock(0, 0),
            new TetrisPieceBlock(1, 0),
            new TetrisPieceBlock(2, 0),
            new TetrisPieceBlock(3, 0),
            new TetrisPieceBlock(3, 1)}),

    //T,
    //H,
    //Z,
    E(new TetrisPieceBlock[] {
            new TetrisPieceBlock(0, 0),
            new TetrisPieceBlock(1, 0),
            new TetrisPieceBlock(0, 1),
            new TetrisPieceBlock(0, 2),
            new TetrisPieceBlock(1, 2)}),

    ;

    final TetrisPieceBlock[] tetrisPieceBlocks;
    TetrisPieceShape(TetrisPieceBlock[] tetrisPieceBlocks) {
        this.tetrisPieceBlocks = tetrisPieceBlocks;
    }

    public static TetrisPieceShape sample() {
        int sampleIdx = (int) Math.round(Math.random() * values().length);
        return values()[sampleIdx];
    }
}
