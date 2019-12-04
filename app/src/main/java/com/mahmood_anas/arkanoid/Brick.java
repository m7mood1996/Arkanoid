package com.mahmood_anas.arkanoid;

import android.graphics.Paint;

public class Brick {

    private float x;
    private float y;
    private float right;
    private float bottom;
    private boolean visible;
    private Paint p;

    public Brick(float x, float y, float right, float bottom,Paint p) {
        this.x = x;
        this.y = y;
        this.right = right;
        this.bottom = bottom;
        this.visible = true;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Paint getP() {
        return p;
    }

    public void setP(Paint p) {
        this.p = p;
    }
}
