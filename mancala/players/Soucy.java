package mancala.players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mancala.MancalaSettings;

public class Soucy extends RandomPlayer {

    MancalaSettings settings;

    public Soucy() {
        this(MancalaSettings.DEFAULT_SETTINGS);
    }

    public Soucy(MancalaSettings settings) {
        this.settings = settings;
    }

    @Override
    public String getName() {
        return "Soucy";
    }

    public double scoreBoard(Integer[] mine, Integer[] other) {
        return 1.0;
    }

    @Override
    public int take_turn(Integer[] mine, Integer[] other) {
        List<Integer> options = new ArrayList<>();

        // Determine options
        for (int i = 0; i < settings.numHouses(); i++) {
            if (mine[i] > 0) {
                options.add(i);
            }
        }

        int idealMove = Collections.max(options, Comparator.comparingDouble(x -> {
            Integer[] mineCopy = Arrays.copyOf(mine, mine.length);
            Integer[] otherCopy = Arrays.copyOf(other, other.length);
            evaluateStrategy(mine, other, x);
            return scoreBoard(mineCopy, otherCopy);
        }));

        // Choose an option at random
        return options.get(idealMove);
    }

    // Helpers

    private void printBoard(Integer[] mine, Integer[] other) {
        System.out.println(mine[0] + " " + mine[1] + " " + mine[2] + " " + mine[3] + " " + mine[4] + " " + mine[5]);
        System.out
                .println(other[5] + " " + other[4] + " " + other[3] + " " + other[2] + " " + other[1] + " " + other[0]);
    }

    private void evaluateStrategy(Integer[] mine, Integer[] other, int choice) {
        // We can assume that there are stones, based on take_turn logic
        int numStones = mine[choice];
        mine[choice] = 0;

        int numTotalHouses = settings.numHouses() * 2;

        // Yoinked from the main mancala board, modified to support Settings
        int cell = choice;
        while (numStones > 0) {
            cell++;
            if (cell == (numTotalHouses + 1)) {
                cell = 0;
            }
            if (cell <= settings.numHouses()) {
                mine[cell]++;
            } else if (cell <= numTotalHouses) {
                other[cell - (settings.numHouses() + 1)]++;
            }
            numStones--;
        }
    }

}
