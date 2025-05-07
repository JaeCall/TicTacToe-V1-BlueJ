
import java.awt.Color;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.*;


public class Title extends JPanel implements Runnable
{
    private Image ticHighlight;
    private Image tacHighlight;
    private Image toeHighlight;
    private Thread animationThread;
    
    private int frame;
    
    Title()
    {
        this.setMinimumSize(new Dimension(400,150)); 
        //for the highlighted image
        this.ticHighlight = Toolkit.getDefaultToolkit().getImage("Tic_Tac_Toe_ticHighlight.jpg");
        //image for the set move
        this.tacHighlight = Toolkit.getDefaultToolkit().getImage("Tic_Tac_Toe_tacHighlight.jpg");
        
        this.toeHighlight = Toolkit.getDefaultToolkit().getImage("Tic_Tac_Toe_toeHighlight.jpg");
        setOpaque(true);
        animationThread = new Thread(this);
        animationThread.setDaemon(true);
        animationThread.start();
        System.out.println("Animation Thread alive "  + animationThread.isAlive());
    }
    public void run()
    {
        try
        {
        while(animationThread.isAlive())
        {
            //System.out.println("Title run() running");
            nextFrame();
            animationThread.sleep(1000/3);
            
            
        }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Color bColor = new Color(135, 0, 135);
        setBackground(bColor);
        if(frame==0)
        {
            g.drawImage(ticHighlight, 0, 0, this);
            //System.out.println("Title image one painted");
        }
        if(frame==1)
        {
            g.drawImage(tacHighlight, 0, 0, this);
            //System.out.println("Title image two painted");
        }
        if(frame==2)
        {
            g.drawImage(toeHighlight, 0, 0, this);
            //System.out.println("Title image three painted");
        }  
    } 
    public void nextFrame()
    {
        frame++;
        if(frame > 2)
        {
            frame = 0;
        }
        repaint();
        //System.out.println("Next Frame Executed");
    }
 }
 
