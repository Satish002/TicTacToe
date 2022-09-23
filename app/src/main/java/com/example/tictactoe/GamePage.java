package com.example.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class GamePage extends AppCompatActivity {
    private TicTacToeBoard ticTacToeBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        Button playagain =findViewById(R.id.Game_PlayAgain);
        Button Home = findViewById(R.id.Game_Home);
        TextView playerDisplay=findViewById(R.id.Game_PlayerIndicator);
        String[] pnames = getIntent().getStringArrayExtra("PlayerNames");
        playerDisplay.setText(pnames[0]+"'s turn");
        playagain.setVisibility(View.GONE);
        Home.setVisibility(View.GONE);
        ticTacToeBoard=findViewById(R.id.ticTacToeBoard);
        ticTacToeBoard.setupGame(playagain,Home,playerDisplay,pnames);
    }
    public void playagain(View view) {

            ticTacToeBoard.resetGame();
            ticTacToeBoard.invalidate();

    }
    public void Home(View view){

        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder(GamePage.this);
        builder.setTitle("Are You sure?")
                .setMessage("Do you want to go back?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(GamePage.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No",null)
                .setCancelable(false);
        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }
}