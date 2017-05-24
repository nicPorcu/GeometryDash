package com.company;

/**
 * Created by apcsaper5 on 5/22/17.
 */
public class Portal {


    private int x;
    private int y;
    private int initialX;
    private int initialY;
    private boolean passed;

            public Portal (int x, int y){
                this.x = x;
                this.y = y;
                initialX = x;
                initialY = y;
                passed = false;
            }

            public int getX()
            {
                return x;
            }

            public int getY()
            {
                return y;
            }

            public boolean isPassed(){return passed;}
            public void justPassed(){passed = true;}

            public void shiftLeft(int spd){
                x -= spd;
            }

            public void reset()
            {
                x=initialX;
                y= initialY;
                passed = false;
            }


    }


