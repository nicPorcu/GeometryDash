package com.company;

import java.util.ArrayList;

/**

 * Created by apcsaper5 on 5/8/17.

 */
public class Background {
    private int ground;
    private int permGround;
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
    private final int initialGroundtX;
    private final int initialGroundt1X;
    private int width;
    private int height;
    private int backgroundSpd;
    private int spikesPassed;
    private int ringsPassed;
    private int pillarsPassed;
    private int groundX;
    private int groundtX;
    private int groundt1X;
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
    private double score;
    private double bestScore;
    private double levelLength;

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
        numSpikes=50;
        numRings =2;
        backgroundSpd = 1;
        groundSpd= 3;
        groundX = 0;
        groundtX = 0;
        groundt1X = 2000;
        ground2X = 2000;
        initialGroundX=groundX;
        initialGround2X=ground2X;
        initialGroundtX = groundtX;
        initialGroundt1X = groundt1X;
        int lastLoc=500;
        int lastRingLoc = 500;
        spikesPassed = 0;
        ringsPassed = 0;
        portalsPassed = 0;
        pillarsPassed = 0;
        spikeThreshold = 2;
        spikeCounter=1;
        numPillars = 12;
        numPortals = 2;
        portalX = 2500;
        portalY = 300;
        spikes=new ArrayList<Spike>();
        pillarLocX = new ArrayList<Integer>();
        pillarLocY = new ArrayList<Integer>();
        pillarWidth = new ArrayList<Integer>();
        pillarHeight = new ArrayList<Integer>();
        pillarLocX.add(650);
        pillarLocX.add(900);
        pillarLocX.add(1450);
        pillarLocX.add(3800);
        pillarLocX.add(3950);
        pillarLocX.add(4100);
        pillarLocX.add(4250);
        pillarLocX.add(4400);
        pillarLocX.add(4550);
        pillarLocX.add(4700);
        pillarLocX.add(4850);
        pillarLocX.add(5200);


        pillarLocY.add(height - ground1 + 100);
        pillarLocY.add(height - ground1 +100);
        pillarLocY.add(ground1 + 20);
        pillarLocY.add(ground1+20);
        pillarLocY.add(ground1-40);
        pillarLocY.add(ground1-100);
        pillarLocY.add(ground1-160);
        pillarLocY.add(ground1-100);
        pillarLocY.add(ground1-40);
        pillarLocY.add(ground1+20);
        pillarLocY.add(ground1-40);
        pillarLocY.add(ground1-40);



        pillarWidth.add(3);
        pillarWidth.add(5);
        pillarWidth.add(1);
        pillarWidth.add(1);
        pillarWidth.add(1);
        pillarWidth.add(1);
        pillarWidth.add(1);
        pillarWidth.add(1);
        pillarWidth.add(1);
        pillarWidth.add(1);
        pillarWidth.add(6);
        pillarWidth.add(1);


        pillarHeight.add(1);
        pillarHeight.add(1);
        pillarHeight.add(2);
        pillarHeight.add(1);
        pillarHeight.add(1);
        pillarHeight.add(1);
        pillarHeight.add(1);
        pillarHeight.add(1);
        pillarHeight.add(1);
        pillarHeight.add(1);
        pillarHeight.add(1);
        pillarHeight.add(1);
        pillarHeight.add(1);
        numAttempts=1;
        attemptStringXPos=100;
        attemptStringInitialXPos=attemptStringXPos;
        permGround=ground;
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

            portalY = ground;
            portalY = 100;

        }


        levelLength= spikes.get(numSpikes - 1).getX()[2];
        score=0;

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
        score+=groundSpd;
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
            pillars.get(i).shiftLeft(groundSpd);//chs
        }
        for (int i= 0; i< numPortals; i++)
        {
            portals.get(i).shiftLeft(groundSpd);
        }//hi
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
        groundtX -= groundSpd;
        groundt1X -= groundSpd;
        ground2X -= groundSpd;
        portalX -= groundSpd;
        if(groundX <= -1*width){
            groundX = width;
        }
        if(ground2X <= -1*width){
            ground2X = width;
        }
        if(groundtX <= -1*width){
            groundtX = width;
        }
        if(groundt1X <= -1*width){
            groundt1X = width;
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
        return b; //hi
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
    public int getGroundtX(){return groundtX;}
    public int getGroundt1X(){return groundt1X;}
    public int getGround2X(){return ground2X;}
    public int getPortalY() {return portalY;}
    public int getPortalX() {return portalX;}
    public ArrayList<Portal> getPortals() {return portals;}
    public void reset()
    {
            ground=permGround;
            score=0;
            numAttempts++;
            attemptStringXPos = attemptStringInitialXPos;
            groundX = initialGroundX;
            ground2X = initialGround2X;
            groundtX = initialGroundtX;
            groundt1X = initialGroundt1X;
            backgroundX = initialBackgroundX;
            background2X = initialBackground2X;
            spikesPassed = 0;
            ringsPassed = 0;
            pillarsPassed = 0;
            portalsPassed = 0;
            b.reset();
            for (Spike s : spikes) {
                s.reset();
            }
            for (JumpRing r : rings) {
                r.reset();
            }
            for (Pillar p : pillars) {
                p.reset();
            }
            for (Portal x : portals) {
                x.reset();
            }




    }
    public String currentScore(){
        String s="Current Score: \n" + (int)(score/levelLength*100)+"%";
        return s;
    }
    public String bestScore(){
        String s = "";
        if(numAttempts == 1){
            s="Best Score: \n" + (int)(score/levelLength*100)+"%";
        }
        else{
            s="Best Score: \n" + (int)(bestScore/levelLength*100)+"%";
        }
        return s;
    }
    public String newBestScore()
    {
        if(score>=bestScore&&b.isDead(spikes.get(spikesPassed), pillars.get(pillarsPassed)))
        {
            bestScore=score;
            String s="New Best Score: \n" + (int)(score/levelLength*100)+"%";
            return s;
        }
        else
        {
            return"";
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