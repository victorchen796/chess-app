package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.chess.game.ChessGame;
import com.example.chess.game.piece.*;

public class GameActivity extends AppCompatActivity {

    static final int DRAW = 0;
    static final int WHITE_RESIGNS = 1;
    static final int BLACK_RESIGNS = 2;
    static final int WHITE_CHECKMATES = 3;
    static final int BLACK_CHECKMATES = 4;

    static final boolean WHITE = false;
    static final boolean BLACK = true;

    static final int ILLEGAL_MOVE = 0;
    static final int SELF_CHECK = 1;
    static final int VALID_MOVE = 2;
    static final int ILLEGAL_CASTLE = 3;
    static final int PAWN_PROMOTION = 4;
    static final int NO_PIECE_EXISTS = 5;
    static final int INVALID_PLAYER_TURN = 6;
    static final int CHECKED = 7;
    static final int CHECKMATED = 8;

    private int[][] squareImage;
    private int[][] pieceImage;

    private ChessGame chessGame;
    private int selectedFile;
    private int selectedRank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        squareImage = new int[8][8];
        pieceImage = new int[8][8];
        {
            squareImage[0][0] = R.id.backgroundA1;
            pieceImage [0][0] = R.id.pieceA1;
            squareImage[1][0] = R.id.backgroundB1;
            pieceImage [1][0] = R.id.pieceB1;
            squareImage[2][0] = R.id.backgroundC1;
            pieceImage [2][0] = R.id.pieceC1;
            squareImage[3][0] = R.id.backgroundD1;
            pieceImage [3][0] = R.id.pieceD1;
            squareImage[4][0] = R.id.backgroundE1;
            pieceImage [4][0] = R.id.pieceE1;
            squareImage[5][0] = R.id.backgroundF1;
            pieceImage [5][0] = R.id.pieceF1;
            squareImage[6][0] = R.id.backgroundG1;
            pieceImage [6][0] = R.id.pieceG1;
            squareImage[7][0] = R.id.backgroundH1;
            pieceImage [7][0] = R.id.pieceH1;

            squareImage[0][1] = R.id.backgroundA2;
            pieceImage [0][1] = R.id.pieceA2;
            squareImage[1][1] = R.id.backgroundB2;
            pieceImage [1][1] = R.id.pieceB2;
            squareImage[2][1] = R.id.backgroundC2;
            pieceImage [2][1] = R.id.pieceC2;
            squareImage[3][1] = R.id.backgroundD2;
            pieceImage [3][1] = R.id.pieceD2;
            squareImage[4][1] = R.id.backgroundE2;
            pieceImage [4][1] = R.id.pieceE2;
            squareImage[5][1] = R.id.backgroundF2;
            pieceImage [5][1] = R.id.pieceF2;
            squareImage[6][1] = R.id.backgroundG2;
            pieceImage [6][1] = R.id.pieceG2;
            squareImage[7][1] = R.id.backgroundH2;
            pieceImage [7][1] = R.id.pieceH2;

            squareImage[0][2] = R.id.backgroundA3;
            pieceImage [0][2] = R.id.pieceA3;
            squareImage[1][2] = R.id.backgroundB3;
            pieceImage [1][2] = R.id.pieceB3;
            squareImage[2][2] = R.id.backgroundC3;
            pieceImage [2][2] = R.id.pieceC3;
            squareImage[3][2] = R.id.backgroundD3;
            pieceImage [3][2] = R.id.pieceD3;
            squareImage[4][2] = R.id.backgroundE3;
            pieceImage [4][2] = R.id.pieceE3;
            squareImage[5][2] = R.id.backgroundF3;
            pieceImage [5][2] = R.id.pieceF3;
            squareImage[6][2] = R.id.backgroundG3;
            pieceImage [6][2] = R.id.pieceG3;
            squareImage[7][2] = R.id.backgroundH3;
            pieceImage [7][2] = R.id.pieceH3;

            squareImage[0][3] = R.id.backgroundA4;
            pieceImage [0][3] = R.id.pieceA4;
            squareImage[1][3] = R.id.backgroundB4;
            pieceImage [1][3] = R.id.pieceB4;
            squareImage[2][3] = R.id.backgroundC4;
            pieceImage [2][3] = R.id.pieceC4;
            squareImage[3][3] = R.id.backgroundD4;
            pieceImage [3][3] = R.id.pieceD4;
            squareImage[4][3] = R.id.backgroundE4;
            pieceImage [4][3] = R.id.pieceE4;
            squareImage[5][3] = R.id.backgroundF4;
            pieceImage [5][3] = R.id.pieceF4;
            squareImage[6][3] = R.id.backgroundG4;
            pieceImage [6][3] = R.id.pieceG4;
            squareImage[7][3] = R.id.backgroundH4;
            pieceImage [7][3] = R.id.pieceH4;

            squareImage[0][4] = R.id.backgroundA5;
            pieceImage [0][4] = R.id.pieceA5;
            squareImage[1][4] = R.id.backgroundB5;
            pieceImage [1][4] = R.id.pieceB5;
            squareImage[2][4] = R.id.backgroundC5;
            pieceImage [2][4] = R.id.pieceC5;
            squareImage[3][4] = R.id.backgroundD5;
            pieceImage [3][4] = R.id.pieceD5;
            squareImage[4][4] = R.id.backgroundE5;
            pieceImage [4][4] = R.id.pieceE5;
            squareImage[5][4] = R.id.backgroundF5;
            pieceImage [5][4] = R.id.pieceF5;
            squareImage[6][4] = R.id.backgroundG5;
            pieceImage [6][4] = R.id.pieceG5;
            squareImage[7][4] = R.id.backgroundH5;
            pieceImage [7][4] = R.id.pieceH5;

            squareImage[0][5] = R.id.backgroundA6;
            pieceImage [0][5] = R.id.pieceA6;
            squareImage[1][5] = R.id.backgroundB6;
            pieceImage [1][5] = R.id.pieceB6;
            squareImage[2][5] = R.id.backgroundC6;
            pieceImage [2][5] = R.id.pieceC6;
            squareImage[3][5] = R.id.backgroundD6;
            pieceImage [3][5] = R.id.pieceD6;
            squareImage[4][5] = R.id.backgroundE6;
            pieceImage [4][5] = R.id.pieceE6;
            squareImage[5][5] = R.id.backgroundF6;
            pieceImage [5][5] = R.id.pieceF6;
            squareImage[6][5] = R.id.backgroundG6;
            pieceImage [6][5] = R.id.pieceG6;
            squareImage[7][5] = R.id.backgroundH6;
            pieceImage [7][5] = R.id.pieceH6;

            squareImage[0][6] = R.id.backgroundA7;
            pieceImage [0][6] = R.id.pieceA7;
            squareImage[1][6] = R.id.backgroundB7;
            pieceImage [1][6] = R.id.pieceB7;
            squareImage[2][6] = R.id.backgroundC7;
            pieceImage [2][6] = R.id.pieceC7;
            squareImage[3][6] = R.id.backgroundD7;
            pieceImage [3][6] = R.id.pieceD7;
            squareImage[4][6] = R.id.backgroundE7;
            pieceImage [4][6] = R.id.pieceE7;
            squareImage[5][6] = R.id.backgroundF7;
            pieceImage [5][6] = R.id.pieceF7;
            squareImage[6][6] = R.id.backgroundG7;
            pieceImage [6][6] = R.id.pieceG7;
            squareImage[7][6] = R.id.backgroundH7;
            pieceImage [7][6] = R.id.pieceH7;

            squareImage[0][7] = R.id.backgroundA8;
            pieceImage [0][7] = R.id.pieceA8;
            squareImage[1][7] = R.id.backgroundB8;
            pieceImage [1][7] = R.id.pieceB8;
            squareImage[2][7] = R.id.backgroundC8;
            pieceImage [2][7] = R.id.pieceC8;
            squareImage[3][7] = R.id.backgroundD8;
            pieceImage [3][7] = R.id.pieceD8;
            squareImage[4][7] = R.id.backgroundE8;
            pieceImage [4][7] = R.id.pieceE8;
            squareImage[5][7] = R.id.backgroundF8;
            pieceImage [5][7] = R.id.pieceF8;
            squareImage[6][7] = R.id.backgroundG8;
            pieceImage [6][7] = R.id.pieceG8;
            squareImage[7][7] = R.id.backgroundH8;
            pieceImage [7][7] = R.id.pieceH8;
        }

        chessGame = new ChessGame();
        selectedFile = 4;
        selectedRank = 4;
        drawBoard();
    }

    private void drawBoard() {
        ImageView view;
        for (int i = 0; i < pieceImage.length; i++) {
            for (int j = 0; j < pieceImage[0].length; j++) {
                view = (ImageView) findViewById(pieceImage[i][j]);
                view.setImageResource(icon(chessGame.currentBoard[i][j]));
            }
        }
    }

    private int icon(Piece p) {
        if (p == null){
            return 0;
        } else if (p.color == WHITE) {
            if (p instanceof Pawn) return R.drawable.ic_white_pawn;
            if (p instanceof Rook) return R.drawable.ic_white_rook;
            if (p instanceof Knight) return R.drawable.ic_white_knight;
            if (p instanceof Bishop) return R.drawable.ic_white_bishop;
            if (p instanceof Queen) return R.drawable.ic_white_queen;
            if (p instanceof King) return R.drawable.ic_white_king;
        } else {
            if (p instanceof Pawn) return R.drawable.ic_black_pawn;
            if (p instanceof Rook) return R.drawable.ic_black_rook;
            if (p instanceof Knight) return R.drawable.ic_black_knight;
            if (p instanceof Bishop) return R.drawable.ic_black_bishop;
            if (p instanceof Queen) return R.drawable.ic_black_queen;
            if (p instanceof King) return R.drawable.ic_black_king;
        }
        return 0;
    }

}