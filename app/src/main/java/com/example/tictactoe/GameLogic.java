package com.example.tictactoe;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLogic {
    private int[][] board;
    private int[] wintype ={-1,-1,-1};
    private Button PlayAgain;
    private Button Home;
    private TextView PlayerDisplay;
    private String[] PNames = {"p1","p2"};



    private int player=1;
    GameLogic(){
        board=new int[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j]=0;
            }
        }
    }
    public boolean updateBoard(int r,int c){
        if (board[r-1][c-1]==0){
            board[r-1][c-1]=player;
            if(player==1)
            PlayerDisplay.setText(PNames[1]+"'s turn");
            else
                PlayerDisplay.setText(PNames[0]+"'s turn");
            return true;
        }
        return false;
    }

    public boolean checkwinner(){

        boolean ret=false;
        int cnt=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]!=0)
                        cnt++;
            }
        }
        for(int i=0;i<3;i++){
            if(board[i][0]!=0 &&(board[i][0]==board[i][1]&&board[i][1]==board[i][2])){
                wintype= new int[]{i,0,1};
                ret=true;
            }
        }
        for(int i=0;i<3;i++){
            if(board[0][i]!=0 &&(board[0][i]==board[1][i]&&board[1][i]==board[2][i])){
                wintype= new int[]{0,i,2};
                ret=true;
            }
        }
        if(board[0][0]!=0&&(board[0][0]==board[1][1]&&board[1][1]==board[2][2])){
            wintype=new int[]{0,2,4};
            ret=true;
        }
        if(board[0][2]!=0&&(board[0][2]==board[1][1]&&board[1][1]==board[2][0])){
            wintype = new int[]{2,2,3};
            ret=true;
        }
        if(ret){
            PlayAgain.setVisibility(View.VISIBLE);
            Home.setVisibility(View.VISIBLE);
            PlayerDisplay.setText((PNames[player-1])+" WON");
        }
        else if(cnt==9){
            PlayAgain.setVisibility(View.VISIBLE);
            Home.setVisibility(View.VISIBLE);
            PlayerDisplay.setText(" Match Tied!");
        }
        return ret;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }
    public void resetGame(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j]=0;
            }
        }
        player=1;
        PlayAgain.setVisibility(View.GONE);
        Home.setVisibility(View.GONE);
        PlayerDisplay.setText(PNames[0]+"'s Turn");


    }

    public void setHome(Button home) {
        Home = home;
    }

    public void setPlayAgain(Button playAgain) {
        PlayAgain = playAgain;
    }

    public void setPNames(String[] PNames) {
        this.PNames = PNames;
    }

    public void setPlayerDisplay(TextView playerDisplay) {
        PlayerDisplay = playerDisplay;
    }

    public int[] getWintype() {
        return wintype;
    }

    public int[][] getBoard() {
        return board;
    }

}
