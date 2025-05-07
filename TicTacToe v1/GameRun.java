import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.Container;


public class GameRun extends JFrame 
{
    Title title;
    X x;
    O o;
    static RestartBoolean restartButton;
    public JPanel[] panels;
    public boolean xTurn = true;
    private boolean[] panelSet;
    public boolean[] xSet;
    public boolean[] oSet;
    public static boolean win;
    public static Thread gameThread;

   
    public GameRun() 
    {
       gameThread = new Thread(new Runnable()
       {
           @Override
           public void run()
           {}
        
        });
        System.out.println("Game Thread alive "  + gameThread.isAlive());
       //RestartBoolean restartButton = new RestartBoolean();
       //title = new Title();
       gameThread.setName("Game Run");
       gameThread.start();
       System.out.println("Game Thread alive "  + gameThread.isAlive());
       window();
       
       
           
    }
    public void window()
    {   
        panelSet = new boolean[9];
        xSet = new boolean[9];
        oSet = new boolean[9];
        title = new Title();
        restartButton = new RestartBoolean();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        Container getCont = getContentPane();
        getCont.setLayout(new GridBagLayout());
        Color fColor = new Color(221,0,201);
        Color bColor = new Color(128, 0, 128);
        panels = new JPanel[9];
        getContentPane().setBackground(fColor);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                        
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(525,600);

        for(int i = 0; i < 9; i++)
        {   
            X localX = new X();
            O localO = new O();
            final int index = i;
            panels[i] = new JPanel();
            panels[i].setBackground(bColor);
            panels[i].add(localO);
            panels[i].add(localX);
        
            localX.setVisible(false);
            localO.setVisible(false);
            System.out.println("X set for panel " + index + xSet[index]);
            System.out.println("O set for panel " + index + oSet[index]);
            panels[i].addMouseListener(new MouseListener() 
                {
                    
                    @Override
                    public void mouseClicked(MouseEvent e){
                        if(panelSet[index]!=true){
                        if((xTurn == true) && (panelSet[index]!=true) && win==false)
                        {
                            
                            localX.setVisible(true);
                            repaint();
                            revalidate();
                            xSet[index] = true;
                        }
                        else
                        {
                            
                            localO.setVisible(true);
                            repaint();
                            oSet[index] = true;
                        }
                        panelSet[index]=true;                        
                        System.out.println("Panel " + index + "clicked." );
                        System.out.println("X set for panel " + index + xSet[index]);
                        System.out.println("O set for panel " + index + oSet[index]);
                        xTurn = !xTurn;
                        checkWinner();      
                        //if(win == true){ restart(); }
                        }
                        
                    }

                    
                    @Override
                    public void mousePressed(MouseEvent e) 
                    {
                        if(panelSet[index]!=true){
                        if((xTurn == true) && (panelSet[index]!=true)&& win==false)
                        {
                            
                            localX.setVisible(true);
                            repaint();
                            revalidate();
                            xSet[index] = true;
                        }
                        else
                        {
                            
                            localO.setVisible(true);
                            repaint();
                            oSet[index] = true;
                        }
                        panelSet[index]=true;                        
                        System.out.println("Panel " + index + "clicked." );
                        System.out.println("X set for panel " + index + xSet[index]);
                        System.out.println("O set for panel " + index + oSet[index]);
                        xTurn = !xTurn;
                        checkWinner();      
                        //if(win == true){ restart(); }
                    
                    }}
    
                    @Override
                    public void mouseReleased(MouseEvent e) 
                    {
                        if(panelSet[index]!=true){
                        if((xTurn == true) && (panelSet[index]!=true)&& win==false)
                        {
                            
                            localX.setVisible(true);
                            repaint();
                            revalidate();
                            xSet[index] = true;
                        }
                        else
                        {
                            
                            localO.setVisible(true);
                            repaint();
                            oSet[index] = true;
                        }
                        panelSet[index]=true;                        
                        System.out.println("Panel " + index + "clicked." );
                        System.out.println("X set for panel " + index + xSet[index]);
                        System.out.println("O set for panel " + index + oSet[index]);
                        xTurn = !xTurn;
                        checkWinner();        
                        }
                    }
                   
                    @Override
                    public void mouseEntered(MouseEvent e) 
                    {
                        if(panelSet[index]!=true && win==false)
                        {
                            if(xTurn == true)
                            {
                                localX.toggleImage();
                                localX.setVisible(true);
                            }
                            else
                            {
                                localO.toggleImage();
                                localO.setVisible(true);
                            }
                        }
                        
                        System.out.println("Mouse entered a panel " + index );
                        
                    }

                     @Override
                    public void mouseExited(MouseEvent e) 
                    {
                       
                       if(panelSet[index]!=true && win==false)
                        {
                            if(xTurn == true)
                            {
                                localX.setVisible(false);
                                //setting the toggle back to the set image
                                localX.toggleImage();
                            }
                            else
                            {   
                                //setting the toggle back to the set image
                                localO.setVisible(false);
                                localO.toggleImage();
                            }
                        }
                    
                       System.out.println("Mouse exited a panel "  + index);
                    }
                
                });                    
            add(panels[i]);    
            }
        loadConstraints(panels, gbc, getCont);       
        gbc.weightx = 3.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth = 3;
        title.setPreferredSize(new Dimension(200,400));
        getCont.add(title,gbc);
        title.setVisible(true);
        while(!win)
        {   
            System.out.println("window() while loop executed");
            try
                {
                    gameThread.sleep(5000);
                }
                catch (InterruptedException ie)
                {
                    ie.printStackTrace();
                }
            if(win)
            {   
               System.out.println("if(win) restartMenu checked");
               restartButton.RestartBooleanMenu();
               restartButton.showMenu(true);
                
                
                    System.out.println("window() while loop restart check executed");
                    try
                        {
                            gameThread.sleep(3000);
                        }
                        catch (InterruptedException ie)
                        {
                            ie.printStackTrace();
                        }
                      
                    if(restartButton.getButton()==2)
                    {   
                        //System.out.println("window() restart checked window should restart");
                        //System.out.println("Game is restarting");
                        //win=false;
                        //System.out.println("win set to " + win);
                        //restartButton.restart();
                        //restartButton.showMenu(false);
                        
                    }
                    if(restartButton.getButton()==1)
                    {   
                        System.out.println("window() restart checked window should close");
                        //System.exit(0);
                    }
                
            }
        }           
    }
    
    
    public void checkWinner()
    {   
        String winnerFound = null;
        
        
        for(int a = 0; a < 8; a++)
        {
            switch(a)
            {
                case 0:
                    if((xSet[0]==true)&&(xSet[1]==true)&&(xSet[2]==true))
                    {
                        winnerFound = "X is the Winner!!!";
                        win = true;
                    }
                    if((oSet[0]==true)&&(oSet[1]==true)&&(oSet[2]==true))
                    {
                        winnerFound = "O is the Winner!!!";
                        win = true;
                    }
                    
                    break;
                case 1:
                    if((xSet[3]==true)&&(xSet[4]==true)&&(xSet[5]==true))
                    {
                        winnerFound = "X is the Winner!!!";
                        win = true;
                    }
                    if((oSet[3]==true)&&(oSet[4]==true)&&(oSet[5]==true))
                    {
                        winnerFound = "O is the Winner!!!";
                        win = true;
                    }
                    break;
                case 2:
                    if((xSet[6]==true)&&(xSet[7]==true)&&(xSet[8]==true))
                    {
                        winnerFound = "X is the Winner!!!";
                        win = true;
                    }
                    if((oSet[6]==true)&&(oSet[7]==true)&&(oSet[8]==true))
                    {
                        winnerFound = "O is the Winner!!!";
                        win = true;
                    }
                    break;
                case 3:
                    if((xSet[0]==true)&&(xSet[3]==true)&&(xSet[6]==true))
                    {
                        winnerFound = "X is the Winner!!!";
                        win = true;
                    }
                    if((oSet[0]==true)&&(oSet[3]==true)&&(oSet[6]==true))
                    {
                        winnerFound = "O is the Winner!!!";
                        win = true;
                    }
                    break;
                case 4:
                    if((xSet[1]==true)&&(xSet[4]==true)&&(xSet[7]==true))
                    {
                        winnerFound = "X is the Winner!!!";
                        win = true;
                    }
                    if((oSet[1]==true)&&(oSet[4]==true)&&(oSet[7]==true))
                    {
                        winnerFound = "O is the Winner!!!";
                        win = true;
                    }
                    break;
                case 5:
                    if((xSet[2]==true)&&(xSet[5]==true)&&(xSet[8]==true))
                    {
                        winnerFound = "X is the Winner!!!";
                        win = true;
                    }
                    if((oSet[2]==true)&&(oSet[5]==true)&&(oSet[8]==true))
                    {
                        winnerFound = "O is the Winner!!!";
                        win = true;
                    }
                    break;
                case 6:
                    if((xSet[0]==true)&&(xSet[4]==true)&&(xSet[8]==true))
                    {
                        winnerFound = "X is the Winner!!!";
                        win = true;
                    }
                    if((oSet[0]==true)&&(oSet[4]==true)&&(oSet[8]==true))
                    {
                        winnerFound = "O is the Winner!!!";
                        win = true;
                    }
                    break;
                case 7:
                    if((xSet[2]==true)&&(xSet[4]==true)&&(xSet[6]==true))
                    {
                        winnerFound = "X is the Winner!!!";
                        win = true;
                    }
                    if((oSet[2]==true)&&(oSet[4]==true)&&(oSet[6]==true))
                    {
                        winnerFound = "O is the Winner!!!";
                        win = true;
                    }
                    break;
            }
            if(win==true)
            {
                System.out.println("Winner found "+ winnerFound);
                    //System.exit(0);
            }
        }
        System.out.println("checkWinner() executed.");
    }
    
    
    public void loadConstraints(JPanel[] pan, GridBagConstraints gbc, Container getCont)
    {
            int obj = 0;
            for(int col = 0; col < 3; col++)
            {
                for(int row = 1; row < 4; row++)
                {   
                    
                    gbc.gridx=col;
                    gbc.gridy=row;
                    gbc.fill= GridBagConstraints.BOTH;
                    gbc.weightx = 1.0;
                    gbc.weighty = 1.0;
                    gbc.insets = new Insets(5,5,5,5);
                    pan[obj].setPreferredSize(new Dimension(175,175));
                    getCont.add(pan[obj], gbc);
                    obj++;
                }
            }
        
    }
 
    public static void main(String[] args)
    {       
            
            for(int i = 0; i < 1; i++)
       {
           System.out.println("for loop started");
            GameRun game = new GameRun(); 
        
        while(win)
        {
            //System.out.println("if(win) restartMenu checked");
            //restartButton.RestartBooleanMenu();
            //restartButton.showMenu();
            System.out.println("GameRun() while loop check one");
            if(win)
            {                  
               if(win && (restartButton.getButton()==1))
               {
                System.exit(0);
               }
               System.out.println("if loop for no checked");
            }
            System.out.println("GameRun() while loop check two after if()");
            try
            {
                gameThread.sleep(1000);
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
            System.out.println("GameRun() while loop check two after if() pt2");
            if(restartButton.getButton() == 2)
            {
                System.out.println("Game is restarting");
                win=false;
                System.out.println("win set to " + win);
                i--;
                restartButton.restart();
                restartButton.showMenu(false);
                System.out.println("for loop decreased");
            }
        }
        
            System.out.println("for loop finished");
            System.out.println(i);
            try
            {
                gameThread.sleep(1500);
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
        }
    }    
}
