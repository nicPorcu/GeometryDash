package com.company;



/**

 * Created by apcsaper3 on 5/10/17.

 */


//random
public class Box {
    private static final double hop = 8.5;
    private int yPos;
    private static final int xPos=100;
    private String name;
    private double velocity;
    private static final double GRAVITY = 0.33;
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
    public boolean isDead(int[] spikeX, int[] spikeY){
        for(int i = 0; i < spikeX.length; i ++){
            if(600-yPos >= spikeY[i] - 34&& xPos < spikeX[i] && xPos + 50 > spikeX[i]){
                return true;
            }
        }
        return false;
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
