package com.mahmood_anas.arkanoid;

import android.graphics.Paint;

public class Paddle {

    private float width;
    private float height;
    private Paint p;
    private float x;
    private float y;


    public Paddle(float width, float height, Paint p, float x, float y) {
        this.width = width;
        this.height = height;
        this.p = p;
        this.x = x;
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Paint getP() {
        return p;
    }

    public void setP(Paint p) {
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
}
