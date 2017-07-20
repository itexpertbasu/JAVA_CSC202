/**
 * SHRABANTI BASU
 * CSC 202
 * CHAPTER 10 
 * PROJECT 1
 * THIS PROJECT DEMONSTRATES GUI COMPONENTS AND ACTION LISTENER.
 * THE USER IS ASKED TO ENTER THE LOAN AMOUNT, INTEREST RATE, NUMBER OF YEARS 
 * FOR PAYMENT AND THE PROGRAM CALCULATES AND DISPLAYS THE MONTHLY 
 * PAYMENTS TO BE MADE ON THE LOAN AMOUNT.
 */
 

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 *
 * @author sb
 */
public class CSC202Project1 extends JFrame
{
    //dimensions of frame 
    private static final int FRAME_WIDTH = 350;
    private static final int FRAME_HEIGHT = 250;
    
    //length of textarea
    private static final int FIELD_WIDTH = 10;
    
    //declare components as instance variables
    private JLabel label1, label2, label3, label4;
    private JTextField loanField, yearsField, rateField;
    private JTextField paymentField;
    private JButton button;
    
    //declare variables to hold user entered input and calculated payment amount
    private double loanAmount, interest, paymentAmount;
    private int years;
    
    /**
     * Constructor
     */
    
    public CSC202Project1()
    {
        super("Mortgage Calculator ");   //set title of the frame
        loanAmount = 0.0;
        interest = 0.0;
        paymentAmount = 0.0;
        years = 0;
        
        //helper methods to create labels, buttons and panels
        createLabel();
        createButton();
        createPanel();
        
        //call superclass method to set size of the frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        
    }
    /*
    * create labels
    */
    public void createLabel()
    {
        //create four labels
        label1 = new JLabel("Loan Amount           ");
        label2 = new JLabel("Years                        ");
        label3 = new JLabel("Annual Interest Rate");
        label4 = new JLabel("Payment                  ");  
    }
    
    /** 
     * create event listener that creates an instance of the Loan class and calls 
     * its monthlyPayment method to calculate the payment amount. Also checks for input 
     * validations and errors.
    */
    
    class AddCalculateListener implements ActionListener
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        loanAmount = Double.parseDouble(loanField.getText());
                        years = Integer.parseInt(yearsField.getText());                  
                        interest  = Double.parseDouble(rateField.getText());
                        
                        
                        
                        if (loanAmount < 0.0 || interest < 0.0 || years < 0)
                        {
                            JOptionPane.showMessageDialog(null, "Loan Amount, " +
                                    "interest rate, or years cannot be a " +
                                    "negative number.\nPlease enter valid numbers.", 
                                    "Warning", JOptionPane.WARNING_MESSAGE);
                            
                            //set the text fields to null string
                            loanField.setText("");
                            yearsField.setText("");
                            rateField.setText("");
                            paymentField.setText("");
                            
                            
                        }
                        else
                        {
                            Loan myLoan = new Loan(interest, years, loanAmount);
                            paymentAmount = myLoan.getMonthlyPayment();
                            DecimalFormat fmt = new DecimalFormat("0.00");
                            paymentField.setText(fmt.format(paymentAmount) + "");
                            
                        }
                        
                    }
                    catch(NumberFormatException ex)
                            {
                                JOptionPane.showMessageDialog(null, "Loan Amount, " +
                                    "interest rate, or years can only be valid numbers." +
                                    "\nPlease enter proper input for each field.", 
                                    "Error!", JOptionPane.ERROR_MESSAGE);
                                
                                loanField.setText("");
                                yearsField.setText("");
                                rateField.setText("");
                                paymentField.setText("");

                            }
                    
                }
            }
            
    
    /**
     * create buttons and register action listener to it
     * 
     */
    public void createButton()
    {
        button = new JButton("Calculate"); 
        ActionListener listener = new AddCalculateListener();
        button.addActionListener(listener);
    }
    
    /**
     * create panel and add components to it
    then add the panel to the frame
    */
    
    public void createPanel()
    {
        loanField = new JTextField(FIELD_WIDTH);
        yearsField = new JTextField(FIELD_WIDTH);
        rateField = new JTextField(FIELD_WIDTH);
        
        paymentField = new JTextField(FIELD_WIDTH);
        paymentField.setEditable(false);
        paymentField.setText("");
        
        
        JPanel panel = new JPanel();
        panel.add(label1);
        panel.add(loanField);
        
        panel.add(label2);
        panel.add(yearsField);
        
        panel.add(label3);
        panel.add(rateField);
        
        panel.add(label4);
        panel.add(paymentField);
        
        panel.add(button);
        
        add(panel);
        
    }
   
    
}
    

