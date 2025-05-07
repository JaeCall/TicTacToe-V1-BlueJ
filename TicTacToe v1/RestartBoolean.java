import javax.swing.JFrame;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class RestartBoolean extends JFrame
{
    private FlowLayout layout;
    private JPanel panel;
    private JButton yesButton;
    private JButton noButton;
    private int restart;
    
    public void RestartBooleanMenu(){ 
        layout = new FlowLayout();
        panel = new JPanel();
        yesButton = new JButton("YES");
        noButton = new JButton("NO");
        
        panel.add(yesButton);
        panel.add(noButton);
        
        noButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    setButton(1);
                    System.out.println("no button pressed");
                }
            }
        );
        yesButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    setButton(2);
                    System.out.println("yes button pressed");
                }
            }
        );
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        getContentPane().setLayout(layout);
        getContentPane().setBackground(new Color(221,0,201));
        setSize(525,600);
        setVisible(true);                     
        setLocationRelativeTo(null);
        System.out.println("RestartBooleanMenu() called");
    }
    public int getButton()
    {
        System.out.println("getButton() called");
        System.out.println("getButton() value " + restart);
        return restart;
        
    }
    public void setButton(int restart)
    {
        
        this.restart = restart;
        System.out.println("setButton() called and set");
        System.out.println("setButton() value " + restart);
    }
    public void showMenu(boolean bool)
    {
        setVisible(bool);
    }
    public void restart()
    {
        this.restart = 0;
    }
}