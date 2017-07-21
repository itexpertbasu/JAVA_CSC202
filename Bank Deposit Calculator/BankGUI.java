/*
 * SHRABANTI BASU
 * CSC 202 
 * APRIL 22, 2017
 * PROJECT 5
 * 
 * THIS PROGRAM CREATES A GUI FOR THE USER TO ENTER INPUT FOR 
 * BANK ACCOUNT NUMBER AND DEPOSIT AMOUNT. 
 * THEN IT SHOWS THE USER THE TOTAL BALANCE FOR THE ACCOUNT.
 * THIS CLASS USES BANK ACCOUNT AND BANK DATA OBJECTS.
 * USER INPUT IS ENCRYPTED BEFORE WRITING TO A FILE
 * AND DECRYPRED FROM WHEN READ FROM A FILE.
 */

//import necessary swing, awt, event, input-output, classes
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sb
 */
public class BankGUI extends JFrame
{
     //frame width and height
    private static final int FRAME_WIDTH = 350;
    private static final int FRAME_HEIGHT = 200;
    
    //length of text areas
    private static final int INPUT_FIELD_LENGTH = 10;
    private static final int RESULT_FIELD_LENGTH = 20;

    private JPanel panel, inputPanel, outputPanel;   //to store main panel, top and bottom panels
    private JButton depositButton, clearButton, exitButton; //to hold button objects
    
     //to hold textfields for account numbers, deposit amount, balance
    private JTextField accNumField, depositField, balanceField; 
    
    //variables for account number, deposit, balance and encrypted counterparts
    private int accountNum = 0;
    private double depositAmount = 0.0;
    private double balanceAmount = 0.0;
    
    private int accountNumEncr = 0;
    private double depositAmountEncr = 0.0;
    private double balanceAmountEncr = 0.0;
    
    private String fileName;             //name of the file
    
    BankAccount account;  //BankAccount object
    
    BankData data;  //BankData object
    
    public static final int KEY = 3;    //Key used to encrypt and decrypt data
    
    
    /** 
     * The Constructor creates a panel with Border Layout and
     * calls helper methods for creating two panels namely inputPanel
     * and outputPanel. The main panel is added to the frame.
     * 
     */
    public BankGUI()
    {
        //set size of the frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        
        //create a panel for the frame and set a Border Layout
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
                
        //creates the input panel where user enters input
        createInputPanel();
       
        //creates the output panel where the result is displayed
        createOutputPanel();
          
        //add the main panel to the frame
        add(panel);
                   
    }
    
    /**
     * creates a panel where user enters input and adds it to 
     * the center of the main panel
     */
    public void createInputPanel()
    {
        inputPanel = new JPanel();
        
        //create label and text field for account number
        inputPanel.add(new JLabel("Enter account number:"));  
        
        accNumField = new JTextField(INPUT_FIELD_LENGTH);
        
        inputPanel.add(accNumField);

        
        //create label and text field for deposit number
        inputPanel.add(new JLabel("Enter deposit amount:"));   
               
        depositField = new JTextField(INPUT_FIELD_LENGTH);
        
        inputPanel.add(depositField);
        
        //helper methods to create buttons   
        createDepositButton();
        createClearButton();
        createExitButton();
        
        //add panel to the center
        panel.add(inputPanel, BorderLayout.CENTER);     
                       
    }
    
    
    /**
     * createOutputPanel creates a panel where output is displayed.
     * it is then added to the south of the main panel.
     */
    public void createOutputPanel()
    {
        outputPanel = new JPanel();
        
        //create label and text field for account number
        outputPanel.add(new JLabel("Balance: $ "));  
        
        balanceField = new JTextField(RESULT_FIELD_LENGTH);
        balanceField.setEditable(false);   //cannot be edit by user
        
        outputPanel.add(balanceField);  
        panel.add(outputPanel, BorderLayout.SOUTH);
    }

    
     /**
     * Class ButtonListener implements the ActionLister.
     * User input is collected and validated. 
     */
    
     class ButtonListener implements ActionListener 
    {
        
        public void actionPerformed(ActionEvent e)
        {
            //SELECT WHICH BUTTON IS SELECTED
            if (e.getSource() == clearButton)
            {
                //clear fields if clear button is clicked
                accNumField.setText("");
                depositField.setText("");
                balanceField.setText("");
                
                
            }
            else if (e.getSource() == exitButton)
            {
                //close the program if exit button clicked
                System.exit(0);
            }
            
            else if (e.getSource() == depositButton)   //if deposit button clicked
            {
                //convert data
                try
                {
                     //collect user input and convert to numerical values
                     accountNum = Integer.parseInt(accNumField.getText()); 
                     depositAmount = Double.parseDouble(depositField.getText());
                     
                     
                     //show error message if account number negative
                    if (accountNum < 0)
                    {
                        JOptionPane.showMessageDialog(null, "Please enter valid input.\n"+
                        "Account number must be a positive integer.\n",                       
                        "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        //create bank data object
                        data = new BankData();
                         
                        //formatter for currency formatting
                        DecimalFormat currencyfmt = new DecimalFormat("#,##0.00");
                        
                        //encrypt input with helper methods and store them in variables
                        accountNumEncr = encryptAccount(accountNum);
                        depositAmountEncr = encryptBalance(depositAmount);
                        
                        try
                        {  
                                //open a file
                                data.open("bank1.dat");
                                    
                                //find position of file pointer
                               int position = data.find(accountNumEncr);
                                    
                                //if account found, add balance to existing account
                                //use encrypted values when writing/saving to the file
                                    if (position >= 0)
                                    {
                                        account = data.read(position);
                                        
                                        //deposit encryped number
                                        account.deposit(depositAmountEncr);
                                        
                                        //decrypted value is shown in balance field
                                        balanceField.setText(""+ 
                                                currencyfmt.format(decryptBalance(account.getBalance())));
                                    }
                                    else // Add account if account does not exist
                                    {  
                                        //create new bank account with encrypted values of account number and deposit amount
                                         account = new BankAccount(accountNumEncr, depositAmountEncr);
                                         position = data.size();
                                         
                                         //decrypted value is shown in balance field
                                         balanceField.setText("" + 
                                                 currencyfmt.format(decryptBalance(account.getBalance())));
                                    }
                                    data.write(position, account);

                        }
                        //error message if file does not open correctly
                        catch (IOException err)
                        {
                            JOptionPane.showMessageDialog(null, "Error Opening file.\n",
                        "Error",JOptionPane.ERROR_MESSAGE);
                        } 
      finally
      {                     //close file and catch error if needed
                            try 
                            {
                                data.close();
                            } 
                            catch (IOException ex)
                            {
                                Logger.getLogger(BankGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
      }
                         
                     }
                    
                    
                }
                
                //display proper error message if user does not enter valid input
                catch (NumberFormatException err)
                {
                    JOptionPane.showMessageDialog(null, "Please enter valid input.\n"+
                        "Account number and deposit amount must be valid numbers.\n",                       
                        "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            
        }
            
     }
    
     
     /**
     * The createDepositButton method creates a button
     * and adds it to the input panel and registers an
     * action listener to it
     */
    public void createDepositButton()
    {
        depositButton = new JButton("Deposit");
        
        ActionListener listener = new ButtonListener();
        depositButton.addActionListener(listener);
        
        inputPanel.add(depositButton);
        
    }
    
    
    /**
     * The createExitButton method creates a button
     * and adds it to the input panel and registers an
     * action listener to it
     */
    public void createExitButton()
    {
        exitButton = new JButton("Exit");
        
        ActionListener listener = new ButtonListener();
        exitButton.addActionListener(listener);
        
        inputPanel.add(exitButton);
        
    }
    
     /**
     * The createClearButton method creates a button
     * and adds it to the input panel and registers an
     * action listener to it
     */
    public void createClearButton()
    {
        clearButton = new JButton("Clear");
        
        ActionListener listener = new ButtonListener();
        clearButton.addActionListener(listener);
        
        inputPanel.add(clearButton);
        
    }
    
    
    /**
     * The encryptAccount method takes an integer, multiplies it with the 
     * encryption key and returns the encrypted value
     */
    public int encryptAccount(int a)
    {
        return a * KEY;
    }
    
     /**
     * The encryptBalance method takes a double, multiplies it with the 
     * encryption key and returns the encrypted value
     */
    public double encryptBalance(double bal)
    {
        return bal * KEY;
    }
    
   
    /**
     * The decryptAccount method takes an integer, divides it by the 
     * encryption key and returns the decrypted value
     */
    public int decryptAccount(int a)
    {
        return a / KEY;
    }
    
 /**
     * The decryptBalance method takes a double, divides it by the 
     * encryption key and returns the decrypted value
     */
    public double decryptBalance(double bal)
    {
        return bal / KEY;
    }
    
    
    
    
}
