package com.company;


public class JumpRing { //laucnhes the player into the air
    private int ringX;
    private int ringY;
    public JumpRing(int x, int y){
        ringX = x;
        ringY = y;
    }
    public int getRingX(){return ringX;}
    public int getRingY(){return ringY;}
    public void shiftLeft(int spd){
        ringX -= spd;
    }
}
