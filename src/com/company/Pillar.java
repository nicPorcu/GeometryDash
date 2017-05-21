package com.company;



/**

 * Created by apcsaper3 on 5/10/17.

 */

public class Pillar {

    private int x;
    private int y;
    private int width;
    private int height;
    private int initialX;
    private int initialY;

    public Pillar (int x, int y, int w, int h){
        this.x = x;
        initialX=x;
        this.y = y;
        initialY=y;
        width = w;
        height = h;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight() {return height;}

    public void shiftLeft(int spd){
        x -= spd;
    }

    public void reset()
    {
        x=initialX;
        y= initialY;
    }
}

