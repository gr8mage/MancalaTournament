
import java.util.ArrayList;

import mancala.MancalaBoard;
import mancala.MancalaPlayer;
import mancala.players.GoodPlayer;
import mancala.players.RandomPlayer;
import mancala.players.Soucy;
import mancala.players.Ultron;
import mancala.players.smartplayer1;

public class Tournament {

    private ArrayList<MancalaPlayer> player_list = new ArrayList<>();

    public int play_match(MancalaPlayer p1, MancalaPlayer p2, int num_games) {
        /// play at match between these two players
        MancalaBoard my_game = new MancalaBoard(false);

        int games = 0;
        int p1_wins = 0;
        int p2_wins = 0;
        int num_ties = 0;
        boolean p1_first = true; 

        for (games = 0; games < num_games; games++) {

            while (my_game.game_over() == false) {
                if (my_game.whose_turn() == 1) {
                    my_game.player_turn(p1);
                } else {
                    my_game.player_turn(p2);
                }
            }
            if (my_game.winner() == 1) {
                p1_wins++;
            } else if (my_game.winner() == 2) {
                p2_wins++;
            } else {
                num_ties++;
            }
            if (p1_first) {
                my_game.Reset(2);
                p1_first = false;
            } else {
                my_game.Reset(1);
                p1_first = true;
            }
        }
        System.out.println("== MATCH RESULTS:" +  p1.getName() + " won " + p1_wins + " games and " + p2.getName() + " won " + p2_wins + " games! ("+num_ties + " ties)");

        if (p1_wins > p2_wins) {
            return 1;
        } else if (p2_wins > p1_wins) {
            return 2; 
        }
        return 0;
    }

    public void play_tournament() {
        int[] victory_points = new int[player_list.size()];

        int cnt = 0;
        for (MancalaPlayer mancalaPlayer : player_list) {
            System.out.println("Player " + (cnt + 1) + ": " + mancalaPlayer.getName());
            victory_points[cnt] = 0;
            cnt++;
        }

        /// play matches...
        ///
        int p1, p2;
        int winner = 0;
        for (p1 = 0; p1 < (player_list.size() - 1); p1++) {
            for (p2 = p1 + 1; p2 < player_list.size(); p2++) {
                MancalaPlayer player1 = player_list.get(p1);
                MancalaPlayer player2 = player_list.get(p2);
                System.out.println("Player1: " + player1.getName() + " versus Player2: " + player2.getName());

                winner = play_match(player1, player2, 50);

                if(winner == 1) {
                    System.out.println("Player1: "+ player1.getName() + " wins (gets 3 victory points)!");
                    victory_points[p1] += 3;
                } else if(winner == 2) {
                    System.out.println("Player2: "+ player2.getName() + " wins (gets 3 victory points)!");
                    victory_points[p2] += 3;
                } else {
                    System.out.println("Player1: "+ player1.getName() + " and Player2: " + player2.getName()+ " tied (each gets 1 victory point)!");
                    victory_points[p1] += 1;
                    victory_points[p2] += 1;
                }
            }
        }

        int max_victory = 0;
        System.out.println("======================================================================");
        for(p1 = 0; p1 < player_list.size(); p1++) {
            if(victory_points[p1] > max_victory) {
                max_victory = victory_points[p1]; 
            }
            System.out.println("Player "+ player_list.get(p1).getName() + " got "+ victory_points[p1] + " victory points");
        }
        System.out.println("======================================================================");
    }

    public void add_player(MancalaPlayer player) {
        player_list.add(player);
    }

    public int num_players() {
        return player_list.size();
    }

    public static void main(String[] args) {
        System.out.println("Play a game?");

        Tournament tourny = new Tournament();

        // tourny.add_player(new GoodPlayer());
        tourny.add_player(new RandomPlayer());
        tourny.add_player(new Ultron());
        tourny.add_player(new smartplayer1());
        tourny.add_player(new Soucy());

        System.out.println("We have " + tourny.num_players() + " players in the Tournament!");

        tourny.play_tournament();

    }
}
