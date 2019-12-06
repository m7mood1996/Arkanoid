package com.mahmood_anas.arkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private String lives;
    private String score;
    private String clickPlay;
    int lives_left;
    int score_ern;

    private Brick brick;
    private int canvasWidth ;
    private int canvasHeight;
    private final int ROWS =4;
    private final int COLs = 10;
    private Paint textPaint;
    private float side_corners;
    private float top_corners;
    private BrickCollection brickCollection;
    Paint p;
    Paddle paddle;
    Ball ball2;


    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        score_ern =0;
        lives_left =3;
        lives = "Lives: " + lives_left;
        score = "Score: " + score_ern;
        clickPlay = "Click to Play!";

        p =new Paint();
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.FILL);
        paddle = new Paddle(250,30,p,canvasWidth/2,20);
        System.out.println("Anaa  : " + canvasWidth);
        ball2 = new Ball(canvasWidth/2,980,25,p);
        System.out.println("width is : " );
        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);
        int c = Color.rgb(255, 195, 0);
        textPaint.setColor(c);
        textPaint.setTextSize(50);
        brick = new Brick(10,10,100,50,p);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        brickCollection = new BrickCollection(COLs,ROWS,canvasHeight/10,canvasWidth,50,10,50,p);
        canvas.drawRGB(144, 12, 63);
        drowTexts(canvas);
        drowPaddle(canvas);
        drowBall(canvas);
        //canvas.drawRect(brick.getX(),brick.getY(),brick.getRight(),brick.getBottom(),brick.getP());
        drowBricks(canvas);
        drowTextClickPlay(canvas);

        //canvas.drawRect(brick.getX(),brick.getY(),brick.getRight(),brick.getBottom(),brick.getP());





        invalidate();
    }
    public void drowTextClickPlay(Canvas canvas){
        canvas.drawText(clickPlay,canvasWidth/2 - 140,canvasHeight/2,textPaint);//


    }

    public void drowTexts(Canvas canvas){
        canvas.drawText(score,canvasHeight/25,canvasWidth/25,textPaint);
        canvas.drawText(lives,canvasWidth -canvasWidth/7,canvasHeight/18,textPaint);

    }
    public void drowPaddle(Canvas canvas){
        canvas.drawRect(canvasWidth/2 - paddle.getWidth()/2,canvasHeight - canvasHeight/13,canvasWidth/2 + paddle.getWidth()/2,(canvasHeight - canvasHeight/13) + paddle.getHeight(),p);
    }
    public void drowBall(Canvas canvas){
        canvas.drawCircle(canvasWidth/2,canvasHeight - canvasHeight/10,ball2.getRadius(),p);
    }
    public void drowBricks(Canvas canvas){

        for (int i=0;i< ROWS * COLs ; i++) {
            Brick brick = brickCollection.getBricks()[i];
            canvas.drawRect(brick.getX(),brick.getY(),brick.getRight(),brick.getBottom(),brick.getP());
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasWidth = w;
        canvasHeight = h;
    }
}
