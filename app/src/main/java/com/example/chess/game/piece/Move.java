package com.example.chess.game.piece;

public class Move {

    public int file;
    public int rank;
    public int type;

    public Move(int file, int rank, int type) {
        this.file = file;
        this.rank = rank;
        this.type = type;
    }

}
