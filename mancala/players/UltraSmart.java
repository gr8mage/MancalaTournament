package mancala.players;

import mancala.MancalaPlayer;
import java.util.Random;

public class UltraSmart implements MancalaPlayer {
    String name;

    public UltraSmart() {
        name = "UltraSmart";
    }

    public String getName() {
        return name;
    }

    public int take_turn(Integer[] your_board, Integer[] opp_board) {
        int num_opt = 0;
        int pit_options[] = new int [50];

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

            // check if i can steal their space
            if (opp_board[0] >= 1 && your_board[5] == 0) {

                if (your_board[4] == 1) {
                    pit_options[num_opt] = 4;
                    num_opt++;
                }
                if (your_board[3] == 2) {
                    pit_options[num_opt] = 3;
                    num_opt++;
                }
                if (your_board[2] == 3) {
                    pit_options[num_opt] = 2;
                    num_opt++;
                }
                if (your_board[1] == 4) {
                    pit_options[num_opt] = 1;
                    num_opt++;
                }
                if (your_board[0] == 5) {
                    pit_options[num_opt] = 0;
                    num_opt++;
                }
            }
            if (opp_board[1] >= 1 && your_board[4] == 0) {

                if (your_board[5] == 12) {
                    pit_options[num_opt] = 5;
                    num_opt++;
                }
                if (your_board[3] == 1) {
                    pit_options[num_opt] = 3;
                    num_opt++;
                }
                if (your_board[2] == 2) {
                    pit_options[num_opt] = 2;
                    num_opt++;
                }
                if (your_board[1] == 3) {
                    pit_options[num_opt] = 1;
                    num_opt++;
                }
                if (your_board[0] == 4) {
                    pit_options[num_opt] = 0;
                    num_opt++;
                }
            }
            if (opp_board[2] >= 1 && your_board[3] == 0) {

                if (your_board[5] == 11) {
                    pit_options[num_opt] = 5;
                    num_opt++;
                }
                if (your_board[4] == 12) {
                    pit_options[num_opt] = 4;
                    num_opt++;
                }
                if (your_board[2] == 1) {
                    pit_options[num_opt] = 2;
                    num_opt++;
                }
                if (your_board[1] == 2) {
                    pit_options[num_opt] = 1;
                    num_opt++;
                }
                if (your_board[0] == 3) {
                    pit_options[num_opt] = 0;
                    num_opt++;
                }
            }
            if (opp_board[3] >= 1 && your_board[2] == 0) {

                if (your_board[5] == 10) {
                    pit_options[num_opt] = 5;
                    num_opt++;
                }
                if (your_board[4] == 11) {
                    pit_options[num_opt] = 4;
                    num_opt++;
                }
                if (your_board[3] == 12) {
                    pit_options[num_opt] = 3;
                    num_opt++;
                }
                if (your_board[1] == 1) {
                    pit_options[num_opt] = 1;
                    num_opt++;
                }
                if (your_board[0] == 2) {
                    pit_options[num_opt] = 0;
                    num_opt++;
                }
            }
            if (opp_board[4] >= 1 && your_board[1] == 0) {

                if (your_board[5] == 9) {
                    pit_options[num_opt] = 5;
                    num_opt++;
                }
                if (your_board[4] == 10) {
                    pit_options[num_opt] = 4;
                    num_opt++;
                }
                if (your_board[3] == 11) {
                    pit_options[num_opt] = 3;
                    num_opt++;
                }
                if (your_board[2] == 12) {
                    pit_options[num_opt] = 2;
                    num_opt++;
                }
                if (your_board[0] == 1) {
                    pit_options[num_opt] = 0;
                    num_opt++;
                }
            }
            if (opp_board[5] >= 1 && your_board[0] == 0) {

                if (your_board[5] == 8) {
                    pit_options[num_opt] = 5;
                    num_opt++;
                }
                if (your_board[4] == 9) {
                    pit_options[num_opt] = 4;
                    num_opt++;
                }
                if (your_board[3] == 10) {
                    pit_options[num_opt] = 3;
                    num_opt++;
                }
                if (your_board[2] == 11) {
                    pit_options[num_opt] = 2;
                    num_opt++;
                }
                if (your_board[1] == 12) {
                    pit_options[num_opt] = 1;
                    num_opt++;
                }
            }

            if (num_opt > 0) {
                return pit_options[rand.nextInt(num_opt)];
            } else {

                for (int cell = 0; cell < 6; cell++) {
                    if (your_board[cell] > 0) {
                        pit_options[num_opt] = cell;
                        num_opt++;
                    }
                }

                int ran_opt = rand.nextInt(num_opt);

                return pit_options[ran_opt];
            }
        }
        
    
    }

    }

