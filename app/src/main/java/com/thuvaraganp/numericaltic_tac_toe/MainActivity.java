package com.thuvaraganp.numericaltic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.GridLayout;
public class MainActivity extends AppCompatActivity {

    int[][] board = new int[3][3];
    int[] oddNums = {1, 3, 5, 7, 9};
    int[] evenNums = {2, 4, 6, 8};

    enum PLAYERS {
        PLAYER_1, PLAYER_2
    }

    enum GAME_STATE {
        WIN, TIE, CONTINUE
    }
    PLAYERS turn = PLAYERS.PLAYER_1;

    GAME_STATE checkWin() {
        // check rows
        for(int i = 0; i < 3; i++) {
            if(board[i][0] != -1) {
                if(board[i][0] + board[i][1] + board[i][2] == 15) {
                    return GAME_STATE.WIN;
                }
            }
        }
        // check cols
        for(int i = 0; i < 3; i++) {
            if(board[0][i] != -1) {
                if(board[0][i] + board[1][i] + board[2][i] == 15) {
                    return GAME_STATE.WIN;
                }
            }
        }
        // check diagonals
        if(board[0][0] != -1) {
            if(board[0][0] + board[1][1] + board[2][2] == 15) {
                return GAME_STATE.WIN;
            }
        }
        if(board[0][2] != -1) {
            if(board[0][2] + board[1][1] + board[2][0] == 15) {
                return GAME_STATE.WIN;

            }
        }
        // check for tie
        boolean filled = true;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] == -1) {
                    filled = false;
                    break;
                }
            }
        }
        if(filled) return GAME_STATE.TIE;
        return GAME_STATE.CONTINUE;
    }


    void reset() {
        turn = PLAYERS.PLAYER_1;
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i = 0; i < 9; i++) {
            board[i/3][i%3] = -1;
            View view = gridLayout.getChildAt(i);
            if(view instanceof ImageButton) {
                final ImageButton button = (ImageButton) view;
                button.setImageResource(R.drawable.defaultflask);
                button.setBackgroundColor(Color.parseColor("#808080"));
            }
        }
        oddNums = new int[5];
        evenNums = new int[4];

        int idx = 0;
        for(int i = 1; i <= 9; i+=2) {
            oddNums[idx] = i;
            idx += 1;
        }
        idx = 0;
        for(int i = 2; i <= 8; i+=2) {
            evenNums[idx] = i;
            idx += 1;
        }
    }

    int[] removeFromList(int[] list, int toRemove) {
        int[] ret = new int[list.length - 1];
        int idx = 0;
        for(int i = 0; i < list.length; i++) {
            if(list[i] != toRemove) {
                ret[idx] = list[i];
                idx++;
            }
        }
        return ret;
    }

    CharSequence[] intListToCharList(int[] intList) {
        String[] charList = new String[intList.length];
        for(int i = 0; i < intList.length; ++i) {
            char x = (char)(intList[i] + '0');
            charList[i] = "" + x;
        }
        return (CharSequence[]) charList;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();
        reset();

        // get all buttons from the grid layout
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); ++i) {
            View view = gridLayout.getChildAt(i);
            if(view instanceof ImageButton) {
                final ImageButton button = (ImageButton) view;
                button.setImageResource(R.drawable.defaultflask);
                button.setBackgroundColor(Color.parseColor("#808080"));
            }
        }
        // reset everytime the reset button is clicked
        Button myButton = findViewById(R.id.resetButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View view = gridLayout.getChildAt(i);
            if (view instanceof ImageButton) {
                final ImageButton button = (ImageButton) view;
                final int row = i/3, col = i%3;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(board[row][col] != -1) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Already Selected!");
                            builder.setMessage("A piece is already there!");
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        } else {
                            // show a dialog to choose numbers
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Choose symbol");
                            builder.setItems(intListToCharList(turn == PLAYERS.PLAYER_1 ? oddNums : evenNums), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int choice) {
                                    // update button text based on player's choice
                                    int selected = (turn == PLAYERS.PLAYER_1 ? oddNums[choice] : evenNums[choice]);
                                    button.setImageResource(R.drawable.flask1 + (selected-1));
                                    if(turn == PLAYERS.PLAYER_1) {
                                        button.setBackgroundColor(Color.parseColor("#8bc6fc"));
                                    } else {
                                        button.setBackgroundColor(Color.parseColor("#ffde59"));
                                    }
                                    // update board array
                                    board[row][col] = (turn == PLAYERS.PLAYER_1 ? oddNums[choice] : evenNums[choice]);
                                    // check if there are any winners
                                    GAME_STATE winner = checkWin();
                                    if(winner == GAME_STATE.WIN) {
                                        AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                                        if(turn == PLAYERS.PLAYER_1) {
                                            builder2.setTitle("Player 1 won!");
                                            builder2.setMessage("Player 1 won the game.");
                                            AlertDialog dialog2 = builder2.create();
                                            dialog2.show();
                                        }
                                        else {
                                            builder2.setTitle("Player 2 won!");
                                            builder2.setMessage("Player 2 won the game.");
                                            AlertDialog dialog2 = builder2.create();
                                            dialog2.show();
                                        }
                                    } else if (winner == GAME_STATE.TIE) {
                                        AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                                        builder2.setTitle("Tie game!");
                                        builder2.setMessage("The game has ended in a tie.");
                                        AlertDialog dialog2 = builder2.create();
                                        dialog2.show();
                                    }
                                    if(turn == PLAYERS.PLAYER_1) oddNums = removeFromList(oddNums, oddNums[choice]);
                                    else evenNums = removeFromList(evenNums, evenNums[choice]);
                                    // switch to the next player
                                    turn = (turn == PLAYERS.PLAYER_1 ? PLAYERS.PLAYER_2: PLAYERS.PLAYER_1);
                                    if(winner != GAME_STATE.CONTINUE) reset();
                                }
                            });
                            builder.show();
                        }
                    }
                });
            }
        }
    }
}
