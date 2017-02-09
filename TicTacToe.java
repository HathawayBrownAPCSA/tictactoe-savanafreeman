import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font; 
import java.awt.Color; 
import javax.swing.*;

public class TicTacToe extends JPanel
{
    JButton buttons[] [] = new JButton[3] [3]; 
    int alternate = 0;//if this number is a even, then put a X. If it's odd, then put an O
    
    public TicTacToe()
    {
      setLayout(new GridLayout(3,3));
      initializebuttons(); 
    }
    
    public void initializebuttons()
    {
        for(int r = 0; r < 3; r++)
        {
          for (int c = 0; c < 3; c++)
          {
            buttons[r] [c]= new JButton();
            buttons[r] [c].setText("");
            buttons[r] [c].addActionListener(new buttonListener());
            buttons[r] [c].setFont(new Font ("Arial", Font.BOLD, 70)); 
            
            add(buttons[r] [c]); //adds this button to JPanel (note: no need for JPanel.add(...)
                                //because this whole class is a JPanel already     
          }
        }
    }
    
    public void resetButtons()
    {
       for(int r = 0; r < 3; r++)
        {
          for (int c = 0; c < 3; c++)
         {
            buttons[r] [c].setText("");
         }
       }
    }
       
       
    
    
// when a button is clicked, it generates an ActionEvent. Thus, each button needs an ActionListener. When it is clicked, it goes to this listener class that I have created and goes to the actionPerformed method. There (and in this class), we decide what we want to do.
    private class buttonListener implements ActionListener
    {
       
       public void actionPerformed(ActionEvent e) 
       {

           JButton buttonClicked = (JButton)e.getSource(); //get the particular button that was clicked
           if(buttonClicked.getText().length() > 0)
           {
             JOptionPane.showMessageDialog(null, "You can't do that!");
           }
           else if(alternate%2 == 0)
           {
               buttonClicked.setText("X");
               buttonClicked.setForeground(Color.ORANGE);
               alternate++;
           }
           else
           {
               buttonClicked.setText("O");
               buttonClicked.setForeground(Color.PINK);
               alternate++;
           }

           /*if(checkForWin() == "")
           {
               JOptionPane.showMessageDialog(null, "Do you want to play again?");
               resetButtons();
           }*/

           String winner = checkForWin();
           if (winner.length() > 0)
           {
             if(winner.equals("T"))
             {
               JOptionPane.showMessageDialog(null, "It's a tie game!");
               resetButtons();
             }
             else if  (winner.equals("X") || winner.equals("O"))
             {
               JOptionPane.showMessageDialog(null, winner + " is the winner! Game over.");
               resetButtons();
             }
             


       }
       }
        
        public String checkForWin()
        {
            /**   Reference: the button array is arranged like this as the board
             *      0 | 1 | 2
             *      3 | 4 | 5
             *      6 | 7 | 8
             */
        
          //horizantalwin check
          if (checkThree (buttons[0][0].getText(), 
                          buttons[1][0].getText(), 
                          buttons[2][0].getText()))
            return buttons[0][0].getText(); 
          else if (checkThree (buttons[0][1].getText(),
                               buttons[1][1].getText(),
                               buttons[2][2].getText()))
            return buttons[0][0].getText();
         else if (checkThree (buttons[0] [2].getText(), 
                          buttons[1][2].getText(), 
                          buttons[2][2].getText()))
            return buttons[0][0].getText();
        
            //vertical win check
            if (checkThree (buttons[0] [0].getText(),    // row 0
                          buttons[0][1].getText(), 
                          buttons[0][2].getText()))
            return buttons[0][0].getText(); 
          else if (checkThree (buttons[1] [0].getText(),     //row 1
                          buttons[1][1].getText(), 
                          buttons[1][2].getText()))
            return buttons[0][0].getText(); 
          else if (checkThree (buttons[2] [0].getText(), 
                          buttons[2][1].getText(), 
                          buttons[2][2].getText()))
            return buttons[0][0].getText(); 
            
            //diagonal win check
         if (checkThree (buttons[0] [0].getText(), 
                          buttons[1][1].getText(), 
                          buttons[2][2].getText()))
            return buttons[0][0].getText(); 
          
        else if (checkThree (buttons[2][0].getText(),
                               buttons[1][1].getText(),
                               buttons[0][2].getText()))
            return buttons[2][0].getText();

            
            //if not a win, check for tie 

      if (alternate == 0)
        return "T";
      
      //No win, no tie: Keep going!
      return "";
     
        }
        
        public boolean checkThree (String s1, String s2, String s3)
        {
            if ( s1.equals(s2) && s1.equals(s3) && (s1.length() > 0))
                return true;
            else
                return false;
        }
        
    }
    
    public static void main(String[] args) 
    {
        JFrame window = new JFrame("Tic-Tac-Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new TicTacToe());
        window.setBounds(300,200,300,300);
        window.setVisible(true);
    }
}
