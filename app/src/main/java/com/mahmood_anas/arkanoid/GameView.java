package com.mahmood_anas.arkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

import static android.os.SystemClock.sleep;

public class GameView extends View {
    private String lives;
    private String score;
    private String clickPlay;
    private String GameOverPlay;
    private String GameOverwon;
    int lives_left;
    int score_ern;
    boolean newGame;
    private Brick brick;
    private int canvasWidth;
    private int canvasHeight;
    private final int ROWS = 5;
    private final int COLs = 7;
    private Paint textPaint;
    private float side_corners;
    private float top_corners;
    BrickCollection brickCollection;
    Paint p;
    Paddle paddle;
    public Ball ball;
    int intraction;
    float paddleleft;
    float paddleright;
    Random random;
    float Balldx;
    float Balldy;
    int paddle_dx;
    boolean gameOver;
    boolean lostLive;
    MediaPlayer mediaPlayer;
    boolean soundon;
    boolean gameOverwon;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        score_ern = 0;
        lives_left = 3;
        lives = "Lives: " + lives_left;
        score = "Score: " + score_ern;
        clickPlay = "Click to Play!";
        GameOverPlay = "Game Over - You Loss!";
        GameOverwon = "Game Over - You Loss!";
        newGame = true;
        p = new Paint();
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.FILL);
        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);
        int c = Color.rgb(255, 195, 0);
        textPaint.setColor(c);
        textPaint.setTextSize(50);
        intraction = 0;
        random = new Random();
        Balldx = 0;
        Balldy = 0;
        gameOver = false;
        gameOverwon = false;
        lostLive = false;
        soundon= false;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRGB(144, 12, 63);
        if (intraction == 0) {
            brickCollection = new BrickCollection(COLs, ROWS, canvasHeight / 10, canvasWidth, 10, 10, 50, p);
            ball = new Ball(canvasWidth / 2, canvasHeight - canvasHeight / 10, 15, p);
            paddle = new Paddle(250, 30, p, canvasWidth / 2, canvasHeight - canvasHeight / 13);
           /* paddleleft = (canvasWidth / 2) - (paddle.getWidth() / 2);
            paddleright = (canvasWidth / 2 + paddle.getWidth() / 2);
            paddle.setX(paddleleft);
            paddle.setY(canvasHeight - canvasHeight / 13);
            paddle.setWidth(paddleright);
            paddle.setHeight((canvasHeight - canvasHeight / 13) + paddle.getHeight());*/


        }
        drowTexts(canvas);
        drowPaddle(canvas);




        //canvas.drawRect(brick.getX(),brick.getY(),brick.getRight(),brick.getBottom(),brick.getP());
        drowBricks(canvas);
        if (newGame == true) {
            drowBall(canvas,0,0);
            drowTextClickPlay(canvas);
            Balldx = getRandomSpeedX();
            Balldy =  (random.nextInt() % 5);
            if(Balldy >0)
                Balldy = Balldy * -1;
            if (Balldy == 0)
                Balldy = -1;

            //Log.d("dx,dy", String.valueOf(Balldx));
            //Log.d("dy", String.valueOf(Balldy));

        }

        else if(lostLive == true){
            drowBall(canvas,0,0);
            drowTextClickPlay(canvas);
            Balldx = getRandomSpeedX();
            Balldy =  (random.nextInt() % 5);
            if(Balldy >0)
                Balldy = Balldy * -1;
            if (Balldy == 0)
                Balldy = -1;

            ball.setX(canvasWidth / 2);
            ball.setY(canvasHeight - canvasHeight / 10);
            float wi = paddle.getRight() -paddle.getLeft();
            paddle.setLeft(canvasWidth/2 -wi/2 );
            paddle.setRight(canvasWidth/2 + wi/2);

        }
        else if(gameOver == true || gameOverwon ==true){
            drowGameOverText(canvas);
            lives_left =3;
            score_ern = 0;
            Balldx = getRandomSpeedX();
            Balldy =  (random.nextInt() % 5);
            if(Balldy >0)
                Balldy = Balldy * -1;
            if (Balldy == 0)
                Balldy = -1;
            ball.setX(canvasWidth / 2);
            ball.setY(canvasHeight - canvasHeight / 10);
            drowBall(canvas, 0, 0);
            for (int i =0; i < COLs * ROWS;i++){
                brickCollection.getBricks()[i].setVisibility(true);
            }
            float wi = paddle.getRight() -paddle.getLeft();
            paddle.setLeft(canvasWidth/2 -wi/2 );
            paddle.setRight(canvasWidth/2 + wi/2);
        }
        else {
            drowBall(canvas, Balldx, Balldy);
            //System.out.println("speed" + Balldy);
        }
        //canvas.drawRect(brick.getX(),brick.getY(),brick.getRight(),brick.getBottom(),brick.getP());

        intraction=1;
        invalidate();
    }

    public void drowTextClickPlay(Canvas canvas) {
        canvas.drawText(clickPlay, canvasWidth / 2 - 140, canvasHeight / 2, textPaint);//
    }

    public void drowGameOverText(Canvas canvas) {
        if (gameOver == true)
        canvas.drawText(GameOverPlay, canvasWidth / 2 - 140, canvasHeight / 2, textPaint);//
        else if (gameOverwon ==true){
            canvas.drawText(GameOverwon, canvasWidth / 2 - 140, canvasHeight / 2, textPaint);
        }
    }

    public void drowTexts(Canvas canvas) {
        score = "Score: " + score_ern;
        lives = "Lives: " + lives_left;
        canvas.drawText(score, canvasHeight / 25, canvasWidth / 25, textPaint);
        canvas.drawText(lives, canvasWidth - canvasWidth / 7, canvasHeight / 18, textPaint);

    }

    public void drowPaddle(Canvas canvas) {

            while(paddle_dx>0 && paddle.getRight() < canvasWidth - 5 ) {
                paddle.setLeft(paddle.getLeft() +1);
                paddle.setRight(paddle.getRight() +1);
                canvas.drawRect(paddle.getLeft(), paddle.getY(), paddle.getRight(), paddle.getBotttom(), paddle.getP());
                paddle_dx--;
            }
            while(paddle_dx<0 && paddle.getLeft() > 5) {
                paddle.setLeft(paddle.getLeft() - 1);
                paddle.setRight(paddle.getRight() - 1);
                canvas.drawRect(paddle.getLeft(), paddle.getY(), paddle.getRight(), paddle.getBotttom(), paddle.getP());
                paddle_dx++;
            }
        canvas.drawRect(paddle.getLeft(), paddle.getY(), paddle.getRight(), paddle.getBotttom(), paddle.getP());

    }

    public void drowBall(Canvas canvas, float dx , float dy) {

        ball.setX(ball.getX()+dx);
        ball.setY(ball.getY()+dy);


        canvas.drawCircle(ball.getX(),ball.getY(), ball.getRadius(), ball.getP());
    }

    public void drowBricks(Canvas canvas) {

        for (int i = 0; i < ROWS * COLs; i++) {
            Brick brick = brickCollection.getBricks()[i];
            if(brick.isVisibility())
                canvas.drawRect(brick.getX(), brick.getY(), brick.getRight(), brick.getBottom(), brick.getP());
        }

    }


    public void wallTouch(){
        if(ball.getX() +ball.getRadius()  >= canvasWidth) {
            ball.setX(ball.getX() - Balldx);
            Balldx = Balldx * -1;
        }
        if(ball.getY() - ball.getRadius()  <= canvasHeight/24) {
            ball.setY(ball.getY() - Balldy);
            Balldy = Balldy * -1;
        }
        if(ball.getX() - ball.getRadius() <= 0){
           ball.setX(ball.getX() - Balldx);
            Balldx = Balldx * -1;
        }
        if(ball.getY() + ball.getRadius()  >= canvasHeight - canvasHeight/26) {
            ball.setY(ball.getY() - Balldy);
            Balldy = Balldy * -1;
            GameOver();
        }
        if(ball.getY() + ball.getRadius() - Balldy-1 <= paddle.getY() && ball.getY() + ball.getRadius() + Balldy+1 >= paddle.getY() && ball.getX() >= paddle.getLeft() && ball.getX() <= paddle.getRight() ){
            ball.setY(ball.getY() - Balldy);
            Balldy = Balldy * -1;
        }
        if( Math.pow(ball.getX()-paddle.getLeft(),2) + Math.pow(ball.getY()-paddle.getY(),2) <= Math.pow(ball.getRadius(),2) || Math.pow(ball.getX()-paddle.getRight(),2) + Math.pow(ball.getY()-paddle.getY(),2) <= Math.pow(ball.getRadius(),2) ){

            ball.setY(ball.getY() - Balldy);
            ball.setX(ball.getX() - Balldx);
            Balldy = Balldy * -1;
            Balldx = Balldx * -1;
        }

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasWidth = w;
        canvasHeight = h;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                newGame = false;
                lostLive = false;
                gameOver = false;
                break;
        }

        return super.onTouchEvent(event);
    }

    public float getRandomSpeedX() {

        float r = random.nextInt() % 10;
        float t = random.nextInt() %100;
        if (t > 50)
            return r;
        return -1 * r;
    }

    public void bricksTouchA() {
        for (int i = 0; i < brickCollection.getSize(); i++) {
            Brick brick = brickCollection.getBricks()[i];
            if(brick.isVisibility() && brick.getBottom() >= ball.getY() - ball.getRadius() + Balldy && brick.getBottom() <= ball.getY() - ball.getRadius()-Balldy && ball.getX() <= brick.getRight() && ball.getX() >=brick.getX()){
                Balldy = Balldy * -1;
                score_ern = score_ern + lives_left*5;
                brick.setVisibility(false);
                soundon = true;


            }
            if(brick.isVisibility() && Math.pow(ball.getX()-brick.getX(),2) + Math.pow(ball.getY()-brick.getY(),2) <= Math.pow(ball.getRadius(),2)){
                Balldy = Balldy * -1;
                Balldx = Balldx * -1;
                score_ern = score_ern + lives_left*5;
                brick.setVisibility(false);
                soundon = true;


            }
            if (brickCollection.allinVis() == true)
                gameOverwon =true;

        }

    }

    public void bricksTouchB() {
        for (int i = 0; i < brickCollection.getSize(); i++) {
            Brick brick = brickCollection.getBricks()[i];

            if(brick.isVisibility() && brick.getX() <= ball.getX() + ball.getRadius()+Balldx && brick.getX() >= ball.getX() + ball.getRadius()-Balldx && ball.getY() <=brick.getBottom() && ball.getY() >= brick.getY() ){
                score_ern = score_ern + lives_left*5;
                brick.setVisibility(false);
                Balldx = Balldx * -1;
                // add point to score
                soundon = true;

            }
            if(brick.isVisibility() && Math.pow(ball.getX()-brick.getRight(),2) + Math.pow(ball.getY()-brick.getY(),2) <= Math.pow(ball.getRadius(),2)){
                Balldy = Balldy * -1;
                Balldx = Balldx * -1;
                score_ern = score_ern + lives_left*5;
                brick.setVisibility(false);
                soundon = true;


            }
            if (brickCollection.allinVis() == true)
                gameOverwon =true;


        }

    }

    public void bricksTouchC() {
        for (int i = 0; i < brickCollection.getSize(); i++) {
            Brick brick = brickCollection.getBricks()[i];
            if (brick.isVisibility() && brick.getY() + Balldy >= ball.getY() + ball.getRadius() && brick.getY() - Balldy <= ball.getY() + ball.getRadius() && ball.getX()+Balldx >= brick.getX() && ball.getX()- Balldx <= brick.getRight()) {
                score_ern = score_ern + lives_left*5;
                brick.setVisibility(false);
                Balldy = Balldy * -1;
                soundon = true;

            }
            if(brick.isVisibility() && Math.pow(ball.getX()-brick.getX(),2) + Math.pow(ball.getY()-brick.getBottom(),2) <= Math.pow(ball.getRadius(),2)){
                Balldy = Balldy * -1;
                Balldx = Balldx * -1;
                score_ern = score_ern + lives_left*5;
                brick.setVisibility(false);
                soundon = true;


            }
            if (brickCollection.allinVis() == true)
                gameOverwon =true;

        }
    }

    public void bricksTouchD(){
        for (int i = 0; i < brickCollection.getSize(); i++) {
            Brick brick = brickCollection.getBricks()[i];
            if (brick.isVisibility() && brick.getRight()+Balldx <= ball.getX() - ball.getRadius() && brick.getRight() -Balldx >= ball.getX()- ball.getRadius() && ball.getY() > brick.getY() && ball.getY()<brick.getBottom() ) {
                score_ern = score_ern + lives_left*5;
                brick.setVisibility(false);
                Balldx = Balldx * -1;
                soundon = true;


            }
            if(brick.isVisibility() && Math.pow(ball.getX()-brick.getRight(),2) + Math.pow(ball.getY()-brick.getBottom(),2) <= Math.pow(ball.getRadius(),2)){
                Balldy = Balldy * -1;
                Balldx = Balldx * -1;
                score_ern = score_ern + lives_left*5;
                brick.setVisibility(false);
                soundon = true;


            }
            if (brickCollection.allinVis() == true)
                gameOverwon =true;

        }

    }

    public void paddleMoveR(int z) {

            if(paddle.getRight() > canvasWidth-5)
                return;

            //while(gameView == null);
            //while(gameView.paddle == null);
            //System.out.println("Game is \t" + gameView.paddle.getHeight());


            paddle.setLeft(paddle.getLeft() + 1);
            paddle.setRight(paddle.getRight() + 1);
            sleep(1);
            //System.out.println("K is now :\t" + k);

    }
    public void paddleMoveL(int z) {
        if(paddle.getLeft() < 5)
            return;

            //gameView.paddle.setWidth(gameView.paddle.getWidth() - 20);
            //System.out.println("K will be :\t" + k);
        paddle.setLeft(paddle.getLeft() -1);
        paddle.setRight(paddle.getRight() -1);
        sleep(1);







    }

    public void GameOver(){

        Balldx =0;
        Balldy =0;
        if(lives_left == 1){
            gameOver = true;
            lostLive = false;
            return;
        }
        lives_left--;
        lostLive = true;
    }
}
