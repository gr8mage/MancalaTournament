package mancala;

public interface MancalaPlayer {
    public String getName();

    public int take_turn(Integer[] your_board, Integer[] opp_board);
}