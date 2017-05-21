package com.company;



/**

 * Created by apcsaper3 on 5/10/17.

 */

public class Pillar {

    private int x;
    private int y;
    private int width;
    private int height;

    public Pillar (int x, int y, int w, int h){
        this.x = x;
        this.y = y;
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
}

