package test;

import main.Game.Board;
import main.NaiveScorer;
import main.Negamax;

public class NegamaxTest {
    static Negamax negamax = new Negamax(8, new NaiveScorer());

    public static void printAnalysis(Board board, boolean isFirstPlayer) {
        // Top
        System.out.print("\u250C\u2500\u2500\u2500");
        for (int col = 0; col < board.width - 1; col++) {
            System.out.print("\u252C\u2500\u2500\u2500");
        }
        System.out.println("\u2510");

        for (int row = board.height - 1; row >= 0; row--) {
            System.out.print("\u2502");
            for (int col = 0; col < board.width; col++) {
                System.out.print(" ");
                if (board.hasPiece(row, col)) {
                    if (board.hasCurrPlayer(row, col) == (isFirstPlayer)) {
                        // is the 0th player's piece
                        System.out.print("X");
                    } else {
                        System.out.print("O");
                    }
                } else {
                    System.out.print(" ");
                }
                System.out.print(" \u2502");
            }
            System.out.println();
        }

        for (int i = 0; i < board.width; i++) {
            System.out.print("\u2502 " + i + " ");
        }

        System.out.println("\u2502");
        System.out.print("\u2514\u2500\u2500\u2500");
        for (int i = 0; i < board.width - 1; i++) {
            System.out.print("\u2534\u2500\u2500\u2500");
        }
        System.out.println("\u2518");

        System.out.println("^ Score: " + negamax.getScore(board));
    }

    public static void main(String[] args) {
        // Board board = new Board();

        // printAnalysis(board, true);
        // board.makeMove(3);
        // printAnalysis(board, false);
        // board.makeMove(0);
        // printAnalysis(board, true);
        // board.makeMove(4);
        // printAnalysis(board, false);
        // board.makeMove(0);
        // printAnalysis(board, true);
        // board.makeMove(5);
        // printAnalysis(board, false);
        // board.makeMove(0);
        // printAnalysis(board, true);
    }
}