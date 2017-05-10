package com.company;

/**
 * Created by apcsaper3 on 5/10/17.
 */
public class Pillar {
    private int x;
    private int y;
    private int height;
    private int width;

    public Pillar (int x, int y){
        this.x = x;
        this.y = y;
    }

    public void shiftLeft()
    {
        x--;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getHeight()
    {
        return height;
    }
    public int getWidth()
    {
        return width;
    }
}

