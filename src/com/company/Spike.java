package com.company;



/**

 * Created by apcsaper3 on 5/10/17.

 */

/**

 *   h8 Created by apcsaper3 on 5/10/17.

 */

public class Spike {//kills you. No touchy

    private int[] x;
    private int[] initialX;
    private int[] y;
    private int[] initialY;
    private boolean up;
    //as a polygon, an array of points must be specified as the vertices. Trying to make close to equilateral in shape
    public Spike(int leftX, int leftY, boolean u){
        up = u;
        this.x = new int[3];
        this.y = new int[3];
        x[0] = leftX;
        x[1] = (leftX+ 20);
        x[2] = (leftX + 40);
        y[0] = leftY;
        if(up){
            y[1] = leftY - 34;
        }
        else{
            y[1] = leftY + 34;
        }
        y[2] = leftY;
        initialX=new int[x.length];
        initialY= new int[y.length];
        for(int i= 0; i< x.length; i++)
        {
            initialX[i]= x[i];
            initialY[i]=y[i];
        }
    }



    public void shiftLeft(int speed)
    {
        for(int i = 0; i < 3; i++)
        x[i]-= speed;
    }
    public int[] getX()
    {
        return x;
    }

    public int[] getY()
    {
        return y;
    }

    public boolean getUp(){return up;}

    public void reset()
    {
        for(int i= 0; i< x.length; i++)
        {
            x[i]=initialX[i];
            y[i]=initialY[i];
        }
    }

}
