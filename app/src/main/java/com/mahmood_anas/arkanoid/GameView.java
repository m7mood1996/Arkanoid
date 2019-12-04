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
    private final int ROWS =4;
    private final int COLs = 10;
    private Paint textPaint;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        score_ern =0;
        lives_left =3;
        lives = "Lives: " + lives_left;
        score = "Score: " + score_ern;
        Paint p =new Paint();
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.FILL);
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
        canvas.drawRGB(144, 12, 63);
        canvas.drawText(score,canvasHeight/25,canvasWidth/25,textPaint);
        canvas.drawRect(brick.getX(),brick.getY(),brick.getRight(),brick.getBottom(),brick.getP());





        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasWidth = w;
        canvasHeight = h;
    }
}
