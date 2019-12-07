package com.mahmood_anas.arkanoid;

import android.graphics.Paint;

public class Ball {

    private float x;
    private float y;
    private float radius;
    private Paint p;
    private float dx;
    private float dy;

    public Ball(float x, float y, float radius,Paint p) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.p = p;
        this.dx = 0;
        this.dy =0;
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

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
    public Paint getP() {
        return p;
    }

    public void setP(Paint p) {
        this.p = p;
    }
}
