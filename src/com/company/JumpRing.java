package com.company;


public class JumpRing { //laucnhes the player into the air
    private int ringX;
    private int initialX;
    private int ringY;
    private int initialY;
    public JumpRing(int x, int y){
        ringX = x;
        initialX=ringX;
        ringY = y;
        initialY=ringY;
    }
    public int getRingX(){return ringX;}
    public int getRingY(){return ringY;}
    public void shiftLeft(int spd){
        ringX -= spd;
    }
    public void reset()
    {
        ringX=initialX;
        ringY=initialY;
    }
}
