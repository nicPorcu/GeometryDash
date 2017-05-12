package com.company;



/**

 * Created by apcsaper3 on 5/10/17.

 */

/**

 *   h8 Created by apcsaper3 on 5/10/17.

 */

public class Spike {

    private int[] x;
    private int[] y;
    private int height;
    public Spike(int leftX, int leftY){
        this.x = new int[3];
        this.y = new int[3];
        x[0] = leftX;
        x[1] = (leftX+ 20);
        x[2] = (leftX + 40);
        y[0] = leftY;
        y[1] = leftY - 34;
        y[2] = leftY;
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

}
