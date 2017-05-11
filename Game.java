/**
 * Created by michaelxiong on 5/10/17.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JComponent implements ActionListener, KeyListener
{
    private int direction = 10;
    private boolean d = true;
    private int playerX = 100;
    private int playerY = 300;
    private int platformY = 300;
    private boolean move = false;
    private int backX = 0;
    private int backY = 0;
    private int backX1 = 1000;
    private int backY1 = 0;


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
    public Game(){
        addKeyListener(this);
        setFocusable(true);
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(Graphics g)
    {

        g.setColor(new Color(178, 115, 156));
        g.fillRect(backX,backY,1000,600);

        g.setColor(new Color(145, 223, 224));
        g.fillRect(backX1,backY1,1000,600);


        g.setColor(Color.BLACK);
        g.fillRect(playerX,playerY, 25, 25);




    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (backX + 1000 < 0)
        {
            backX = backX1 +1000;
        }
        else if (backX1 + 1000 < 0)
        {
            backX1 = backX + 1000;
        }
        backX -=5;
        backX1-=5;

        if(move){
            if(playerY != platformY - 63){
                playerY -= 3;
            }
            else{
                move = false;
            }
        }
        if(!move && playerY != platformY){
            playerY += 3;
        }
        repaint();

    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == 32){
            if(playerY == platformY){
                move = true;
            }
        }
    }
    public void keyReleased(KeyEvent e){

    }
    public void keyTyped(KeyEvent e){}
}

