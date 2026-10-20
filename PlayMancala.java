import java.util.Scanner;

import mancala.MancalaBoard;
import mancala.MancalaPlayer;
import mancala.players.MinPlayer;
//import mancala.players.GoodPlayer;
import mancala.players.RandomPlayer;
import mancala.players.Ultron;
import mancala.players.smartplayer1;

public class PlayMancala {

    public static void main(String[] args) {
        System.out.println("Play a game?");
        boolean verbose = false;
        MancalaBoard my_game = new MancalaBoard(verbose);
        Scanner myScanner = new Scanner(System.in);

        // MancalaPlayer player1 = new GoodPlayer("Repeat", 1.0, 3.0, 1.5, 1.0, 22.0);
        // // RandomPlayer();
        MancalaPlayer player1 = new smartplayer1();
        MancalaPlayer player2 = new Ultron(); // new RandomPlayer();

        // System.out.println("How many games?");
        // int num_games = myScanner.nextInt();
        int num_games = 100;

        int p1_wins = 0;
        int p2_wins = 0;
        int ties = 0;
        boolean p1_first = true;

        for (int games = 0; games < num_games; games++) {

            while (my_game.game_over() == false) {
                if (verbose) {
                    my_game.view_board(my_game.whose_turn());
                }
                if (my_game.whose_turn() == 1) {
                    my_game.player_turn(player1);
                } else {
                    my_game.player_turn(player2);
                }
                /*
                 * my_game.view_board(my_game.whose_turn());
                 * System.out.println("Which cell Player" + my_game.whose_turn() + "?");
                 * int my_cell = myScanner.nextInt();
                 * my_game.move(my_game.whose_turn(), my_cell);
                 */
                // System.out.println("Next turn?");
                // String next = myScanner.next();
            }
            if (my_game.winner() == 1) {
                p1_wins++;
            } else if (my_game.winner() == 2) {
                System.out.print("Game " + (games + 1) + " (p1_1st: " + p1_first + "):");
                my_game.print_winner();
                // my_game.print_history();
                p2_wins++;
            } else {
                System.out.print("Game " + (games + 1) + " (p1_1st: " + p1_first + "):");
                my_game.print_winner();
                ties++;
            }
            if (p1_first) {
                my_game.Reset(2);
                p1_first = false;
            } else {
                my_game.Reset(1);
                p1_first = true;
            }
        }
        System.out.println("Player1 " + player1.getName() + " won " + p1_wins + " games!");
        System.out.println("Player2 " + player2.getName() + " won " + p2_wins + " games!");
        System.out.println(ties + " games tied!");

        myScanner.close();
    }

}
