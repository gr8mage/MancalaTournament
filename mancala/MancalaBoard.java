package mancala;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MancalaBoard {

    private Integer[] player1_board = { 4, 4, 4, 4, 4, 4, 0 };
    private Integer[] player2_board = { 4, 4, 4, 4, 4, 4, 0 };

    private int player_turn = 1;
    private int strike = 0;
    private int winner = 0;

    private boolean verbose = true;

    private ArrayList<Integer> history = new ArrayList<>();

    public MancalaBoard() {
    }

    public MancalaBoard(boolean verbose_out) {
        verbose = verbose_out;
    }

    public void Reset() {
        Reset(1);
    }

    public void Reset(int player_start) {
        int cell;
        for(cell = 0; cell<6; cell++) {
          player1_board[cell] = player2_board[cell] = 4; 
        }
        player1_board[6] = player2_board[6] = 0; 
        winner = 0; 
        strike = 0;
        player_turn = player_start;
        history.clear();
    }

    public int winner() {
        return winner;
    }

    public int whose_turn() {
        return player_turn;
    }

    public void player_turn(MancalaPlayer player) {
        //System.out.println(player.getName() + " it is your turn!");
        int cell;
        if(player_turn == 1) {
            cell = player.take_turn(player1_board, player2_board);
            //System.out.println("Player 1 (" + player.getName()+") chose cell "+ cell);
            move(1, cell);
        } else {
            cell = player.take_turn(player2_board, player1_board);
            //System.out.println("Player 2 (" + player.getName()+") chose cell "+ cell);
            move(2, cell);
        }
    }

    private int first_move(Integer [] board) {
        for(int pit = 0; pit < 6; pit++) {
          if(board[pit] > 0) {
            return pit;
          }            
        }
        return -1;
    }

    public void move(int player, int cell) {
        if (player != player_turn) {
            System.out.println("It is not your turn Player " + player);
            return;
        }
        if ((cell < 0) || (cell > 5)) {
            strike++;
            System.out.println("Cell value " + cell + " is not in the valid range (0 - 5)!  Strike "+strike);
            if(strike == 3) {
                System.out.println("You lose your turn (selecting first cell with stones)!");
                if(player == 1) {
                    cell = first_move(player1_board);
                } else {
                    cell = first_move(player2_board);
                }
                if(cell < 0) {
                    System.out.println("No valid moves!  This game is over!");
                    return;
                }
                strike = 0;
            } else {
                System.out.println("Try again!");
                return;
            }
        }
        int num_marbles;
        if (player == 1) {
            num_marbles = player1_board[cell];
            if (num_marbles == 0) {
                strike++;
                System.out.println("Cannot select cell " + cell + " which has no marbles!  Strike "+ strike);
                if(strike == 3) {
                    System.out.println("Player " + player + " you lose your turn (selecting first cell with stones)!");
                    cell = first_move(player1_board);
                    if(cell < 0) {
                        System.out.println("No valid moves!  This game is over!");
                        view_board(player);
                        try {
                            TimeUnit.SECONDS.sleep(10);                            
                        } catch (Exception e) {
                            // TODO: handle exception
                            System.out.println("exception");
                        }
                        strike = 0;
                        return;
                    } else {
                      num_marbles = player1_board[cell];
                    }
                } else { 
                    System.out.println("Try again!");
                    return;
                }
            }
            strike = 0;
            history.add(player*100+cell);
            player1_board[cell] = 0;
            while (num_marbles > 0) {
                cell++;
                if (cell == 13) {
                    cell = 0;
                }
                if (cell <= 6) {
                    player1_board[cell]++;
                } else if (cell <= 12) {
                    player2_board[cell - 7]++;
                }
                num_marbles--;
            }
            if (cell == 6) {
                // last stone was in the home spot
                if(verbose) {
                    System.out.println("Player 1 gets another turn!");
                }
            } else {
                if ((cell < 6) && player1_board[cell] == 1) {
                    player1_board[cell] = 0;
                    player1_board[6] += player2_board[5 - cell] + 1;
                    player2_board[5 - cell] = 0;
                }
                player_turn = 2;
            }
        }
        if (player == 2) {
            num_marbles = player2_board[cell];
            if (num_marbles == 0) {
                strike++;
                System.out.println("Cannot select cell " + cell + " which has no marbles!  Strike "+ strike);
                if(strike == 3) {
                    System.out.println("Player " + player + " you lose your turn (selecting first cell with stones)!");
                    cell = first_move(player2_board);
                    if(cell < 0) {
                        System.out.println("No valid moves!  This game is over!");
                        view_board(player);
                        try {
                            TimeUnit.SECONDS.sleep(10);                            
                        } catch (Exception e) {
                            // TODO: handle exception
                            System.out.println("exception");
                        }
                        strike = 0;
                        return;                        
                    } else {
                      num_marbles = player2_board[cell];
                    }
                } else { 
                    System.out.println("Try again!");
                    return;
                }
            }
            strike = 0;
            history.add(player*100+cell);
            player2_board[cell] = 0;
            while (num_marbles > 0) {
                cell++;
                if (cell == 13) {
                    cell = 0;
                }
                if (cell <= 6) {
                    player2_board[cell]++;
                } else if (cell <= 12) {
                    player1_board[cell - 7]++;
                }
                num_marbles--;
            }
            if (cell == 6) {
                // last stone was in the home spot
                if(verbose) {
                    System.out.println("Player 2 gets another turn!");
                }
            } else {
                if ((cell < 6) && player2_board[cell] == 1) {
                    player2_board[cell] = 0;
                    player2_board[6] += player1_board[5 - cell] + 1;
                    player1_board[5 - cell] = 0;
                }
                player_turn = 1;
            }
        }
        // determine if the next player has a valid move
        int sum1 = 0;
        int sum2 = 0;
        for (int ii = 0; ii < 6; ii++) {
            sum1 += player1_board[ii];
            sum2 += player2_board[ii];
        }
        if ((player_turn == 1) && (sum1 == 0)) {
            // player 1 has no play
            player2_board[6] += sum2;
            for(int ii=0;ii<6; ii++) {
                player2_board[ii] = 0;
            }
        }
        if ((player_turn == 2) && (sum2 == 0)) {
            // player 2 has no play
            player1_board[6] += sum1;
            for(int ii=0;ii<6; ii++) {
                player1_board[ii] = 0;
            }
        }
    }

    public void view_board(int player) {
        if (player == 1) {
            // Display board based on Player 1
            System.out.println("Player 1");
            System.out
                    .println(player2_board[6] + "|" + player2_board[5] + "|" + player2_board[4] + "|" + player2_board[3]
                            + "|" + player2_board[2] + "|" + player2_board[1] + "|" + player2_board[0] + "|");
            System.out.println("  |" + player1_board[0] + "|" + player1_board[1] + "|" + player1_board[2] + "|"
                    + player1_board[3] + "|" + player1_board[4] + "|" + player1_board[5] + "|" + player1_board[6]);
        }
        if (player == 2) {
            // Display board based on Player 2
            System.out.println("Player 2");
            System.out
                    .println(player1_board[6] + "|" + player1_board[5] + "|" + player1_board[4] + "|" + player1_board[3]
                            + "|" + player1_board[2] + "|" + player1_board[1] + "|" + player1_board[0] + "|");
            System.out.println("  |" + player2_board[0] + "|" + player2_board[1] + "|" + player2_board[2] + "|"
                    + player2_board[3] + "|" + player2_board[4] + "|" + player2_board[5] + "|" + player2_board[6]);
        }
    }

    public boolean game_over() {
        if (player1_board[6] > 24) {
            if(verbose) { System.out.println("Player1 Wins! "+ player1_board[6] + " to " + player2_board[6]); }
            winner = 1;
            return true;
        }
        if (player2_board[6] > 24) {
            if(verbose) { System.out.println("Player2 Wins! "+ player2_board[6] + " to " + player1_board[6]); }
            winner = 2;
            return true;
        }
        if(player1_board[6] == 24 && player2_board[6] == 24) {
            if(verbose) { System.out.println("It was a TIE " + player2_board[6] + " to " + player1_board[6]); }
            winner = 0;
            return true;
        }
        return false;
    }

    public void print_winner() {
        if(winner == 1) {
            System.out.println("Player1 Wins! "+ player1_board[6] + " to " + player2_board[6]);
        }
        else if(winner == 2) {
            System.out.println("Player2 Wins! "+ player2_board[6] + " to " + player1_board[6]);
        }
        else 
        {
            System.out.println("It was a TIE " + player1_board[6] + " to " + player2_board[6]);
        }
    }

    public void print_history()
    {
      System.out.println("Mancala Game Recap " + history.size() + " moves!");
      for (Integer integer : history) {
        System.out.print(integer + ",");
      }
      System.out.println("END!");
    }
}
