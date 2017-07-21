/*
 * SHRABANTI BASU
 * CSC 202
 * MARCH 25, 2017
 *
 * This program creates an instance of the BabyNames class.
 */
 

import javax.swing.*;

/**
 *
 * @author sb
 */
public class NameViewer extends JFrame
{
    public static void main(String[] args)
    {
        JFrame frame = new BabyNames();
        frame.setTitle("Baby Names Ranking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setVisible(true);
        
    
        
    }
}
