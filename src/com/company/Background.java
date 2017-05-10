package com.company;

/**
 * Created by apcsaper3 on 5/10/17.
 */
/**
 * Created by apcsaper3 on 5/10/17.
 */
import java.util.ArrayList;
/**
 * Created by apcsaper5 on 5/8/17.
 */
public class Background {
    private int ground;
    private Box b;
    private ArrayList<Spike> spikes;
    private int numSpikes;

    public Background (int g)
    {
        ground = g;
        b = new Box("Bread");
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



}