package com.mahmood_anas.arkanoid;

import android.graphics.Paint;

public class BrickCollection {

    private Brick[] bricks;
    private int size;

    public BrickCollection(int COL, int ROW, float starting_hight, int canvasWidth, float side_corners, float top_corners, float brickHight, Paint p) {
        this.size = COL * ROW;
        float brickWidth = (canvasWidth - ((COL+1) *side_corners))/COL;

        this.bricks = new Brick[size];
        for (int i =0;i<this.size;i++){
            int x =(int) (i /COL);
            if ( x == 0 && i % COL == 0)
                this.bricks[i] = new Brick(side_corners,starting_hight,brickWidth,brickHight , p);
            else if(x== 0 )
                this.bricks[i] = new Brick(side_corners + this.bricks[i-1].getRight(),starting_hight,brickWidth,brickHight , p);
            else if (i % COL == 0)
                this.bricks[i] = new Brick(side_corners,top_corners +this.bricks[i-COL].getBottom(),brickWidth,brickHight , p);
            else
                this.bricks[i] = new Brick(side_corners + this.bricks[i-1].getRight(),top_corners +this.bricks[i-COL].getBottom(),brickWidth,brickHight , p);
        }
    }

    public Brick[] getBricks() {
        return bricks;
    }

    public void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean allinVis(){
        int k=0;
        for (int i =0;i<size;i++){
            if (!this.bricks[i].isVisibility())
                k++;
        }
        if(k == size)
            return true;
        return false;
    }
}
