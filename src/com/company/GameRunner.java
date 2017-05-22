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
import java.awt.GraphicsEnvironment;
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
    private Image jumpRing;
    private Image pillar;
    private Image portal;
    private Graphics g;
    private int toDie;


    Background background;


//The main. You sorta need it
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
    //in the constructor, all instances are assigned, including the images. A background object is created.
    //The background object basically keeps track of most of the game. GameRunner is mostly responsible for drawing things
    public GameRunner(){
        width= 2000;
        height=600;
        toDie = 0;
        background= new Background(width, height);

        addKeyListener(this);
        try {
            sprite = ImageIO.read(new File("Sprite1.png"));
            background1 = ImageIO.read(new File("background1.png"));
            background2 = ImageIO.read(new File("background2.png"));
            ground1 = ImageIO.read(new File("ground1.png"));
            ground2 = ImageIO.read(new File("ground1.png"));
            jumpRing = ImageIO.read(new File("jump1.png"));
            pillar = ImageIO.read(new File("block.png"));
            portal = ImageIO.read(new File("portal.png"));
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("PUSAB___.otf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(48f);

        }
        catch (IOException|FontFormatException e1) {}
        setFocusable(true);


    }


    //idk what this is and why this is here wtf
    public Dimension getPreferredSize()

    {
        return new Dimension(1000, 600);
    }


    //Here's where we have fun drawing things. Everything in here is redrawn each time repaint is called in ActionPerformed
    @Override

    protected void paintComponent(Graphics g)

    {
        this.g = g;
        //draws the background rectangles
        g.drawImage(background1, background.getBackgroundX(), 0, null);
        g.drawImage(background2, background.getBackground2X(), 0, null);
        //draws the ground
        g.drawImage(ground1, background.getGroundX(), 600 - background.getGroundHeight(), null);
        g.drawImage(ground2, background.getGround2X(), 600 - background.getGroundHeight(), null);
        g.setColor(Color.white);
        g.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 36));
        g.drawString( background.getNumAttempts(), background.attemptStringPosition(), height/2 + 75);
        ArrayList<Spike> spikes= background.getSpikes();
        g.setColor(Color.BLACK);
        for(Spike s: spikes)
        {
            g.fillPolygon(s.getX(), s.getY(), 3);
        }
        ArrayList<JumpRing> rings = background.getRings();
        for(JumpRing r : rings){
            g.drawImage(jumpRing, r.getRingX(), r.getRingY(), null);
        }
        ArrayList<Pillar> pillars = background.getPillars();
        for(Pillar p : pillars){
            for(int i = 0; i < p.getWidth(); i ++){
                for(int j = 0; j< p.getHeight(); j++){
                    int newX = p.getX() + i*40;
                    int newY = p.getY() + j*40;
                    g.drawImage(pillar, newX, newY, null);
                }
            }
        }
        ArrayList<Portal> portals = background.getPortals();
        for(Portal p :portals){
            g.drawImage(portal, p.getX(), p.getY(), null);
        }
        rotateImage();


    }


    //This action repeats itself on a timer, every 10 milliseconds. As you can see, most actions are
    //calling methods created within the background or box classes.
    @Override

    public void actionPerformed(ActionEvent e)

    {

        if (!background.getBox().isDead(background.getNextSpike(), background.getNextPillar()))
        {
            background.passedSpike();
            background.passedRing();
            background.passedPillar();
            background.passedPortal();
            background.shiftLeft();
            background.getBox().move();
            background.getBox().touchRing(background.getNextRing());
            background.getBox().touchPillar(background.getNextPillar());
            background.getBox().touchPortal(background.getNextPortal());
            if(!background.getBox().ifPassedPortal()){
                if (!background.getBox().onGround() && background.getBox().getAngle()< background.getBox().getAngleThreshold())
                {
                    background.getBox().setAngle(background.getBox().getAngle() + 5);
                }
            }
            else{
                if (!background.getBox().onGround() && background.getBox().getAngle() > -1* background.getBox().getAngleThreshold())
                {
                    background.getBox().setAngle(background.getBox().getAngle() - 5);
                }
            }
            if(background.getBox().onGround()){
                    background.getBox().setAngle(background.getBox().getAngleThreshold());
            }


        }
        else{
            toDie += 1;
            if(toDie == 50){background.reset();
            toDie = 0;}
        }
        repaint();

    }
    //This rotates the image and then redraws it. Nobody knows how or why this works. Just roll with it, ok?
    public void rotateImage() {

        AffineTransform at = AffineTransform.getTranslateInstance(background.getBox().getX() ,600 - background.getBox().getY());
        at.rotate(Math.toRadians(background.getBox().getAngle()), 25 , 25);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(sprite, at, null);

    }
    //Whenever a key is pressed, ActionListener calls this method. If the key code is 32, the space bar, it will cause something to happen.
    public void keyPressed(KeyEvent e){

        if(e.getKeyCode() == 32) {
            if (background.getBox().onGround()) {
                if (background.getBox().getAngle() <= 180)//upside down
                {
                    background.getBox().setAngleThreshold(360);
                    if(background.getBox().ifPassedPortal()){
                        background.getBox().setAngle(-180);
                    }
                    else{
                        background.getBox().setAngle(180);
                    }
                }
                else {
                    background.getBox().setAngleThreshold(180);
                    background.getBox().setAngle(0);
                }
                background.getBox().jump();
            }

        }

    }
    //Because this class extends ActionListener, these methods needed to be implemented. They do nothing tho.
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}



}
