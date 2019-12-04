package com.mahmood_anas.arkanoid;

import android.graphics.Paint;

public class Brick {

    private float x;
    private float y;
    private float right;
    private float bottom;
    private Paint p;

    public Brick(float x, float y, float width, float hight,Paint p) {
        this.x = x;
        this.y = y;
        this.right = x+width;
        this.bottom = y + hight;
        this.p = p;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }


    public Paint getP() {
        return p;
    }

    public void setP(Paint p) {
        this.p = p;
    }
}
