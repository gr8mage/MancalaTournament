package mancala.players;
import mancala.MancalaPlayer;

public class MaxPlayer implements MancalaPlayer {
    String name;
    boolean min_next;

    public MaxPlayer() {
        name = "MinMax";
        min_next  = true;
    }

    public String getName() {
        return name;
    }

    public int take_turn(Integer [] your_board, Integer [] opp_board) {
        if(min_next) {
        for(int cell=0; cell<=6; cell++) {
            if(your_board[cell] > 0) {
                return cell;
            }
        }       
            min_next = false;
        } else {
            min_next = true;
        for(int cell=5; cell>=0; cell--) {
            if(your_board[cell] > 0) {
                return cell;
            }
        }       
        } 
        return 0;
    }
}
