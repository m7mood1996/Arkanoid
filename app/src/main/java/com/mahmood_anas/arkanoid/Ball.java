package com.mahmood_anas.arkanoid;

import android.graphics.Paint;

public class Ball {

    private float x;
    private float y;
    private int radius;
    private Paint p;

    public Ball(float x, float y, int radius,Paint p) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.p = p;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    public Paint getP() {
        return p;
    }

    public void setP(Paint p) {
        this.p = p;
    }
}
