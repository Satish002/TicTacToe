package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PlayerName extends AppCompatActivity {
    private EditText p1;
    private EditText p2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);
        p1=findViewById(R.id.PlayerName_Player1_Name);
        p2=findViewById(R.id.PlayerName_Player2_Name);
    }
    public void startgame(View view){
        String p1name=p1.getText().toString();
        String p2name=p2.getText().toString();
        if(p1name.equals("")){
            p1.setHint("fill this feild");
            p1.setHintTextColor(Color.RED);

        }
        else if(p2name.equals("")){
            p2.setHint("fill this feild");
            p2.setHintTextColor(Color.RED);
        }
        else {
            Intent intent = new Intent(this, GamePage.class);
            intent.putExtra("PlayerNames", new String[]{p1name, p2name});
            startActivity(intent);
            finish();
        }
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(PlayerName.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}