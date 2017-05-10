package com.company;

/**
 * Created by apcsaper3 on 5/10/17.
 */
/**
 *   h8 Created by apcsaper3 on 5/10/17.
 */
public class Spike {
    private int x;
    private int y;

    public Spike(int x, int y){
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
}

