package com.company;



/**

 * Created by apcsaper3 on 5/10/17.

 */

public class Pillar {

    private int x;
    private int y;
    private int width;

    public Pillar (int x, int y, int w){
        this.x = x;
        this.y = y;
        width = w;
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

    public void shiftLeft(int spd){
        x -= spd;
    }
}

