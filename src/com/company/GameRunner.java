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
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.Graphics2D;






/**

 * Created by apcsaper5 on 5/8/17

 */

public class GameRunner extends JComponent implements ActionListener, KeyListener

{
    private int width ,height;
    private Image sprite;
    private Image background1;
    private Image background2;
    private Image ground1;
    private Image ground2;
    private int angle = 360;
    private int angleThreshold = 180;
    private Graphics g;


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
        width= 2000;
        height=600;
        background= new Background(width, height);
        addKeyListener(this);
        try {
            sprite = ImageIO.read(new File("Sprite1.png"));
            background1 = ImageIO.read(new File("background1.png"));
            background2 = ImageIO.read(new File("background2.png"));
            ground1 = ImageIO.read(new File("ground1.png"));
            ground2 = ImageIO.read(new File("ground1.png"));

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
        this.g = g;
        //draws the first background rectangle
        g.drawImage(background1, background.getBackgroundX(), 0, null);
        g.drawImage(background2, background.getBackground2X(), 0, null);

        g.drawImage(ground1, background.getGroundX(), 600 - background.getGroundHeight(), null);
        g.drawImage(ground2, background.getGround2X(), 600 - background.getGroundHeight(), null);

        //g.drawImage(sprite, background.getBox().getX(), height-background.getBox().getY(), null);

        ArrayList<Spike> spikes=  background.getSpikes();
        g.setColor(Color.BLACK);
        for(Spike s: spikes)
        {
            g.fillPolygon(s.getX(), s.getY(), 3);
        }
        rotateImage();
    }



    @Override

    public void actionPerformed(ActionEvent e)

    {

        if (!background.getBox().isDead(background.getNextSpike()))
        {
            background.passedSpike();
            background.shiftLeft();
            background.getBox().move();
            if (!background.getBox().onGround() && angle<angleThreshold) {
                {
                    angle += 5;
                }
            }

            repaint();
        }

    }
    public void rotateImage() {

        AffineTransform at = AffineTransform.getTranslateInstance(background.getBox().getX() ,600 - background.getBox().getY());
        at.rotate(Math.toRadians(angle), 25 , 25);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(sprite, at, null);

    }

    public void keyPressed(KeyEvent e){

        if(e.getKeyCode() == 32) {
            if (background.getBox().onGround()) {
                if (angle <= 180)//upside down
                {
                    angleThreshold = 360;
                    angle = 180;
                } else {
                    angleThreshold = 180;
                    angle = 0;
                }
                background.getBox().jump();
            }

        }

    }
    public void keyReleased(KeyEvent e){



    }
    public void keyTyped(KeyEvent e){}



}
