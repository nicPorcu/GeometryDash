package com.company;

import java.util.ArrayList;

/**

 * Created by apcsaper5 on 5/8/17.

 */
public class Background {
    private int ground;
    private int ground1;
    private final int initialGroundX;
    private final int initialGround2X;
    private Box b;
    private ArrayList<Spike> spikes;
    private int numSpikes;
    private int backgroundX;
    private int background2X;
    private final int initialBackgroundX;
    private final int initialBackground2X;
    private int width;
    private int height;
    private int backgroundSpd;
    private int spikesPassed;
    private int ringsPassed;
    private int pillarsPassed;
    private int groundX;
    private int ground2X;
    private int groundSpd;
    private int numPortals;
    private int portalX;
    private int portalY;
    private int portalsPassed;
    private ArrayList<JumpRing> rings;
    private ArrayList<Pillar> pillars;
    private ArrayList<Portal> portals;
    private ArrayList<Integer> pillarLocX;
    private ArrayList<Integer> pillarLocY;
    private ArrayList<Integer> pillarWidth;
    private ArrayList<Integer> pillarHeight;
    private int numPillars;
    private int numRings;
    private int spikeThreshold;
    private int spikeCounter;
    private int numAttempts;
    private int attemptStringXPos;
    private int attemptStringInitialXPos;

    private int bestScore;

    //because the getNextSpike and getNextRing classes will cause a crash after passing the last spike or ring, i made
    //a spike and a ring that could not be passed at the very end of the list.
    private Spike untouchableSpike = new Spike(0, 2000);
    private JumpRing untouchableRing = new JumpRing (0, 2000);
    private Pillar untouchablePillar = new Pillar( 0, 0, 0, 0);
    private Portal untouchablePortal = new Portal(0,0);
    //constructor where all the instance variables are initialized:
    public Background (int width, int height)
    {
        this.width=width;
        this.height=height;
        backgroundX=0;
        background2X=width;
        initialBackgroundX=backgroundX;
        initialBackground2X=background2X;
        ground = 100;
        ground1 = 400;
        b = new Box(ground);
        numSpikes=10;
        numRings =2;
        backgroundSpd = 1;
        groundSpd= 3;
        groundX = 0;
        ground2X = 2000;
        initialGroundX=groundX;
        initialGround2X=ground2X;
        int lastLoc=500;
        int lastRingLoc = 500;
        spikesPassed = 0;
        ringsPassed = 0;
        portalsPassed = 0;
        pillarsPassed = 0;
        spikeThreshold = 2;
        spikeCounter=1;
        numPillars = 3;
        numPortals = 1;
        portalX = 2500;
        portalY = 300;
        spikes=new ArrayList<Spike>();
        pillarLocX = new ArrayList<Integer>();
        pillarLocY = new ArrayList<Integer>();
        pillarWidth = new ArrayList<Integer>();
        pillarHeight = new ArrayList<Integer>();
        pillarLocX.add(650);
        pillarLocX.add(850);
        pillarLocX.add(1450);
        pillarLocY.add(height - ground1 + 100);
        pillarLocY.add(height - ground1 +100);
        pillarLocY.add(ground1 + 20);
        pillarWidth.add(2);
        pillarWidth.add(5);
        pillarWidth.add(1);
        pillarHeight.add(1);
        pillarHeight.add(1);
        pillarHeight.add(2);
        numAttempts=1;
        attemptStringXPos=100;
        attemptStringInitialXPos=attemptStringXPos;
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
        pillars = new ArrayList<Pillar>();
        for(int i = 0; i<numPillars; i ++){
            pillars.add(new Pillar(pillarLocX.get(i), pillarLocY.get(i), pillarWidth.get(i), pillarHeight.get(i)));
        }

        portals = new ArrayList<Portal>();
        for(int i = 0; i<numPortals; i ++){
            portals.add(new Portal(portalX, portalY));
            portalX += 1000;
        }



        bestScore=0;
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
    public void passedPillar(){
        if(b.getX() > (pillars.get(pillarsPassed).getX() + 40*pillars.get(pillarsPassed).getWidth())){
            if(pillarsPassed < numPillars - 1){

                pillarsPassed += 1;

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

    public void passedPortal(){
        if(b.getX() > portals.get(portalsPassed).getX()){
            if(portalsPassed < numPortals - 1){
                portalsPassed += 1;
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
        for (int i= 0; i< numPillars; i++)
        {
            pillars.get(i).shiftLeft(groundSpd);
        }
        for (int i= 0; i< numPortals; i++)
        {
            portals.get(i).shiftLeft(groundSpd);
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
        portalX -= groundSpd;
        if(groundX <= -1*width){
            groundX = width;
        }
        if(ground2X <= -1*width){
            ground2X = width;
        }
        attemptStringXPos-=backgroundSpd;
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
    public Pillar getNextPillar(){
        if(pillarsPassed < numPillars){
            return pillars.get(pillarsPassed);
        }
        return untouchablePillar;
    }
    public ArrayList<Pillar> getPillars() {return pillars;}
    //Similar purpose to getNextSpike, but for JumpRings
    public JumpRing getNextRing(){
        if(ringsPassed < numRings) {
            return rings.get(ringsPassed);
        }
        return untouchableRing;
    }

    public Portal getNextPortal(){
        if(portalsPassed < numPortals) {
            return portals.get(portalsPassed);
        }
        return untouchablePortal;
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
    public int getPortalY() {return portalY;}
    public int getPortalX() {return portalX;}
    public ArrayList<Portal> getPortals() {return portals;}
    public void reset()
    {
            numAttempts++;
            attemptStringXPos = attemptStringInitialXPos;
            groundX = initialGroundX;
            ground2X = initialGround2X;
            backgroundX = initialBackgroundX;
            background2X = initialBackground2X;
            spikesPassed = 0;
            ringsPassed = 0;
            pillarsPassed = 0;
            for (Spike s : spikes) {
                s.reset();
            }
            for (JumpRing r : rings) {
                r.reset();
            }
            for (Pillar p : pillars) {
                p.reset();
            }
            for (Portal p : portals) {
                p.reset();
            }




    }
    public String getNumAttempts()
    {
        return "Attempt "+ numAttempts;
    }
    public int attemptStringPosition()
    {
        return attemptStringXPos;
    }

}