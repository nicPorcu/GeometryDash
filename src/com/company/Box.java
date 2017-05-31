package com.company;



/**

 * Created by apcsaper3 on 5/10/17.

 */


//random
public class Box {
    private static final double hop = 8.5;
    private static final double ringHop = 11;
    private int yPos;
    private int orgYPos;
    private static final int xPos=100;
    private double velocity;
    private static double GRAVITY = 0.33;
    private int angle = 360;
    private int angleThreshold = 0;
    //private int score;
    private int permGround;
    private int groundH;
    private boolean passPortal = false;
    private boolean isSpaceship;
    //This constructor gives the appropriate height to the player
    public Box(int groundH){
        permGround = groundH;
        this.groundH=groundH;
        yPos= groundH+50;
        orgYPos = yPos;
        velocity = 0;
        isSpaceship=false;
    }
    //You jump
    public void jump(){
        if (passPortal) {

            if (this.onGround()) {
                velocity = -hop;
            }
        }
        else {

            if (this.onGround()) {
                velocity = hop;
            }
        }
    }
    //This simulates gravity. If the player is above the ground, it will fall at an increasing rate
    public void move()
    {
        yPos+=velocity;
        if(!this.onGround()) {
            if(!passPortal){
                velocity -= GRAVITY;
            }
            else{
                velocity += GRAVITY;
            }
        }
        else
        {
            if(passPortal){
                velocity = 0;
                yPos = 600 - groundH;
            }
            else{
                velocity=0;
                yPos=groundH+50;
            }
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
        if((600 - yPos <= p.getY()) && (xPos + 50 >= p.getX())&& (xPos + 50 >= p.getX()) && (xPos <= p.getX() + 40*p.getWidth())&&!passPortal){
            groundH = 600 - p.getY();
        }
        else if((600 - yPos >= p.getY()+40*p.getHeight()) && (xPos + 50 >= p.getX())&& (xPos + 50 >= p.getX()) && (xPos <= p.getX() + 40*p.getWidth())&&passPortal){
            groundH = p.getY()+40*p.getHeight();
        }
        else{
            groundH = permGround;
        }
    }

    public void touchPortal(Portal p1){
        if(650 - yPos >= p1.getY() && 650 - yPos <= p1.getY() + 170 && xPos >= p1.getX() && xPos <= p1.getX()+92){
            if(!p1.isPassed()) {
                passPortal = !passPortal;
                velocity = 0;
                p1.justPassed();
            }
        }
    }

    //Is used by the ActionPerformed inside the GameRunner
    //Sees if the player has touched the nearest spike to the right
    public boolean isDead(Spike s, Pillar p){
            if(600-yPos >= s.getY()[1] - 50 && xPos < s.getX()[1] && xPos + 50 > s.getX()[1]){
                return true;
            }
            if(xPos + 50 > s.getX()[0] && xPos + 50 < s.getX()[1] && 650-yPos >= (int)(s.getY()[0] - (Math.pow(3, 0.5))*(xPos + 50  - s.getX()[0]))){
                return true;
            }
            if(600 -yPos >= p.getY() && 650 - yPos <= p.getY() + 40*p.getHeight() && xPos + 50 >= p.getX() && xPos + 50 < p.getX() + 40*p.getWidth()){
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
    public boolean ifPassedPortal(){return passPortal;}

    public boolean onGround()
    {
        if (passPortal) {
            if (yPos >= 600 - groundH)
            return true;
        return false;
        }
        else
        {
            if (yPos - 50 <= groundH)
                return true;
            return false;
          }
    }
    public void reset()

    {
        groundH=permGround;
        yPos = orgYPos;
        angle = 360;
        velocity=0;
        passPortal = false;

    }
}
