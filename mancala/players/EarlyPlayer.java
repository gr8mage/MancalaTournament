package mancala.players;

import mancala.MancalaPlayer;
import mancala.MancalaSettings;

public class EarlyPlayer implements MancalaPlayer {

    MancalaSettings settings;

    public EarlyPlayer() {
        this(MancalaSettings.DEFAULT_SETTINGS);
    }

    public EarlyPlayer(MancalaSettings settings) {
        this.settings = settings;
    }

    @Override
    public String getName() {
        return "Early";
    }

    @Override
    public int take_turn(Integer[] your_board, Integer[] opp_board) {
        for (int i = 0; i < settings.numHouses(); i++) {
            if(your_board[i] > 0) {
                return i;
            }
        }
        return 0;
    }

}
