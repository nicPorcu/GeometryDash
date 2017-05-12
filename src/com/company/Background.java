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

    public Background (int width, int height)
    {
        this.width=width;
        this.height=height;
        backgroundX=0;
        background2X=width;
        ground = 20;
        b = new Box("Bread", ground);
        numSpikes=50;
        backgroundSpd = 3;
        int lastLoc=500;
        spikes=new ArrayList<Spike>();
        for (int  i=0; i<numSpikes; i++)

        {
            spikes.add(new Spike(lastLoc, height - ground));
            lastLoc+=100;
        }
    }

    public void shiftLeft()
    {
        for (int i= 0; i< spikes.size(); i++)
        {
            spikes.get(i).shiftLeft(backgroundSpd);
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
    }
    public int[] getSpikeX(){
        int[] spikeX = new int[numSpikes];
        for(int i = 0; i < spikeX.length; i++){
            spikeX[i] = spikes.get(i).getX()[1];
        }
        return spikeX;
    }
    public int[] getSpikeY(){
        int[] spikeY = new int[numSpikes];
        for(int i = 0; i < spikeY.length; i++){
            spikeY[i] = spikes.get(i).getY()[1];
        }
        return spikeY;
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
}