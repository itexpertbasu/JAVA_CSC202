/*
 * SHRABANTI BASU
 * CSC 202
 * CHAPTER 11 
 * PROJECT 2
 * THIS PROJECT DEMONSTRATES ADVANCED GUI COMPONENTS AND ACTION LISTENER.
 * THE USER IS ASKED TO ENTER THE LOAN AMOUNT, NUMBER OF YEARS 
 * AND THE PROGRAM CALCULATES MONTHLY AND TOTAL 
 * PAYMENTS STARTING WITH INTEREST RATE OF 5% UPTO 8% WITH THE INCREMENT OF 0.125.
 */
 


//import necessary classes
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.text.DecimalFormat;



public class LoanTable extends JFrame 
{
    //frame width and height
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 300;
    
    //length of text areas
    private static final int LOAN_FIELD_LENGTH = 8;
    private static final int YEARS_FIELD_LENGTH = 4;
    
    //maximum, minimum interest rates
    private static final double MIN_INTEREST = 5.0;
    private static final double MAX_INTEREST = 8.0;
    
    //increment amount for interest rate
    private static final double INCREMENT = 0.125;
    
    //number of rows and columns for text area
    private static final int COLS = 13;
    private static final int ROWS = 16;
    
    
    private JPanel panel, inputPanel, outputPanel; //to hold main panel, top and bottom panels
    private JButton button;                        //to hold the button object
    private JTextField loanField, yearsField;      //to hold textfield objects
    private JScrollPane scrollPane;                //scrollpane
    private JTextArea area;                        //to hold text area
    
    //to hold loan amount, total payment and monthly payment
    private double loanAmount, totalPayment, monthlyPayment; 
    
    private int years;          //number of years
    private double interest;    //to hold interest rate
    private Loan myLoan;        //to hold a Loan object
    
    /**
     * The Constructor creates a panel with Border Layout and
     * calls helper methods for creating two panels namely inputPanel
     * and outputPanel. The main panel is added to the frame.
     */
    public LoanTable()
    {
        //set size of the frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        
        //create a panel for the frame and set a Border Layout
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
                
       //creates the input panel where user enters input
       createInputPanel();
       
        //creates the output panel where the table is displayed
        createOutputPanel();
          
        //add the main panel to the frame
        add(panel);
                   
    }
    
    /**
     * creates a panel where user enter input and adds it to 
     * the north of the main panel
     */
    
    public void createInputPanel()
    {
        inputPanel = new JPanel();
        
        inputPanel.add(new JLabel("Loan Amount "));
        
        loanField = new JTextField(LOAN_FIELD_LENGTH);
        inputPanel.add(loanField);
        
        inputPanel.add(new JLabel("Number of years "));
        
        yearsField = new JTextField(YEARS_FIELD_LENGTH);
        inputPanel.add(yearsField);
        
        //helper method to create button        
        createButton();
        
        panel.add(inputPanel, BorderLayout.NORTH);       
    }
    
    
    /**
     * createOutputPanel method creates a panel where 
     * the loan table is displayed. User cannot edit this area.
     * This output panel is added to the main panel
     */
    
    public void createOutputPanel()
    {   
        area = new JTextArea(ROWS, COLS);
        area.setEditable(false);
        
        //add scroll bars
        scrollPane = new JScrollPane(area);      
        panel.add(scrollPane, BorderLayout.CENTER);
        
    }
    
    /**
     * Class ButtonListener implements the ActionLister.
     * The user entered input is validated. If necessary, error message is displayed.
     * Else a loan object is generated and monthly and total payments are calculated and displayed
     * from r.o.i 5% to 8% with an increment of 0.125.
     * 
     */
    class ButtonListener implements ActionListener
    {
        
        public void actionPerformed(ActionEvent e)
        {
            
            try
            {
                //convert user entered String to numerical values
                loanAmount = Double.parseDouble(loanField.getText());
                years = Integer.parseInt(yearsField.getText());
                        
                //validate the range of input for loan amount and  years
                if (loanAmount < 1000 || loanAmount > 100000 || years < 1 || years > 20)
                {
                    JOptionPane.showMessageDialog(null, "Loan Amount has to be between " +
                                    "$1,000 and $100,000 and years has to be within " +
                                    "1 and 20.", 
                                    "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                     interest = MIN_INTEREST;
                     //cleanup existing table
                     //then add values for r.o.i range using a for loop
                     
                        for (int i = 1; i <= area.getRows(); i++) 
                        {
                            area.setText("");
                        }
                        
                        area.setText("Interest Rate \t");
                        area.append("Monthly Payment \t");
                        area.append("Total Payment\n");
                    
                    
                    for(double interest = MIN_INTEREST; interest <= MAX_INTEREST; interest+= INCREMENT)
                    {
                        myLoan = new Loan(interest, years, loanAmount);
                
                        monthlyPayment = myLoan.getMonthlyPayment();
                        totalPayment = myLoan.getTotalPayment();
                        
                        //formatter for interest rate
                        DecimalFormat numberfmt = new DecimalFormat("0.000");
                        //formatter for currency
                        DecimalFormat currencyfmt = new DecimalFormat("#,##0.00");
                        
                        //append output to the text area
                        area.append(numberfmt.format(interest) + "\t");
                        area.append("$" + currencyfmt.format(monthlyPayment) + "\t\t");
                        area.append("$" + currencyfmt.format(totalPayment)+ "\n");
                    
                    }

                 }
              
            }
            //display proper error message if user does not enter valid input
            catch (NumberFormatException err)
            {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers.\n"+
                        "Loan amount can be between $1,000 and $100,000.\n" +
                        "Years can be between 1 and 20.\n", 
                        "Error",JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }
               
    /**
     * The createButton button method creates a button
     * and adds it to the input panel and registers an
     * action listener to it
     */
    public void createButton()
    {
        button = new JButton("Show Table");
        
        ActionListener listener = new ButtonListener();
        button.addActionListener(listener);
        
        inputPanel.add(button);
        
    }
    
    
}


