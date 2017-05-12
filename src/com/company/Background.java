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

    public Background (int width, int height)
    {
        this.width=width;
        this.height=height;
        backgroundX=0;
        background2X=width;
        ground = 5;
        b = new Box("Bread", ground);
        numSpikes=50;
        int lastLoc=500;
        spikes=new ArrayList<Spike>();
        for (int  i=0; i<numSpikes; i++)

        {
            spikes.add(new Spike(lastLoc, ground));
            lastLoc+=100;
        }
    }

    public void shiftLeft()
    {
        for (int i= 0; i< spikes.size(); i++)
        {
            spikes.get(i).shiftLeft();
        }
        backgroundX--;
        background2X--;
        if(backgroundX<=-1*width){
            backgroundX=width;
        }
        if(background2X<=-1*width)
        {
            background2X=width;
        }
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