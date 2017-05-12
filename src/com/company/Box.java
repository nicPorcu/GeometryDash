package com.company;



/**

 * Created by apcsaper3 on 5/10/17.

 */



public class Box {
    private static final int hop = 15;
    private int yPos;
    private static final int xPos=30;
    private String name;
    private int velocity;
    private static final int GRAVITY = 1;
    //private int score;
    private int groundH;

    public Box(String n, int groundH){
        this.groundH=groundH;
        yPos= groundH+50;
        velocity = 0;
    }
    public void jump(){
        if(this.onGround()) {
            velocity = hop;
        }
    }
    public void move()
    {
        yPos+=velocity;
        if(!this.onGround()) {
            velocity -= GRAVITY;
        }
        else
        {
            velocity=0;
            yPos=groundH+50;
        }
    }
    public int getX()
    {
        return xPos;
    }
    public int getY()
    {
        return yPos;
    }

    public boolean onGround()
    {
        if (yPos-50<=groundH)
            return true;
        return false;
    }

    public void hitsGround(){
        velocity = 0;
    }


}
