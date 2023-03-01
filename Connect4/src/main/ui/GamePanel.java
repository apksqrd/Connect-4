package main.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import main.Game;
import main.HumanPlayer;
import main.NaiveScorer;
import main.Negamax;
import main.ScoreBasedPlayer;

public class GamePanel extends MainPanel {
    private JPanel boardPanel;
    private Game game;

    public GamePanel() {
        super(new BorderLayout());

        game = new Game(HumanPlayer::new,
                ScoreBasedPlayer.createScoreBasedPlayerCreator(new Negamax(8, new NaiveScorer()))) {
            @Override
            protected void printBoard() {
                updateBoardPanel();
                super.printBoard();
            }
        };

        boardPanel = new JPanel(new GridLayout(game.board.height, game.board.width));

        add(boardPanel);

        (new Thread(game)).start();
    }

    private void updateBoardPanel() {
        boardPanel.removeAll();

        for (int row = game.board.height - 1; row >= 0; row--) {
            for (int col = 0; col < game.board.width; col++) {
                JLabel piece;
                if (game.board.hasPiece(row, col)) {
                    if (game.board.hasCurrPlayer(row, col) == (game.gamelog.size() % 2 == 0)) {
                        // is the 0th player's piece
                        piece = new JLabel("X");
                    } else {
                        piece = new JLabel("O");
                    }
                } else {
                    piece = new JLabel(" ");
                }
                piece.setBorder(new LineBorder(Color.BLACK));
                boardPanel.add(piece);
            }
        }

        revalidate();
        repaint();
    }
}