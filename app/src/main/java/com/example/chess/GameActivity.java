package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chess.game.ChessGame;
import com.example.chess.game.piece.*;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

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
    static final int STALEMATED = 9;

    static final int ROOK = 0;
    static final int KNIGHT = 1;
    static final int BISHOP = 2;
    static final int QUEEN = 3;

    static final int MOVED_SQUARE = 0;
    static final int CHECKED_SQUARE = 1;

    private ImageView[][] squareImage;
    private ImageView[][] pieceImage;
    private TextView text;

    private ChessGame chessGame;
    private ArrayList<Integer[][]> backgrounds;
    private int prevSelectedFile, prevSelectedRank, selectedFile, selectedRank, index;
    private boolean preventMoves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        squareImage = new ImageView[8][8];
        pieceImage = new ImageView[8][8];
        bindBoardViews();
        text = (TextView) findViewById(R.id.player_turn_text);

        playAgain(null);
    }

    private void setDefaultButtons(int visibilityType) {
        findViewById(R.id.undo_button).setVisibility(visibilityType);
        findViewById(R.id.random_button).setVisibility(visibilityType);
        findViewById(R.id.draw_button).setVisibility(visibilityType);
        findViewById(R.id.resign_button).setVisibility(visibilityType);
    }

    private void setPromotionButtons(int visibilityType) {
        findViewById(R.id.promote_queen_button).setVisibility(visibilityType);
        findViewById(R.id.promote_knight_button).setVisibility(visibilityType);
        findViewById(R.id.promote_rook_button).setVisibility(visibilityType);
        findViewById(R.id.promote_bishop_button).setVisibility(visibilityType);
    }

    private void setGameOverButtons(int visibilityType) {
        findViewById(R.id.previous_button).setVisibility(visibilityType);
        findViewById(R.id.next_button).setVisibility(visibilityType);
        findViewById(R.id.play_again_button).setVisibility(visibilityType);
        findViewById(R.id.exit_button_game).setVisibility(visibilityType);
    }

    private void setPromotionIcons(boolean color) {
        if (color == WHITE) {
            ((ImageButton) findViewById(R.id.promote_queen_button)).setImageResource(R.drawable.ic_white_queen);
            ((ImageButton) findViewById(R.id.promote_knight_button)).setImageResource(R.drawable.ic_white_knight);
            ((ImageButton) findViewById(R.id.promote_rook_button)).setImageResource(R.drawable.ic_white_rook);
            ((ImageButton) findViewById(R.id.promote_bishop_button)).setImageResource(R.drawable.ic_white_bishop);
        } else {
            ((ImageButton) findViewById(R.id.promote_queen_button)).setImageResource(R.drawable.ic_black_queen);
            ((ImageButton) findViewById(R.id.promote_knight_button)).setImageResource(R.drawable.ic_black_knight);
            ((ImageButton) findViewById(R.id.promote_rook_button)).setImageResource(R.drawable.ic_black_rook);
            ((ImageButton) findViewById(R.id.promote_bishop_button)).setImageResource(R.drawable.ic_black_bishop);
        }
    }

    private void bindBoardViews() {
        squareImage[0][0] = findViewById(R.id.backgroundA1);
        pieceImage[0][0] = findViewById(R.id.pieceA1);
        squareImage[1][0] = findViewById(R.id.backgroundB1);
        pieceImage[1][0] = findViewById(R.id.pieceB1);
        squareImage[2][0] = findViewById(R.id.backgroundC1);
        pieceImage[2][0] = findViewById(R.id.pieceC1);
        squareImage[3][0] = findViewById(R.id.backgroundD1);
        pieceImage[3][0] = findViewById(R.id.pieceD1);
        squareImage[4][0] = findViewById(R.id.backgroundE1);
        pieceImage[4][0] = findViewById(R.id.pieceE1);
        squareImage[5][0] = findViewById(R.id.backgroundF1);
        pieceImage[5][0] = findViewById(R.id.pieceF1);
        squareImage[6][0] = findViewById(R.id.backgroundG1);
        pieceImage[6][0] = findViewById(R.id.pieceG1);
        squareImage[7][0] = findViewById(R.id.backgroundH1);
        pieceImage[7][0] = findViewById(R.id.pieceH1);

        squareImage[0][1] = findViewById(R.id.backgroundA2);
        pieceImage[0][1] = findViewById(R.id.pieceA2);
        squareImage[1][1] = findViewById(R.id.backgroundB2);
        pieceImage[1][1] = findViewById(R.id.pieceB2);
        squareImage[2][1] = findViewById(R.id.backgroundC2);
        pieceImage[2][1] = findViewById(R.id.pieceC2);
        squareImage[3][1] = findViewById(R.id.backgroundD2);
        pieceImage[3][1] = findViewById(R.id.pieceD2);
        squareImage[4][1] = findViewById(R.id.backgroundE2);
        pieceImage[4][1] = findViewById(R.id.pieceE2);
        squareImage[5][1] = findViewById(R.id.backgroundF2);
        pieceImage[5][1] = findViewById(R.id.pieceF2);
        squareImage[6][1] = findViewById(R.id.backgroundG2);
        pieceImage[6][1] = findViewById(R.id.pieceG2);
        squareImage[7][1] = findViewById(R.id.backgroundH2);
        pieceImage[7][1] = findViewById(R.id.pieceH2);

        squareImage[0][2] = findViewById(R.id.backgroundA3);
        pieceImage[0][2] = findViewById(R.id.pieceA3);
        squareImage[1][2] = findViewById(R.id.backgroundB3);
        pieceImage[1][2] = findViewById(R.id.pieceB3);
        squareImage[2][2] = findViewById(R.id.backgroundC3);
        pieceImage[2][2] = findViewById(R.id.pieceC3);
        squareImage[3][2] = findViewById(R.id.backgroundD3);
        pieceImage[3][2] = findViewById(R.id.pieceD3);
        squareImage[4][2] = findViewById(R.id.backgroundE3);
        pieceImage[4][2] = findViewById(R.id.pieceE3);
        squareImage[5][2] = findViewById(R.id.backgroundF3);
        pieceImage[5][2] = findViewById(R.id.pieceF3);
        squareImage[6][2] = findViewById(R.id.backgroundG3);
        pieceImage[6][2] = findViewById(R.id.pieceG3);
        squareImage[7][2] = findViewById(R.id.backgroundH3);
        pieceImage[7][2] = findViewById(R.id.pieceH3);

        squareImage[0][3] = findViewById(R.id.backgroundA4);
        pieceImage[0][3] = findViewById(R.id.pieceA4);
        squareImage[1][3] = findViewById(R.id.backgroundB4);
        pieceImage[1][3] = findViewById(R.id.pieceB4);
        squareImage[2][3] = findViewById(R.id.backgroundC4);
        pieceImage[2][3] = findViewById(R.id.pieceC4);
        squareImage[3][3] = findViewById(R.id.backgroundD4);
        pieceImage[3][3] = findViewById(R.id.pieceD4);
        squareImage[4][3] = findViewById(R.id.backgroundE4);
        pieceImage[4][3] = findViewById(R.id.pieceE4);
        squareImage[5][3] = findViewById(R.id.backgroundF4);
        pieceImage[5][3] = findViewById(R.id.pieceF4);
        squareImage[6][3] = findViewById(R.id.backgroundG4);
        pieceImage[6][3] = findViewById(R.id.pieceG4);
        squareImage[7][3] = findViewById(R.id.backgroundH4);
        pieceImage[7][3] = findViewById(R.id.pieceH4);

        squareImage[0][4] = findViewById(R.id.backgroundA5);
        pieceImage[0][4] = findViewById(R.id.pieceA5);
        squareImage[1][4] = findViewById(R.id.backgroundB5);
        pieceImage[1][4] = findViewById(R.id.pieceB5);
        squareImage[2][4] = findViewById(R.id.backgroundC5);
        pieceImage[2][4] = findViewById(R.id.pieceC5);
        squareImage[3][4] = findViewById(R.id.backgroundD5);
        pieceImage[3][4] = findViewById(R.id.pieceD5);
        squareImage[4][4] = findViewById(R.id.backgroundE5);
        pieceImage[4][4] = findViewById(R.id.pieceE5);
        squareImage[5][4] = findViewById(R.id.backgroundF5);
        pieceImage[5][4] = findViewById(R.id.pieceF5);
        squareImage[6][4] = findViewById(R.id.backgroundG5);
        pieceImage[6][4] = findViewById(R.id.pieceG5);
        squareImage[7][4] = findViewById(R.id.backgroundH5);
        pieceImage[7][4] = findViewById(R.id.pieceH5);

        squareImage[0][5] = findViewById(R.id.backgroundA6);
        pieceImage[0][5] = findViewById(R.id.pieceA6);
        squareImage[1][5] = findViewById(R.id.backgroundB6);
        pieceImage[1][5] = findViewById(R.id.pieceB6);
        squareImage[2][5] = findViewById(R.id.backgroundC6);
        pieceImage[2][5] = findViewById(R.id.pieceC6);
        squareImage[3][5] = findViewById(R.id.backgroundD6);
        pieceImage[3][5] = findViewById(R.id.pieceD6);
        squareImage[4][5] = findViewById(R.id.backgroundE6);
        pieceImage[4][5] = findViewById(R.id.pieceE6);
        squareImage[5][5] = findViewById(R.id.backgroundF6);
        pieceImage[5][5] = findViewById(R.id.pieceF6);
        squareImage[6][5] = findViewById(R.id.backgroundG6);
        pieceImage[6][5] = findViewById(R.id.pieceG6);
        squareImage[7][5] = findViewById(R.id.backgroundH6);
        pieceImage[7][5] = findViewById(R.id.pieceH6);

        squareImage[0][6] = findViewById(R.id.backgroundA7);
        pieceImage[0][6] = findViewById(R.id.pieceA7);
        squareImage[1][6] = findViewById(R.id.backgroundB7);
        pieceImage[1][6] = findViewById(R.id.pieceB7);
        squareImage[2][6] = findViewById(R.id.backgroundC7);
        pieceImage[2][6] = findViewById(R.id.pieceC7);
        squareImage[3][6] = findViewById(R.id.backgroundD7);
        pieceImage[3][6] = findViewById(R.id.pieceD7);
        squareImage[4][6] = findViewById(R.id.backgroundE7);
        pieceImage[4][6] = findViewById(R.id.pieceE7);
        squareImage[5][6] = findViewById(R.id.backgroundF7);
        pieceImage[5][6] = findViewById(R.id.pieceF7);
        squareImage[6][6] = findViewById(R.id.backgroundG7);
        pieceImage[6][6] = findViewById(R.id.pieceG7);
        squareImage[7][6] = findViewById(R.id.backgroundH7);
        pieceImage[7][6] = findViewById(R.id.pieceH7);

        squareImage[0][7] = findViewById(R.id.backgroundA8);
        pieceImage[0][7] = findViewById(R.id.pieceA8);
        squareImage[1][7] = findViewById(R.id.backgroundB8);
        pieceImage[1][7] = findViewById(R.id.pieceB8);
        squareImage[2][7] = findViewById(R.id.backgroundC8);
        pieceImage[2][7] = findViewById(R.id.pieceC8);
        squareImage[3][7] = findViewById(R.id.backgroundD8);
        pieceImage[3][7] = findViewById(R.id.pieceD8);
        squareImage[4][7] = findViewById(R.id.backgroundE8);
        pieceImage[4][7] = findViewById(R.id.pieceE8);
        squareImage[5][7] = findViewById(R.id.backgroundF8);
        pieceImage[5][7] = findViewById(R.id.pieceF8);
        squareImage[6][7] = findViewById(R.id.backgroundG8);
        pieceImage[6][7] = findViewById(R.id.pieceG8);
        squareImage[7][7] = findViewById(R.id.backgroundH8);
        pieceImage[7][7] = findViewById(R.id.pieceH8);
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

    private void drawBoard() {
        for (int i = 0; i < pieceImage.length; i++) {
            for (int j = 0; j < pieceImage[0].length; j++) {
                pieceImage[i][j].setImageResource(icon(chessGame.currentBoard[i][j]));
            }
        }
        drawBackground();
    }

    private void drawBackground() {
        Integer[][] currentBackground = backgrounds.get(backgrounds.size() - 1);
        for (int i = 0; i < pieceImage.length; i++) {
            for (int j = 0; j < pieceImage[0].length; j++) {
                if (currentBackground[i][j] != null) {
                    if (currentBackground[i][j] == MOVED_SQUARE) {
                        squareImage[i][j].setImageResource(R.drawable.ic_moved_square);
                    } else {
                        squareImage[i][j].setImageResource(R.drawable.ic_checked_square);
                    }
                } else {
                    squareImage[i][j].setImageResource(0);
                }
            }
        }
    }

    private void drawIndexedBoard() {
        Integer[][] currentBackground = backgrounds.get(index);
        for (int i = 0; i < pieceImage.length; i++) {
            for (int j = 0; j < pieceImage[0].length; j++) {
                pieceImage[i][j].setImageResource(icon(chessGame.game.get(index)[i][j]));
                if (currentBackground[i][j] != null) {
                    if (currentBackground[i][j] == MOVED_SQUARE) {
                        squareImage[i][j].setImageResource(R.drawable.ic_moved_square);
                    } else {
                        squareImage[i][j].setImageResource(R.drawable.ic_checked_square);
                    }
                } else {
                    squareImage[i][j].setImageResource(0);
                }
            }
        }
    }

    public void playAgain(View view) {
        text.setText(": Your turn to move.");
        setGameOverButtons(View.GONE);
        setDefaultButtons(View.VISIBLE);

        chessGame = new ChessGame();
        backgrounds = new ArrayList<>();
        backgrounds.add(new Integer[8][8]);
        prevSelectedFile = -1;
        prevSelectedRank = -1;
        index = -1;
        preventMoves = false;

        drawBoard();
    }

    public void boardClick(View view) {
        if (preventMoves) {
            return;
        }

        String square = view.getResources().getResourceName(view.getId());
        selectedFile = square.charAt(square.length() - 2) - 'A';
        selectedRank = square.charAt(square.length() - 1) - '1';
//
        if (prevSelectedFile == -1) {
            if (chessGame.currentBoard[selectedFile][selectedRank] != null && chessGame.currentBoard[selectedFile][selectedRank].color == chessGame.playerTurn) {
                prevSelectedFile = selectedFile;
                prevSelectedRank = selectedRank;
                squareImage[prevSelectedFile][prevSelectedRank].setImageResource(R.drawable.ic_selected_square);
            }
            return;
        }

        if (chessGame.currentBoard[selectedFile][selectedRank] != null && chessGame.currentBoard[selectedFile][selectedRank].color == chessGame.playerTurn) {
            drawBackground();
            prevSelectedFile = selectedFile;
            prevSelectedRank = selectedRank;
            squareImage[prevSelectedFile][prevSelectedRank].setImageResource(R.drawable.ic_selected_square);
            return;
        }

        int result = chessGame.move(prevSelectedFile, prevSelectedRank, selectedFile, selectedRank);

        if (result == PAWN_PROMOTION) {
            text.setText(": What to promote to?");
            setDefaultButtons(View.GONE);
            setPromotionButtons(View.VISIBLE);
            setPromotionIcons(chessGame.playerTurn);

            preventMoves = true;
            return;
        }

        Integer[][] background = new Integer[8][8];
        switch (result) {
            case CHECKED:
            case CHECKMATED:
                for (int i = 0; i < chessGame.currentBoard.length; i++) {
                    for (int j = 0; j < chessGame.currentBoard[0].length; j++) {
                        if (chessGame.currentBoard[i][j] instanceof King && chessGame.currentBoard[i][j].color == chessGame.playerTurn) {
                            background[i][j] = CHECKED_SQUARE;
                            squareImage[i][j].setImageResource(R.drawable.ic_checked_square);
                            break;
                        }
                    }
                }
            case STALEMATED:
            case VALID_MOVE:
                background[prevSelectedFile][prevSelectedRank] = MOVED_SQUARE;
                background[selectedFile][selectedRank] = MOVED_SQUARE;
                backgrounds.add(background);
                if (chessGame.playerTurn == WHITE) {
                    ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_white_king);
                } else {
                    ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_black_king);
                }
                text.setText(": Your turn to move.");
                break;
            default:
                text.setText(": Illegal move; try again.");
        }

        if (result == CHECKMATED || result == STALEMATED) {
            if (chessGame.playerTurn == WHITE) {
                ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_black_king);
            } else {
                ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_white_king);
            }
            text.setText(result == CHECKMATED ? ": Checkmate. You win!" : ": Stalemate. Draw!");
            setDefaultButtons(View.GONE);
            setGameOverButtons(View.VISIBLE);

            index = chessGame.game.size() - 1;
            preventMoves = true;
        }

        prevSelectedFile = -1;
        prevSelectedRank = -1;

        drawBoard();
    }

    public void promoteQueen(View view) {
        promote(QUEEN);
    }

    public void promoteKnight(View view) {
        promote(KNIGHT);
    }

    public void promoteRook(View view) {
        promote(ROOK);
    }

    public void promoteBishop(View view) {
        promote(BISHOP);
    }

    private void promote(int pieceType) {
        setPromotionButtons(View.GONE);
        setDefaultButtons(View.VISIBLE);

        int result = chessGame.promote(pieceType, selectedFile, selectedRank);
        chessGame.updateAllMoves(chessGame.currentBoard);

        Integer[][] background = new Integer[8][8];
        System.out.println(result);
        switch (result) {
            case CHECKED:
            case CHECKMATED:
                for (int i = 0; i < chessGame.currentBoard.length; i++) {
                    for (int j = 0; j < chessGame.currentBoard[0].length; j++) {
                        if (chessGame.currentBoard[i][j] instanceof King && chessGame.currentBoard[i][j].color == chessGame.playerTurn) {
                            background[i][j] = CHECKED_SQUARE;
                            squareImage[i][j].setImageResource(R.drawable.ic_checked_square);
                            break;
                        }
                    }
                }
            default:
                background[prevSelectedFile][prevSelectedRank] = MOVED_SQUARE;
                background[selectedFile][selectedRank] = MOVED_SQUARE;
                backgrounds.add(background);
                if (chessGame.playerTurn == WHITE) {
                    ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_white_king);
                } else {
                    ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_black_king);
                }
                text.setText(": Your turn to move.");
        }

        preventMoves = false;

        if (result == CHECKMATED || result == STALEMATED) {
            if (chessGame.playerTurn == WHITE) {
                ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_black_king);
            } else {
                ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_white_king);
            }
            text.setText(result == CHECKMATED ? ": Checkmate. You win!" : ": Stalemate. Draw!");
            setDefaultButtons(View.GONE);
            setGameOverButtons(View.VISIBLE);

            index = chessGame.game.size() - 1;
            preventMoves = true;
        }

        prevSelectedFile = -1;
        prevSelectedRank = -1;

        drawBoard();
    }

    public void undo(View view) {
        if (backgrounds.size() == 1) {
            return;
        }

        chessGame.undo();

        backgrounds.remove(backgrounds.size() - 1);
        if (chessGame.playerTurn == WHITE) {
            ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_white_king);
        } else {
            ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_black_king);
        }
        text.setText(": Your turn to move.");
        drawBoard();
    }

    public void random(View view) {
        int[] move = new int[4];
        int result = ILLEGAL_MOVE;
        while (result == ILLEGAL_MOVE || result == SELF_CHECK || result == ILLEGAL_CASTLE) {
            move = chessGame.getRandomMove(chessGame.currentBoard, chessGame.playerTurn);
            result = chessGame.move(move[0], move[1], move[2], move[3]);
        }

        if (result == PAWN_PROMOTION) {
            chessGame.promote((int) (4 * Math.random()), move[2], move[3]);
            chessGame.updateAllMoves(chessGame.currentBoard);
        }

        Integer[][] background = new Integer[8][8];
        switch (result) {
            case CHECKED:
            case CHECKMATED:
                for (int i = 0; i < chessGame.currentBoard.length; i++) {
                    for (int j = 0; j < chessGame.currentBoard[0].length; j++) {
                        if (chessGame.currentBoard[i][j] instanceof King && chessGame.currentBoard[i][j].color == chessGame.playerTurn) {
                            background[i][j] = CHECKED_SQUARE;
                            squareImage[i][j].setImageResource(R.drawable.ic_checked_square);
                            break;
                        }
                    }
                }
            default:
                background[move[0]][move[1]] = MOVED_SQUARE;
                background[move[2]][move[3]] = MOVED_SQUARE;
                backgrounds.add(background);
                if (chessGame.playerTurn == WHITE) {
                    ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_white_king);
                } else {
                    ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_black_king);
                }
                text.setText(": Your turn to move.");
        }

        if (result == CHECKMATED || result == STALEMATED) {
            if (chessGame.playerTurn == WHITE) {
                ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_black_king);
            } else {
                ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_white_king);
            }
            text.setText(result == CHECKMATED ? ": Checkmate. You win!" : ": Stalemate. Draw!");
            setDefaultButtons(View.GONE);
            setGameOverButtons(View.VISIBLE);

            index = chessGame.game.size() - 1;
            preventMoves = true;
        }

        prevSelectedFile = -1;
        prevSelectedRank = -1;

        drawBoard();
    }

    public void draw(View view) {
        text.setText(": Draw!");
        setDefaultButtons(View.GONE);
        setGameOverButtons(View.VISIBLE);

        findViewById(R.id.draw_button).setVisibility(View.GONE);

        index = chessGame.game.size() - 1;
        preventMoves = true;
    }

    public void resign(View view) {
        if (chessGame.playerTurn == WHITE) {
            ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_black_king);
        } else {
            ((ImageView) findViewById(R.id.player_turn_icon)).setImageResource(R.drawable.ic_white_king);
        }
        text.setText(": Resigned. You win!");
        setDefaultButtons(View.GONE);
        setGameOverButtons(View.VISIBLE);

        findViewById(R.id.resign_button).setVisibility(View.GONE);

        index = chessGame.game.size() - 1;
        preventMoves = true;
    }

    public void previous(View view) {
        if (index > 0) {
            index--;
            drawIndexedBoard();
        }
    }

    public void next(View view) {
        if (index < chessGame.game.size() - 1) {
            index++;
            drawIndexedBoard();
        }
    }

    public void exit(View view) {
        finish();
    }

}