package com.company;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.imageio.*;
import java.io.IOException;
import java.io.*;







/**

 * Created by apcsaper5 on 5/8/17

 */

public class GameRunner extends JComponent implements ActionListener, KeyListener

{
    private int width ,height;
    private Image img;

    Background background;



    public static void main(String[] args)
    {

        JFrame window = new JFrame("Geometry Dash");
        GameRunner game1 = new GameRunner();
        window.add(game1);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        Timer time = new Timer(10, game1);
        time.start();


    }
    public GameRunner(){
        width=900;
        height=600;
        background= new Background(width, height);
        addKeyListener(this);
        try {
            img = ImageIO.read(new File("Sprite1.png"));
        }
        catch (IOException e1) {}
        setFocusable(true);

    }



    public Dimension getPreferredSize()

    {
        return new Dimension(800, 600);
    }



    @Override

    protected void paintComponent(Graphics g)

    {
        //draws the first background rectangle
        g.setColor(new Color(178, 223, 224));
        g.fillRect(background.getBackgroundX(),0,width, height);
        g.fillRect(background.getBackground2X(),0,width, height);


        g.setColor(Color.RED);
        g.drawImage(img, background.getBox().getX(), height-background.getBox().getY(), null);


        g.setColor(Color.BLACK);
        g.fillRect(0,height- background.getGroundHeight(), 80000, background.getGroundHeight());
        ArrayList<Spike> spikes=  background.getSpikes();
        g.setColor(Color.BLACK);
        for(Spike s: spikes)
        {
            g.fillPolygon(s.getX(), s.getY(), 3);
        }
    }



    @Override

    public void actionPerformed(ActionEvent e)

    {

        if (!background.getBox().isDead(background.getNextSpike()))
        {
            background.passedSpike();
            background.shiftLeft();
            background.getBox().move();
            repaint();
        }

    }
    public void keyPressed(KeyEvent e){

        if(e.getKeyCode() == 32){
            background.getBox().jump();
        }

    }
    public void keyReleased(KeyEvent e){



    }
    public void keyTyped(KeyEvent e){}



}
