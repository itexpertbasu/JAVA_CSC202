/*
 * SHRABANTI BASU
`* CSC 202 PROJECT 3
 * FEB 18, 2017
 * 
 * This program demonstrates implementation of recurssion.
 * This program caluclates the number of bunnies standing in a line. 
 * The odd bunnies have 2 ears and the even bunnies have 5 ears.
 * The user enters the number of bunnies and the CountDaEars 
 * method calculates the numbe of ears.
 */
 

//import necessary libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Bunny extends JFrame
{
    //frame height and width
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 200;
    
    //length of text field area
    private static final int TEXTFIELD_LENGTH = 8;
  
    private JPanel panel;  //to hold main panel
    private JButton button;     //button

    //textfields for entering bunny numbers and displaying ears number
    private JTextField bunnyField, earsField;  
       
    private int bunnyNumbers;   //number of bunnies in the line
    private int earNumbers;    //number of total ears for all bunnies
    
    private static final int EVEN_EARS = 5; //ears for even bunnies
    private static final int ODD_EARS = 2;  //ears for odd bunnies
    
    
    /**
     * Constructor creates the panel and adds it to the frame
     */
    public Bunny()
    {
        //set frame width
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        
        //call helper method to create panel
        createPanel();
        
        add(panel);
    }
    
    
   /**
    * The CreatePanel method creates a panel and other components 
    * and adds them to the panel.
    * */
    public void createPanel()
    {
        //creates main panel and adds compoenents to it
        panel = new JPanel();
        bunnyField = new JTextField(TEXTFIELD_LENGTH);
        earsField = new JTextField(TEXTFIELD_LENGTH);
        earsField.setEditable(false);
        
        panel.add(new JLabel("Number of bunnies: "));
        
        panel.add(bunnyField);
        
        panel.add(new JLabel("Number of ears: "));
        
        panel.add(earsField);
        createButton();    //helper method to create button
    }
    
    /**
     * Create Event listener that calls countDaEars method to count the total 
     * number of ears for all bunnies. 
     * Also checks for input validations and errors.
     * 
     */
    class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
             try 
             {
                 //get number of bunnies
                 bunnyNumbers = Integer.parseInt(bunnyField.getText());
                 
                 //check for positive numbers
                 if (bunnyNumbers < 0)
                 {
                     earsField.setText("");
                     JOptionPane.showMessageDialog(null, "Please enter a positive integer for\n" +
                                                "number of bunnies.", "Error", JOptionPane.ERROR_MESSAGE);
                     
                     
                 }
                 else
                 {
                    //count number of ears and display 
                    earNumbers = countDaEars(bunnyNumbers);
                   
                    earsField.setText("" + earNumbers);
                     
                 }
                            
             }
             catch(NumberFormatException err)
             {
                 //if a proper number is not entered user gets warning
                  earsField.setText("");
                  JOptionPane.showMessageDialog(null, "Please enter a valid integer for\n" +
                                                "number of bunnies.", "Error", JOptionPane.ERROR_MESSAGE);
                  
             }
        }
    }
     
    /** The createButton method creates a button, registers an 
     *  action listener and adds the button to the panel.
     * 
     */
    private void createButton() 
    {
        //create button
        button = new JButton("Calculate Ears");
        
        //add action listener
        ActionListener listener = new ButtonListener();
        button.addActionListener(listener);
        
        //add button to the panel
        panel.add(button);
        }
    
        /**
        * CountDaEars method takes the number of bunnies as argument
        * and calculates total number of ears for the bunnies. 
        * It uses recursion to calculate the ears for the number of bunnies and checks
        * if the position of a bunny in the line is even or odd.
        * 
        */
        public int countDaEars(int n)
        {
            int ears;  //local variable to store number of ears
            
            //Base case, if number of bunnies is zero the recurssion stops
            if (n == 0) 
            {
                ears = 0;
            }
            
            //when n is even
            else if ((n%2) == 0)
            {
                //recurssive call
                ears = EVEN_EARS + countDaEars(n-1);        
            }
            //when n is odd
            else 
            {
                //recurssive call
                ears = ODD_EARS + countDaEars(n-1);
            }
            return ears; 
        }
}




    
  

