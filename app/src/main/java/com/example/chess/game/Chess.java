package com.example.chess.game;

import java.util.Scanner;
import com.example.chess.game.piece.*;

public class Chess {

    static final int DRAW = 0;
    static final int WHITE_RESIGNS = 1;
    static final int BLACK_RESIGNS = 2;
    static final int WHITE_CHECKMATES = 3;
    static final int BLACK_CHECKMATES = 4;

    static final int ILLEGAL_MOVE = 0;
    static final int SELF_CHECK = 1;
    static final int VALID_MOVE = 2;
    static final int ILLEGAL_CASTLE = 3;
    static final int PAWN_PROMOTION = 4;
    static final int NO_PIECE_EXISTS = 5;
    static final int INVALID_PLAYER_TURN = 6;
    static final int CHECKED = 7;
    static final int CHECKMATED = 8;

    static final int ROOK = 0;
    static final int KNIGHT = 1;
    static final int BISHOP = 2;
    static final int QUEEN = 3;

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        Scanner scan = new Scanner(System.in);
        int outcome = -1;
        while (outcome != WHITE_CHECKMATES && outcome != BLACK_CHECKMATES) {
            drawBoard(game.currentBoard);
            System.out.print("\n" + (game.playerTurn ? "Black" : "White") + "'s turn: ");
            String input = scan.nextLine();
            if (input.equals("undo")) {
                game.undo();
                System.out.println();
                continue;
            }

            int file = input.charAt(0) - 'a';
            int rank = input.charAt(1) - '1';
            int newFile = input.charAt(3) - 'a';
            int newRank = input.charAt(4) - '1';
            int result = game.move(file, rank, newFile, newRank);

            if (result == NO_PIECE_EXISTS) {
                System.out.println("No piece exists at " + input.charAt(0) + input.charAt(1));
                System.out.println();
                continue;
            } else if (result == ILLEGAL_MOVE) {
                System.out.println("Illegal move");
                System.out.println();
                continue;
            } else if (result == SELF_CHECK) {
                System.out.println("King is under check");
                System.out.println();
                continue;
            } else if (result == VALID_MOVE || result == CHECKED || result == CHECKMATED) {
                System.out.println("Move successful");
            } else if (result == ILLEGAL_CASTLE) {
                System.out.println("Cannot castle while king is under check; cannot move king through threatened "
                        + "squares");
                System.out.println();
                continue;
            } else if (result == PAWN_PROMOTION) {
                System.out.print("Promote pawn to: ");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("r")) {
                    result = game.promote(ROOK, newFile, newRank);
                } else if (input.equalsIgnoreCase("n")) {
                    result = game.promote(KNIGHT, newFile, newRank);
                } else if (input.equalsIgnoreCase("b")) {
                    result = game.promote(BISHOP, newFile, newRank);
                } else if (input.equalsIgnoreCase("q")) {
                    result = game.promote(QUEEN, newFile, newRank);
                } else {
                    return;
                }
                game.updateAllMoves(game.currentBoard);
                System.out.println("Promotion successful");
            } else if (result == INVALID_PLAYER_TURN) {
                System.out.println("Cannot move opponent's pieces");
                System.out.println();
                continue;
            }

            if (result == CHECKED) {
                System.out.println("Opponent has been checked");
            } else if (result == CHECKMATED) {
                System.out.print("Opponent has been checkmated");
                outcome = game.gameEndType;
            }
            System.out.println();
        }
        drawBoard(game.currentBoard);
        if (outcome == WHITE_CHECKMATES) {
            System.out.println("White wins!");
        } else if (outcome == BLACK_CHECKMATES) {
            System.out.println("Black wins!");
        }
    }

    public static void drawBoard(Piece[][] board) {
        for (int j = 7; j >= 0; j--) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] == null) {
                    if ((i + j) % 2 == 0) {
                        System.out.print("  ");
                    } else {
                        System.out.print("##");
                    }
                } else {
                    if (board[i][j].color == false) {
                        if (board[i][j] instanceof Pawn) {
                            System.out.print("wp");
                        } else if (board[i][j] instanceof Rook) {
                            System.out.print("wR");
                        } else if (board[i][j] instanceof Knight) {
                            System.out.print("wN");
                        } else if (board[i][j] instanceof Bishop) {
                            System.out.print("wB");
                        } else if (board[i][j] instanceof Queen) {
                            System.out.print("wQ");
                        } else if (board[i][j] instanceof King) {
                            System.out.print("wK");
                        }
                    } else {
                        if (board[i][j] instanceof Pawn) {
                            System.out.print("bp");
                        } else if (board[i][j] instanceof Rook) {
                            System.out.print("bR");
                        } else if (board[i][j] instanceof Knight) {
                            System.out.print("bN");
                        } else if (board[i][j] instanceof Bishop) {
                            System.out.print("bB");
                        } else if (board[i][j] instanceof Queen) {
                            System.out.print("bQ");
                        } else if (board[i][j] instanceof King) {
                            System.out.print("bK");
                        }
                    }
                }
                System.out.print(" ");
            }
            System.out.println(j + 1);
        }
        for (char c = 'a'; c <= 'h'; c++) {
            System.out.print(" " + c + " ");
        }
        System.out.println();
    }

}
