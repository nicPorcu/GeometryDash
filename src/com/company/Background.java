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
    private int spikeThreshold;
    private int spikeCounter;

    //because the getNextSpike and getNextRing classes will cause a crash after passing the last spike or ring, i made
    //a spike and a ring that could not be passed at the very end of the list.
    private Spike untouchableSpike = new Spike(0, 2000);
    private JumpRing untouchableRing = new JumpRing (0, 2000);
    //constructor where all the instance variables are initialized:
    public Background (int width, int height)
    {
        this.width=width;
        this.height=height;
        backgroundX=0;
        background2X=width;
        ground = 100;
        b = new Box(ground);
        numSpikes=50;
        numRings =2;
        backgroundSpd = 1;
        groundSpd= 3;
        groundX = 0;
        ground2X = 2000;
        int lastLoc=500;
        int lastRingLoc = 500;
        spikesPassed = 0;
        ringsPassed = 0;
        spikeThreshold = 2;
        spikeCounter=1;
        spikes=new ArrayList<Spike>();
        for (int  i=0; i<numSpikes; i++)
        {
            spikes.add(new Spike(lastLoc, height - ground));
            lastLoc+= getNextSpikeLoc();
        }
        rings = new ArrayList<JumpRing>();
        for(int i = 0; i<numRings; i ++){
            rings.add(new JumpRing(lastRingLoc, 400));
            lastRingLoc += 800;
        }

    }
    //This method updates the number of spikes passed. Necessary for the method that finds the next spike.
    //This is necessary to prevent the program from checking every single spike to see if it is touching the player.
    public void passedSpike(){
        if(b.getX() > spikes.get(spikesPassed).getX()[1]){
            if(spikesPassed < numSpikes - 1){
                spikesPassed += 1;
            }
        }
    }
    //Similar method as the above, but for JumpRings instead.
    public void passedRing(){
        if(b.getX() > rings.get(ringsPassed).getRingX()){
            if(ringsPassed < numRings - 1){
                ringsPassed += 1;
            }
        }
    }

    public int getNextSpikeLoc()
    {
        int x = (int) (Math.random() * 150) + 150;
        int randomChance = (int)(Math.random() * 3 + 1);
        if (spikeCounter >= spikeThreshold)
        {
            spikeCounter = 1;
        }
        else if (spikeCounter < spikeThreshold && randomChance == 1)
        {
            spikeCounter++;
            return 40; //triangle width, this makes it right next to it
        }
        return x;
    }
    //This method is called in the ActionPerformed of the GameRunner class. It moves every object
    //in the screen by the proper amount.
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
    //This method returns the closest spike to the right (first unpassed spike).
    //Prevents the program from needing to compare the position of the player to the position of every spike
    public Spike getNextSpike(){
        if(spikesPassed < numSpikes){
            return spikes.get(spikesPassed);
        }
        return untouchableSpike;
    }
    //This allows the arraylist of spikes to be accessed in other classes, specifically GameRunner
    public ArrayList<Spike> getSpikes()
    {
        return spikes;
    }
    //Similar purpose to getNextSpike, but for JumpRings
    public JumpRing getNextRing(){
        if(ringsPassed < numRings) {
            return rings.get(ringsPassed);
        }
        return untouchableRing;
    }
    //Similar purpose to getSpikes, but for JumpRings
    public ArrayList<JumpRing> getRings(){
        return rings;
    }
    //This allows the GameRunner class to know the height of the ground. Kinda useful if you want things drawn properly.
    public int getGroundHeight()
    {
        return ground;
    }
    //This allows the GameRunner class to attain information about the player
    public Box getBox()
    {
        return b;
    }
    //I think you know what these are.
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