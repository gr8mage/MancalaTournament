package mancala.players;
import mancala.MancalaPlayer;

public class MinPlayer implements MancalaPlayer {
    String name;

    public MinPlayer() {
        name = "Minnie";
    }

    public String getName() {
        return name;
    }

    public int take_turn(Integer [] your_board, Integer [] opp_board) {
        for(int cell=0; cell<6; cell++) {
            if(your_board[cell] > 0) {
                return cell;
            }
        }
        return 0;
    }
}
