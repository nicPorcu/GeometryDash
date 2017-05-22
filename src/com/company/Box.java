package com.company;



/**

 * Created by apcsaper3 on 5/10/17.

 */


//random
public class Box {
    private static final double hop = 8.5;
    private static final double ringHop = 15;
    private int yPos;
    private static final int xPos=100;
    private double velocity;
    private static double GRAVITY = 0.33;
    private int angle = 360;
    private int angleThreshold = 180;
    //private int score;
    private int permGround;
    private int groundH;
    //This constructor gives the appropriate height to the player
    public Box(int groundH){
        permGround = groundH;
        this.groundH=groundH;
        yPos= groundH+50;
        velocity = 0;
    }
    //You jump
    public void jump(){
        if(this.onGround()) {
            velocity = hop;
        }
    }
    //This simulates gravity. If the player is above the ground, it will fall at an increasing rate
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
    //This method is called by the ActionPerformed inside the GameRunner
    //It tests if the player is in contact with the nearest ring to the right.
    //If ths player is in contact, it will have a sudden boost to its vertical velocity, causing it to jump
    public void touchRing(JumpRing r){
        if(((600 - yPos <= r.getRingY() && 600 - yPos >= r.getRingY() - 40)) && ((xPos <= r.getRingX() && xPos + 50 >= r.getRingX())||(xPos <= r.getRingX() + 30 && xPos + 50 >= r.getRingX() + 30))){
            velocity = ringHop;
            if (angle <= 180)//upside down
            {
                angleThreshold = 360;
                angle = 180;
            } else {
                angleThreshold = 180;
                angle = 0;
            }
        }
    }
    public void touchPillar(Pillar p){
        if((600 - yPos <= p.getY()) && (xPos + 50 >= p.getX())&& (xPos + 50 >= p.getX()) && (xPos <= p.getX() + 40*p.getWidth())){
            groundH = 600 - p.getY();
        }
        else{
            groundH = permGround;
        }
    }

    //Is used by the ActionPerformed inside the GameRunner
    //Sees if the player has touched the nearest spike to the right
    public boolean isDead(Spike s, Pillar p){
            if(600-yPos >= s.getY()[1] - 50 && xPos < s.getX()[1] && xPos + 50 > s.getX()[1]){
                return true;
            }
            if(xPos + 50 > s.getX()[0] && xPos + 50 < s.getX()[1] && 600-yPos >= (int)(s.getY()[0] - (Math.pow(3, 0.5))*(xPos + 80  - s.getX()[0]))){
                return true;
            }
            if(600 -yPos >= p.getY() && 600 - yPos <= p.getY() + 40*p.getHeight() && xPos + 50 >= p.getX() && xPos + 50 < p.getX() + 40*p.getWidth()){
                return true;
            }

        return false;
    }
    //Sets a new temporary ground height for pillars and things;
    public void setGround(int g){
        groundH = g;
    }
    //if you don't know what these do go learn stuff
    public int getX()
    {
        return xPos;
    }
    public int getY()
    {
        return yPos;
    }
    public int getAngle(){
        return angle;
    }
    public int getAngleThreshold(){ return angleThreshold;}
    public void setAngle(int ang){
        angle = ang;
    }
    public void setAngleThreshold(int angt){
        angleThreshold = angt;
    }

    public boolean onGround()
    {
        if (yPos-50<=groundH)
            return true;
        return false;
    }
}
