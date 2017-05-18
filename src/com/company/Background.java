package com.company;

import java.util.ArrayList;

/**

 * Created by apcsaper5 on 5/8/17.

 */
public class Background {
    private int ground;
    private Box b;
    private ArrayList<Spike> spikes;
    private int numSpikes;
    private int backgroundX;
    private int background2X;
    private int width;
    private int height;
    private int backgroundSpd;
    private int spikesPassed;
    private int groundX;
    private int ground2X;
    private int groundSpd;
    private JumpRing jumpRing1;
    private int jumpRing1X;

    public Background (int width, int height)
    {
        jumpRing1 = new JumpRing(500);
        jumpRing1X = jumpRing1.getX();
        this.width=width;
        this.height=height;
        backgroundX=0;
        background2X=width;
        ground = 100;
        b = new Box("Bread", ground);
        numSpikes=50;
        backgroundSpd = 1;
        groundSpd= 3;
        groundX = 0;
        ground2X = 2000;
        int lastLoc=500;
        spikesPassed = 0;
        spikes=new ArrayList<Spike>();
        for (int  i=0; i<numSpikes; i++)

        {
            spikes.add(new Spike(lastLoc, height - ground));
            lastLoc+=200;
        }
    }
    public void passedSpike(){
        if(b.getX() > spikes.get(spikesPassed).getX()[1]){
            spikesPassed += 1;
        }
    }
    public void shiftLeft()
    {
        for (int i= 0; i< spikes.size(); i++)
        {
            spikes.get(i).shiftLeft(groundSpd);
        }
        backgroundX-= backgroundSpd;
        background2X-= backgroundSpd;
        if(backgroundX<=-1*width){
            backgroundX=width;
        }
        if(background2X<=-1*width)
        {
            background2X=width;
        }
        groundX -= groundSpd;
        ground2X -= groundSpd;
        jumpRing1X-= groundSpd;
        if(groundX <= -1*width){
            groundX = width;
        }
        if(ground2X <= -1*width){
            ground2X = width;
        }
    }

    public Spike getNextSpike(){
            return spikes.get(spikesPassed);
    }

    public ArrayList<Spike> getSpikes()
    {
        return spikes;
    }
    public int getGroundHeight()
    {
        return ground;
    }
    public Box getBox()
    {
        return b;
    }
    public int getBackgroundX()
    {
        return backgroundX;
    }
    public int getBackground2X()
    {
        return background2X;
    }
    public int getGroundX(){return groundX;}
    public int getGround2X(){return ground2X;}
    public int getJumpRing1X() {return jumpRing1X;}
}