package mancala.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mancala.MancalaPlayer;

public class RandomPlayer implements MancalaPlayer {

    private static Integer STORE_INDEX = 6;

    @Override
    public String getName() {
        return "Random";
    }

    @Override
    public int take_turn(Integer[] your_board, Integer[] opp_board) {
        List<Integer> options = new ArrayList<>();
        Random rand = new Random();

        // Determine options
        for (int i = 0; i < STORE_INDEX; i++) {
            if (your_board[i] > 0) {
                options.add(i);
            }
        }

        // Choose an option at random
        int randChoice = rand.nextInt(options.size());
        return options.get(randChoice);
    }

}
