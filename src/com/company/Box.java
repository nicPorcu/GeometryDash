package com.company;

/**
 * Created by apcsaper3 on 5/10/17.
 */

public class Box {
    private static final int hop = 20;
    private int yPos;
    private static final int xPos=30;
    private String name;
    private int velocity;
    private static final int GRAVITY = 1;
    private int score;


    public Box(String n){

        name = n;
        velocity = 0;
    }


    public void jump(){
        velocity+= hop;
    }

    public void move()
    {
        yPos+=velocity;
    }

    public int getX()
    {
        return xPos;
    }
    public int getY()
    {
        return xPos;
    }
    public void fall(){
        velocity-=GRAVITY;
    }

    public void hitsGround(){
        velocity = 0;
    }



}
