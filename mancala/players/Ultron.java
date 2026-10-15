package mancala.players;
import mancala.MancalaPlayer;
import java.util.Random;

public class Ultron implements MancalaPlayer {
    String name;

    public Ultron() {
        name = "Ultron";
    }

    public String getName() {
        return name;
    }

    public int take_turn(Integer[] your_board, Integer[] opp_board) {
        int num_opt = 0;
        int pit_options[] = { 0, 0, 0, 0, 0, 0 };

        if (your_board[5] == 1) {
            pit_options[num_opt] = 5;
            num_opt++;
        }

        if (your_board[4] == 2) {
            pit_options[num_opt] = 4;
            num_opt++;
        }

        if (your_board[3] == 3) {
            pit_options[num_opt] = 3;
            num_opt++;
        }

        if (your_board[2] == 4) {
            pit_options[num_opt] = 2;
            num_opt++;
        }

        if (your_board[1] == 5) {
            pit_options[num_opt] = 1;
            num_opt++;
        }

        if (your_board[0] == 6) {
            pit_options[num_opt] = 0;
            num_opt++;
        }
        Random rand = new Random();
        if (num_opt != 0) {
            int rand_opt = rand.nextInt(num_opt);

            return pit_options[rand_opt];
        } else {
            for (int cell = 0; cell < 6; cell++) {
                if (your_board[cell] > 0) {
                    pit_options[num_opt] = cell;
                    num_opt++;
                }

            }
            int rand_opt = rand.nextInt(num_opt);

            return pit_options[rand_opt];
        }
    }
}
