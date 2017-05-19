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
    private int ringsPassed;
    private int groundX;
    private int ground2X;
    private int groundSpd;
    private ArrayList<JumpRing> rings;
    private int numRings;
    private Spike untouchableSpike = new Spike(0, 2000);
    private JumpRing untouchableRing = new JumpRing (0, 2000);

    public Background (int width, int height)
    {
        this.width=width;
        this.height=height;
        backgroundX=0;
        background2X=width;
        ground = 100;
        b = new Box("Bread", ground);
        numSpikes=50;
        numRings = 1;
        backgroundSpd = 1;
        groundSpd= 3;
        groundX = 0;
        ground2X = 2000;
        int lastLoc=500;
        spikesPassed = 0;
        ringsPassed = 0;
        spikes=new ArrayList<Spike>();
        for (int  i=0; i<numSpikes; i++)
        {
            spikes.add(new Spike(lastLoc, height - ground));
            lastLoc+=200;
        }
        rings = new ArrayList<JumpRing>();
        for(int i = 0; i<numRings; i ++){
            rings.add(new JumpRing(500, 400));
        }

    }
    public void passedSpike(){
        if(b.getX() > spikes.get(spikesPassed).getX()[1]){
            if(spikesPassed < numSpikes - 1){
                spikesPassed += 1;
            }
        }
    }
    public void passedRing(){
        if(b.getX() > rings.get(ringsPassed).getRingX()){
            if(ringsPassed < numRings - 1){
                ringsPassed += 1;
            }
        }
    }
    public void shiftLeft()
    {
        for (int i= 0; i< numSpikes; i++)
        {
            spikes.get(i).shiftLeft(groundSpd);
        }
        for (int i= 0; i< numRings; i++)
        {
            rings.get(i).shiftLeft(groundSpd);
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
        if(groundX <= -1*width){
            groundX = width;
        }
        if(ground2X <= -1*width){
            ground2X = width;
        }
    }

    public Spike getNextSpike(){
        if(spikesPassed < numSpikes){
            return spikes.get(spikesPassed);
        }
        return untouchableSpike;
    }

    public ArrayList<Spike> getSpikes()
    {
        return spikes;
    }
    public JumpRing getNextRing(){
        if(ringsPassed < numRings) {
            return rings.get(ringsPassed);
        }
        return untouchableRing;
    }
    public ArrayList<JumpRing> getRings(){
        return rings;
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
}