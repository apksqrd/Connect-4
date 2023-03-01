package main;

public class Main {
    public static void main(String[] args) {
        // new MainFrame();

        // while (true) {
        // (new Game(HumanPlayer::new, HumanPlayer::new, 9, 5, true)).startGame();
        // }

        while (true) {
            (new Game(HumanPlayer::new,
                    ScoreBasedPlayer.createScoreBasedPlayerCreator(new Negamax(6, new NaiveScorer()))))
                    .startGame();
        }

        // while (true) {
        // (new Game(
        // ScoreBasedPlayer.createScoreBasedPlayerCreator(new Negamax(6, new
        // MonteCarloScorer(1))),
        // ScoreBasedPlayer.createScoreBasedPlayerCreator(new Negamax(6, new
        // NaiveScorer())),
        // 7, 6,
        // true))
        // .startGame();
        // }
    }
}