/*
 * This program demonstrates a bunny class.
 */
 

import javax.swing.*;

/**
 *
 * @author sb
 */
public class BunnyDemo extends JFrame
{
    public static void main(String[] args)
    {
        //show custom message to user about the purpose of program
        JOptionPane.showMessageDialog(null, "The program calculates total number ears for bunnies.\n" +
                                            "The bunnies in odd places have 2 ears and the bunnies\n" +
                                            "in even places have 5 ears.\n Click the OK button to proceed" +
                                            " and enter the number of bunnies in line.", "Info", JOptionPane.INFORMATION_MESSAGE);
        
        //create instance of bunny class
        JFrame frame = new Bunny();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bunny Ear Calculation Table");
        frame.setVisible(true);
        
    }
}
