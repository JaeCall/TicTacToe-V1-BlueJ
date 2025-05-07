
import java.awt.Color;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.*;


public class X extends JPanel
{
    private Image x;
    private Image xSet;
    private boolean isSet = true;
    X()
    {
        this.setPreferredSize(new Dimension(150,150)); 
        //for the highlighted image
        this.x = Toolkit.getDefaultToolkit().getImage("C:\\Coding Files\\2D_tic_tac_toe\\tic_tac_toe_x_off.jpg");
        //image for the set move
        this.xSet = Toolkit.getDefaultToolkit().getImage("C:\\Coding Files\\2D_tic_tac_toe\\tic_tac_toe_x_on.jpg");
        setOpaque(true);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Color bColor = new Color(135, 0, 135);
        setBackground(bColor);
        
        if(isSet)
        {
           g.drawImage(xSet, 0, 0, this); 
        }
        else
        {
            g.drawImage(x, 0, 0, this);
        }
            
    }
    public void toggleImage()
    {
        isSet = !isSet;
        repaint();
    }
         
    
    
 }

