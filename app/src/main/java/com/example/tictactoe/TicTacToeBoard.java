package com.example.tictactoe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TicTacToeBoard extends View {
    private int cellSize=getWidth()/3;
    private final int boardColor;
    private final int XColor;
    private final int OColor;
    private final int WinColor;
    private final Paint paint=new Paint();
    private final GameLogic game;
    private boolean winningind =false;

    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        game=new GameLogic();
        TypedArray a =context.getTheme().obtainStyledAttributes(attrs,R.styleable.TicTacToeBoard,0,0);
        try{
            boardColor=a.getInteger(R.styleable.TicTacToeBoard_boardColor,0);
            XColor=a.getInteger(R.styleable.TicTacToeBoard_XColor,0);
            OColor=a.getInteger(R.styleable.TicTacToeBoard_OColor,0);
            WinColor=a.getInteger(R.styleable.TicTacToeBoard_WinColor,0);
        }
        finally {
            a.recycle();
        }
    }
    @Override
    protected void onMeasure(int width , int Height){
        super.onMeasure(width,Height);
        int dimension=Math.min(getMeasuredWidth(),getMeasuredHeight());
        cellSize = dimension/3;
        setMeasuredDimension(dimension,dimension);

    }
    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        drawBoard(canvas);
        drawMarkers(canvas);
        if(winningind){
            paint.setColor(WinColor);
            drawwinline(canvas);
        }

    }

    private void drawMarkers(Canvas canvas) {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
               if(game.getBoard()[i][j]!=0){
                   if(game.getBoard()[i][j]==1){
                       drawX(canvas,i,j);
                   }
                   else {
                       drawO(canvas,i,j);
                   }
               }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x= event.getX();
        float y= event.getY();
        int action= event.getAction();
        if(action==MotionEvent.ACTION_DOWN){
            int row = (int) Math.ceil(y/cellSize);
            int col = (int) Math.ceil(x/cellSize);
            if(!winningind){
                if(game.updateBoard(row,col)){
                    if(game.checkwinner())
                        winningind=true;
                    invalidate();

                    if(game.getPlayer()==1){
                        game.setPlayer(2);
                }
                else{
                    game.setPlayer(1);
                }
            }
            }
            invalidate();
            return true;
        }
        return false;
    }

    private void drawBoard(Canvas canvas) {
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);
        for(int c=1;c<3;c++){
            canvas.drawLine(cellSize*c,0,cellSize*c,canvas.getWidth(),paint);
        }
        for(int r=1;r<3;r++){
            canvas.drawLine(0,cellSize*r,canvas.getWidth(),cellSize*r,paint);
        }
    }
    private void drawX(Canvas canvas,int row,int col){
        paint.setColor(XColor);
        canvas.drawLine((float) ((col+1)*cellSize-cellSize*0.2),
                        (float) (row*cellSize+cellSize*0.2),
                        (float) (col*cellSize+cellSize*0.2),
                        (float) ((row+1)*cellSize-cellSize*0.2),
                        paint);
        canvas.drawLine((float)( (col)*cellSize+cellSize*0.2),
                        (float)(row*cellSize+cellSize*0.2),
                        (float)((col+1)*cellSize-cellSize*0.2),
                        (float)((row+1)*cellSize-cellSize*0.2),
                        paint);
    }
    private void drawO(Canvas canvas,int row,int col){
        paint.setColor(OColor);
        canvas.drawOval((float) (col*cellSize+cellSize*0.2),
                        (float)(row*cellSize+cellSize*0.2),
                        (float)( (col*cellSize+cellSize)-cellSize*0.2),
                        (float) ((row*cellSize+cellSize)-cellSize*0.2),
                            paint);
    }
    private void drawH(Canvas canvas,int row,int col){
        canvas.drawLine((float)col,
                (float)(row*cellSize+cellSize/2),
                (float)(cellSize*3),
                (float)(row*cellSize+cellSize/2),paint);
    }
    private void drawV(Canvas canvas,int row,int col){
        canvas.drawLine((float)(col*cellSize+cellSize/2),
                (float)(row),
                (float)(cellSize*col+cellSize/2),
                (float)(3*cellSize),paint);
    }
    private void drawD1(Canvas canvas){
        canvas.drawLine(0,cellSize*3,cellSize*3,0,paint);
    }
    private void drawD2(Canvas canvas ){
        canvas.drawLine(0,0,cellSize*3,cellSize*3,paint);
    }
    private void drawwinline(Canvas canvas){
        int row=game.getWintype()[0];
        int col=game.getWintype()[1];
        switch (game.getWintype()[2]){
            case 1:
                drawH(canvas,row,col);
            break; case 2:
                drawV(canvas,row,col);
            break; case 3:
                drawD1(canvas);
            break; case 4:
                drawD2(canvas);
                break;
        }
    }

    public void resetGame(){
        game.resetGame();
        winningind=false;

    }
    public void setupGame(Button playAgain, Button Home , TextView PlayerDisplay,String[] pnames){
        game.setPlayAgain(playAgain);
        game.setHome(Home);
        game.setPlayerDisplay(PlayerDisplay);
        game.setPNames(pnames);
    }
}
