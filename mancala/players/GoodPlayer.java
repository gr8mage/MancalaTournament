package mancala.players;

import mancala.MancalaPlayer;

public class GoodPlayer implements MancalaPlayer {

  private String name;
  private final Integer SCORE_PIT_INDEX = 6;

  private Double pit_factor = 1.0;
  private Double score_factor = 3.2;
  private Double guard_score_factor = 1.0;
  private Double guard_pit_factor = 1.0;
  private Double extra_turn_value = 20.0;

  public GoodPlayer() {
    name = "AJ_Good";
  }

  public GoodPlayer(String my_name) {
    name = my_name;
  }

  public GoodPlayer(String my_name, Double pf, Double sf, Double gsf, Double gpf, Double etv) {
    name = my_name;
    pit_factor = pf;
    score_factor = sf;
    guard_pit_factor = gpf;
    guard_score_factor = gsf;
    extra_turn_value = etv;
  }

  public int take_turn(Integer[] your_board, Integer[] opp_board) {
    int pit;
    Double best_score = -1.0;
    int best_opt = 5;

    // Does any option steal stones (end in an empty space on our side)
    int end_cell;
    Double this_score = -2.0;

    if (extra_turn_value > 0.5) {
      if (your_board[5] == 1) {
        return 5;
      }
      if (your_board[4] == 2) {
        return 4;
      }
    }

    for (pit = 0; pit < SCORE_PIT_INDEX; pit++) {
      if (your_board[pit] > 0) {
        this_score = pit * pit_factor; // want to empty from goal back

        // where does the last cell land??
        end_cell = pit + your_board[pit];
        if (end_cell > 12) {
          end_cell -= 13;
        } // wrap the board

        /// last stone will end in GOAL - EXTRA MOVE!
        if (end_cell == SCORE_PIT_INDEX) {
          this_score += extra_turn_value;
        }
        if ((end_cell < SCORE_PIT_INDEX) && (your_board[end_cell] == 0)) {
          this_score = (opp_board[5 - end_cell] + 1) * score_factor;
        }
        // check if this cell is across from an empty cell?
        if (opp_board[5 - pit] == 0) {
          // check if the opponent can reach this cell
          this_score = (your_board[pit]) * guard_score_factor + (6 - pit) * guard_pit_factor; // protect these stones
        }
        // check if this is the best option so far
        if (this_score > best_score) {
          best_opt = pit;
          best_score = this_score;
        }
      }
    }
    // System.out.println(name + " best option is "+best_opt+" with score "+
    // best_score);

    return best_opt;
  }

  public String getName() {
    return name;
  }
}