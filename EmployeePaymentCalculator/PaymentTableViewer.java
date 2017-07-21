/*
 *This program creates an instance of the PaymentTable class.
 */
 

import javax.swing.*;

/**
 *
 * @author sb
 */
public class PaymentTableViewer extends JFrame
{
    public static void main(String[] args)
    {
        JFrame frame = new PaymentTable();
        frame.setTitle("Payment Calculator Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
