package com.mahmood_anas.arkanoid;

import android.graphics.Paint;

import androidx.annotation.NonNull;

public class Brick {

    private float x;
    private float y;
    private float right;
    private float bottom;
    private Paint p;
    private  boolean visibility;

    public Brick(float x, float y, float width, float hight,Paint p) {
        this.x = x;
        this.y = y;
        this.right = x+width;
        this.bottom = y + hight;
        this.p = p;
        visibility = true;
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

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    @NonNull
    @Override
    public String toString() {
        return "x is \t" + x + "\t y is \t" + y + "\t right is :\t" + right + "\t bottom is \t" + bottom + "\tis Vis \t" + visibility;

    }
}
