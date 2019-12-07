package com.mahmood_anas.arkanoid;

import android.graphics.Paint;

public class Paddle {

    private float right;
    private float botttom;
    private Paint p;
    private float left;
    private float y;


    public Paddle(float width, float height, Paint p, float x, float y) {
        this.right = x + width/2;
        this.botttom = y + height;
        this.p = p;
        this.left = x - width/2 ;
        this.y = y;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getBotttom() {
        return botttom;
    }

    public void setBotttom(float botttom) {
        this.botttom = botttom;
    }

    public Paint getP() {
        return p;
    }

    public void setP(Paint p) {
        this.p = p;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;

    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
