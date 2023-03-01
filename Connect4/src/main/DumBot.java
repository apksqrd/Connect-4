package main;

import main.Game.Board;

public class DumBot implements Player {
    private final Board board;

    public DumBot(Board board) {
        this.board = board;
    };

    @Override
    public int getMove() {
        for (int col = 0; col < board.width; col++) {
            if (board.isLegal(col)) {
                return col;
            }
        }

        throw new RuntimeException("No legal moves.");
    }
}
