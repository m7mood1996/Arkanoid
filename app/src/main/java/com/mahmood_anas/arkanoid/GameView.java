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
    int lives_left;
    int score_ern;
    private Brick brick;
    private int canvasWidth ;
    private int canvasHeight;
    private final int ROWS = 10;
    private final int COLs = 10;
    private Paint textPaint;
    private float side_corners;
    private float top_corners;
    private BrickCollection brickCollection;
    Paint p;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        score_ern =0;
        lives_left =3;
        lives = "Lives: " + lives_left;
        score = "Score: " + score_ern;

        p =new Paint();
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.FILL);
        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);
        int c = Color.rgb(255, 195, 0);
        textPaint.setColor(c);
        textPaint.setTextSize(50);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        brickCollection = new BrickCollection(COLs,ROWS,canvasHeight/10,canvasWidth,50,10,50,p);
        canvas.drawRGB(144, 12, 63);
        canvas.drawText(score,canvasWidth/50,canvasHeight/18,textPaint);
        canvas.drawText(lives,canvasWidth -canvasWidth/7,canvasHeight/18,textPaint);
        for (int i=0;i< ROWS * COLs ; i++) {
            Brick brick = brickCollection.getBricks()[i];
            canvas.drawRect(brick.getX(),brick.getY(),brick.getRight(),brick.getBottom(),brick.getP());
        }
        //canvas.drawRect(brick.getX(),brick.getY(),brick.getRight(),brick.getBottom(),brick.getP());





        //invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        canvasWidth = w;
        canvasHeight = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
