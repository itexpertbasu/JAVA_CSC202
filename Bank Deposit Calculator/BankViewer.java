/*
 * SHRABANTI BASU
 * CSC 202
 * APRIL 22, 2017
 * PROJECT 5
 * This class creates a Bank GUI Object.
 */
 

import javax.swing.JFrame;

/**
 *
 * @author sb
 */
public class BankViewer extends JFrame
{
    
    public static void main(String[] args)
    {
        JFrame frame = new BankGUI();
        frame.setTitle("Bank Account Info");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setVisible(true);
        
    }
    
    
    
}
