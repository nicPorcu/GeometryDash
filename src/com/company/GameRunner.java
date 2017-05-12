package com.company;

/**
 * Created by apcsaper3 on 5/10/17.
 */
/**
 * Created by apcsaper3 on 5/10/17.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



/**
 * Created by apcsaper5 on 5/8/17
 */
public class Game extends JComponent implements ActionListener
{
    private int rectX = 100;
    private int rectY = 350;
    int width=800;
    int height=600;
    private int direction = 10;
    private boolean d = true;
    Background background=new Background(5);



    public static void main(String[] args)
    {
        JFrame window = new JFrame("Geometry Dash");
        Game game1 = new Game();
        window.add(game1);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Timer time = new Timer(10, game1);
        time.start();
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(Graphics g)
    {

        g.setColor(new Color(178, 223, 224));
        g.fillRect(0,0,width, height);

        // g.setColor(Color.BLACK);
        //g.fillOval(100,300, 25, 25);

        //g.setColor(Color.BLUE);
        //g.fillRect(rectX,rectY,150,25);


        g.setColor(Color.RED);
        g.fillRect(background.getBox().getX(),height-background.getBox().getY(),50,50);
        g.setColor(Color.GREEN);
        g.fillRect(0,height- background.getGroundHeight(), 80000, background.getGroundHeight());
        ArrayList<Spike> spikes=  background.getSpikes();
        g.setColor(Color.BLACK);

        for(Spike s: spikes)
        {
            g.fillRect(s.getX(),height -50- s.getY(), 50,50);

        }






    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        background.shiftLeft();
        if (rectX > 800 - 150 && d == true)
        {
            direction = -direction;
            d = false;
        }
        if (rectX < 0 && d == false)
        {
            direction = -direction;
            d = true;
        }
        rectX = rectX + direction;

        repaint();

    }
}
