package main;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.function.Function;

public class Game implements Runnable {
    public static class Board { // TODO: don't have a board class, use boardfunctions and just store two bitsets
                                // and some extra information
        public final int connectSize, width, height;
        /**
         * is upside down (im kind of sure; I just changed row and column and reversed
         * stuff until it worked)
         * 
         * The hope is that if I put the index into where it would be in an irl board,
         * it would look like this:
         * 
         * 5 11 ...
         * 4 10 ...
         * 3 9 ...
         * 2 8 ...
         * 1 7 ...
         * 0 6 ...
         */
        private final BitSet pos, mask;

        public Board() {
            this(7, 6);
        }

        public Board(int width, int height) {
            this(width, height, 4, new BitSet(width * height),
                    new BitSet(width * height));
        }

        public Board(int width, int height, int connectSize, BitSet pos, BitSet mask) {
            this.width = width;
            this.height = height;
            this.connectSize = connectSize;
            this.pos = pos;
            this.mask = mask;
        }

        public boolean isDone() {
            return BoardFunctions.isDone(width, height, mask, pos, connectSize);
        }

        public boolean wasWon() {
            return BoardFunctions.wasWon(width, height, mask, pos, connectSize);
        }

        public boolean isFull() {
            return BoardFunctions.isFull(width, height, mask, pos);
        }

        public boolean isLegal(int col) {
            return BoardFunctions.isLegal(width, height, mask, pos, col);
        }

        private void makeMove(int col) {
            BoardFunctions.makeMove(width, height, mask, pos, col);
        }

        public BitSet getPos() {
            return (BitSet) pos.clone();
        }

        public BitSet getMask() {
            return (BitSet) mask.clone();
        }

        public boolean hasPiece(int row, int col) {
            return BoardFunctions.hasPiece(width, height, mask, pos, row, col);
        }

        public boolean hasCurrPlayer(int row, int col) {
            return BoardFunctions.hasCurrPlayer(width, height, mask, pos, row, col);
        }

        public boolean hasCurrOther(int row, int col) {
            return BoardFunctions.hasCurrPlayer(width, height, mask, pos, row, col);
        }

        public ArrayList<Integer> getLegalMoves() {
            return BoardFunctions.getLegalMoves(width, height, mask, pos);
        }
    }

    public final Board board;

    private final Player[] players;
    public final ArrayList<Integer> gamelog; // player index = gamelog.length % 2

    private final boolean shouldDisplay;

    public Game(Function<Board, ? extends Player> p0, Function<Board, ? extends Player> p1) {
        this(p0, p1, 7, 6, true);
    }

    public Game(Function<Board, ? extends Player> p0, Function<Board, ? extends Player> p1, int width, int height,
            boolean shouldDisplay) {
        this(p0, p1, width, height, shouldDisplay, new ArrayList<>());
    }

    public Game(Function<Board, ? extends Player> p0, Function<Board, ? extends Player> p1, int width, int height,
            boolean shouldDisplay,
            ArrayList<Integer> gamelog) {
        this.board = new Board(width, height);
        this.players = new Player[] { p0.apply(this.board), p1.apply(this.board) };
        this.shouldDisplay = shouldDisplay;
        this.gamelog = gamelog;
    }

    public void startGame() {
        printBoard();
        while (!board.isDone()) {
            makeMove(players[gamelog.size() % 2].getMove());
            printBoard();
        }

        printEnd();
    }

    private void makeMove(int col) {
        board.makeMove(col);
        gamelog.add(col);
    }

    protected void printEnd() {
        if (board.wasWon()) {
            System.out.println("Player " + ((gamelog.size() + 1) % 2 + 1) + " has won!"); // Not 0-indexed
        } else if (board.isFull()) {
            System.out.println("There is a tie!");
        } else {
            throw new RuntimeException("Game ended without win or tie.");
        }
    }

    protected void printBoard() {
        if (!shouldDisplay) {
            return;
        }

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
                    if (board.hasCurrPlayer(row, col) == (gamelog.size() % 2 == 0)) {
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

        System.out.println("Gamelog: " + gamelog);
    }

    @Override
    public void run() {
        startGame();
    }
}