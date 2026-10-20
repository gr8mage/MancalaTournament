package mancala.players;

import mancala.MancalaPlayer;
import mancala.MancalaSettings;

public class LatePlayer implements MancalaPlayer {

    MancalaSettings settings;

    public LatePlayer() {
        this(MancalaSettings.DEFAULT_SETTINGS);
    }

    public LatePlayer(MancalaSettings settings) {
        this.settings = settings;
    }

    @Override
    public String getName() {
        return "Late";
    }

    @Override
    public int take_turn(Integer[] your_board, Integer[] opp_board) {
        for (int i = settings.numHouses(); i > 0; i--) {
            if(your_board[i-1] > 0) {
                return i-1;
            }
        }
        return 0;
    }

}
