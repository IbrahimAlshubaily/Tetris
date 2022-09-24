package org.example.model;


public class TetrisPieceShape {
    private final static TetrisPieceBlock[] I = (new TetrisPieceBlock[] {
            new TetrisPieceBlock(0, 0),
            new TetrisPieceBlock(1, 0),
            new TetrisPieceBlock(2, 0),
            new TetrisPieceBlock(3, 0)});

    private final static TetrisPieceBlock[] L = (new TetrisPieceBlock[]{
            new TetrisPieceBlock(0, 0),
            new TetrisPieceBlock(1, 0),
            new TetrisPieceBlock(2, 0),
            new TetrisPieceBlock(3, 0),
            new TetrisPieceBlock(3, 1)});

    //T,
    //H,
    //Z,
    private final static TetrisPieceBlock[] E = (new TetrisPieceBlock[] {
            new TetrisPieceBlock(0, 0),
            new TetrisPieceBlock(1, 0),
            new TetrisPieceBlock(0, 1),
            new TetrisPieceBlock(0, 2),
            new TetrisPieceBlock(1, 2)});

    ;

    static final TetrisPieceBlock[][] shapes = new TetrisPieceBlock[][] {I, L, E};


    public static TetrisPieceBlock[] sample() {
        int sampleIdx = (int) Math.floor(Math.random() * shapes.length);
        return shapes[sampleIdx].clone();
    }
}
