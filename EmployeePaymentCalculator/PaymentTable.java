/*
 * SHRABANTI BASU
 * CSC 202
 * MIDTERM
 * MARCH 31, 2017
 * THIS PROGRAM LETS USER ENTER EMPLOYEE NUMBER, BASE PAY, HOURS WORKED
 * AND CALCULATES THE PAYMENT AMOUNT FOR EACH EMPLOYEE, TOTAL AMOUNT FOR ALL
 * EMPLOYEES. THE RADIO BUTTON CALCULATES THE DIFFERENT AMOUNT OF RAISES OR
 * RESETS EVERYTHING. THE 1%, 2%, OR 5% RAISE IS CALCULATED OVER THE CURRENT 
 * PAYMENT AMOUNT CALCULATED (NOT CALCULATED OVER PREVIOUS RAISE VALUES).
 * THE TOTAL VALUE IS SUM OF PAYMENT AMOUNT FOR ALL EMPLYEES.
 * 
 */
 

//import necessary classes
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 *
 * @author sb
 */
public class PaymentTable extends JFrame
{
    //frame width and height
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 300;
    
    //length of text fields
    private static final int TEXT_FIELD_LENGTH = 8;
    private static final int RESULT_FIELD_LENGTH = 20;

    
    //number of rows and columns for text area
    private static final int COLS = 50;
    private static final int ROWS = 16;
    
     //increment amount for interest rate
    private static final double INCREMENT1 = 0.01;
    private static final double INCREMENT2 = 0.02;
    private static final double INCREMENT5 = 0.05;
    
    //constant values for min, max, overtime hours, mim base pay
    private static final double MIN_HOURS = 4.0;
    private static final double MAX_HOURS = 60.0;
    private static final double OT_HOURS = 40.0;
    private static final double MIN_WAGE = 8.25;
    
    
    //to hold main panel and top, center, bottom, right panels
    private JPanel panel, topPanel, centerPanel, bottomPanel, rightPanel; 
    
    private JButton addButton;         //to hold button object                    
    private JTextField empNumField, basePayField, hoursField, 
                       amountField, totalField;     //to hold textfield objects
    private JScrollPane scrollPane;                 //scrollpane
    private JTextArea area;                         //to store text area
    
    private int empNum;     //employee number
    
    //radio buttons to store selection for increase pay by 1, 2, 5% or reset
    private JRadioButton increase1Radio, increase2Radio, increase5Radio, resetRadio;
    
    //to store buttongroup object to reference radio buttons
    private ButtonGroup bg; 
    
    //base pay of an employee, hours, payment amount for one employee, 
    //total payment for all employees
    private double basePay, hours, amountPay, totalPay;
    
    private double pctRaise;  //percent by which raise is calculated
    
    //Payroll object used to calculate regular and overtime payments
    Payroll myPayroll;
    
    //formatter for hours
    DecimalFormat numberfmt = new DecimalFormat("0.00");
    //formatter for currency
    DecimalFormat currencyfmt = new DecimalFormat("#,##0.00");
    
    //create new array list to hold each employee payment
    ArrayList<Double> payList = new ArrayList();
    
    //create new array to hold employee number, basepay, hourslist
    ArrayList<Integer> empList = new ArrayList();
    ArrayList<Double> basePayList = new ArrayList();
    ArrayList<Double> hoursList = new ArrayList();
    
    
    
    
    /**
     * The Constructor creates a panel with Border Layout and
     * calls helper methods for creating two panels namely inputPanel
     * and outputPanel. The main panel is added to the frame.
     */
    public PaymentTable()
    {
        //set size of the frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        
        //create a panel for the frame and set a Border Layout
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
                
        //creates the top panel where user enters input
        createTopPanel();
       
        //creates the output panel where the table is displayed
        createCenterPanel();
        
        //creates the bottom panel where total is shown
        createBottomPanel();
       
        //creates the output panel where radio buttons are displayed
        createRightPanel();
        
        //add the main panel to the frame
        add(panel);
                   
    }
     
    /**
     * Creates a panel where user enter input and adds it to 
     * the north of the main panel
     */
     public void createTopPanel()
     {
        topPanel = new JPanel();
        
        topPanel.add(new JLabel("Employee Number "));
        
        empNumField = new JTextField(TEXT_FIELD_LENGTH);
        topPanel.add(empNumField);
        
        topPanel.add(new JLabel("Base Pay "));
        
        basePayField = new JTextField(TEXT_FIELD_LENGTH);
        topPanel.add(basePayField);
        
        topPanel.add(new JLabel("Hours Worked "));
        
        hoursField = new JTextField(TEXT_FIELD_LENGTH);
        topPanel.add(hoursField);
        
        //helper method to create button to add components       
        createAddButton();
        
        panel.add(topPanel, BorderLayout.NORTH); 
     }
     
     /**
     * creates a panel where user table is displayed and adds it to 
     * the center of the main panel
     */
     public void createCenterPanel()
     {
        centerPanel = new JPanel();
         
        area = new JTextArea(ROWS, COLS);
        area.setEditable(false);
        
        //helper method that creates a header for output area in center panel
        setHeader();
        
        //add scroll bars to the text area and add scrollpane to center panel
        scrollPane = new JScrollPane(area);      
        centerPanel.add(scrollPane);
        
        panel.add(centerPanel, BorderLayout.CENTER);
     }
     
     /**
     * creates a panel where user total is shown and adds it to 
     * the south of the main panel
     */
     public void createBottomPanel()
     {
        bottomPanel = new JPanel();
        
        bottomPanel.add(new JLabel("Total pay for all employees: $ ")); 
        
        totalField = new JTextField(RESULT_FIELD_LENGTH);
        totalField.setEditable(false);
        bottomPanel.add(totalField);
        
        panel.add(bottomPanel, BorderLayout.SOUTH);
     }
     
     /**
     * creates a panel where radio buttons are displayed and adds it to 
     * the right of the main panel
     */
     public void createRightPanel()
     {
         //create panel and add a Grid Layout with 4 rows, 1 column
         rightPanel = new JPanel();
         rightPanel.setLayout(new GridLayout(4, 1));
         
         //helper method to create radiobuttons
         createRadioButton();
           
         //add border around panel
         rightPanel.setBorder(BorderFactory.createTitledBorder("Raises"));
         
         //add right panel to the east of the main panel
         panel.add(rightPanel, BorderLayout.EAST);
         
     }
     
     /**
     * Class ButtonListener implements the ActionLister.
     * The user entered input is validated. If necessary, error message is displayed.
     * Else all the fields are populated and results are shown in center and bottom panels
     * 
     */
     
     class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                //convert user entered String to numerical values
                empNum = Integer.parseInt(empNumField.getText());
                basePay = Double.parseDouble(basePayField.getText());
                hours = Double.parseDouble(hoursField.getText());
                
                empList.add(empNum);
                basePayList.add(basePay);
                hoursList.add(hours);
                
                //check for minimum and maximum values
                if (empNum <= 0 || basePay < MIN_WAGE || hours < MIN_HOURS 
                        || hours > MAX_HOURS)
                {
                    JOptionPane.showMessageDialog(null, "Employee number is" +
                                                "a positive integer.\n" +
                            "Base pay cannot be less than $" + MIN_WAGE +                       
                            "\nHours must be between" + MIN_HOURS + " and " 
                            + MAX_HOURS + "hours.",
                                            "Error",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    //create payroll object with user entered values
                    myPayroll = new Payroll(basePay, hours);
                    
                    //if hours less than 40 calculate regular pay
                    //else calculate overtime pay
                    if (hours <= OT_HOURS)
                        amountPay = myPayroll.regularPay(hours, basePay);
                    else
                        amountPay = myPayroll.overtimePay(hours, basePay);
                    
                    //total pay is the total of all amountPay
                    totalPay += amountPay;
                    
                    //add the amountPay to the arraylist payList and 
                    //display those values in the output area
                    payList.add(amountPay);
                             
                    
                    //append output to the text area
                    area.append("" + empNum + "\t\t");
                    area.append("$" + currencyfmt.format(basePay) + "\t");
                    area.append(numberfmt.format(hours) + "\t\t");
                    area.append("$" + currencyfmt.format(amountPay)+ "\n");
                    
                    //display total amount of payment for all payment amounts
                    totalField.setText("" + currencyfmt.format(totalPay));
                        
                }
                        
            }
            //display error message if input cannot be processed
            catch(NumberFormatException err)
            {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers.\n",
                        "Error",JOptionPane.ERROR_MESSAGE);
            }
            
        }
     }
     
     /**
     * The createButton button method creates a button
     * and adds it to the top panel and registers an
     * action listener to it
     */
     public void createAddButton()
     {
         addButton = new JButton("Add");
        
        ActionListener listener = new ButtonListener();
        addButton.addActionListener(listener);
        
        topPanel.add(addButton);
     }
     
     /**
      * Class RadioButtonListener implements the ActionLister.
      * Depending on user selection it calculates raises for payment amount
      * or resets all fields
      * 
      */
     private class RadioButtonListener implements ActionListener
     {
         public void actionPerformed(ActionEvent e)
         {
             //if reset button is selected clear all fields and arraylist
             //set total to zero
             //draw headers for text area again
             if (e.getSource() == resetRadio)
             {
                 empNumField.setText("");
                 hoursField.setText("");
                 basePayField.setText("");  
                 totalField.setText("");
                 
                 payList = new ArrayList();
                 totalPay = 0.0;
                 
                 area.setText("");
                 
                 setHeader();
                 

             }
             else
             {
                //set pctRaise based on user selection of radio button
                 if (e.getSource() == increase1Radio)
                     pctRaise = INCREMENT1;
                 else if (e.getSource() == increase2Radio)
                     pctRaise = INCREMENT2;
                 else if (e.getSource() == increase5Radio)
                     pctRaise = INCREMENT5;
                 
                 //set total to zero
                 totalPay = 0.0;
                 
                 //create a new array list to store the values for all raises
                 ArrayList<Double> raiseList = new ArrayList();
                 
                 //calculate raises for all elements  of payList
                 //and store them in the raiseList
                 //clear the text area, draw header and add new values again
                 
                 for (int i = 0; i < payList.size(); i++)
                 {
                    double temp = payList.get(i) * (1 + pctRaise);
                    raiseList.add(temp);
                 }
                 area.setText("");
                 setHeader();
                 
                 int SIZE = raiseList.size();
                 
                 for (int j = 0; j < SIZE; j++)
                 {
                     area.append("" + empList.get(j) + "\t\t");
                     area.append("$" + currencyfmt.format(basePayList.get(j)) + "\t");
                     area.append(numberfmt.format(hoursList.get(j)) + "\t\t");
                     area.append("$" + currencyfmt.format(raiseList.get(j))+ "\n");
                     //totalPay += payList.get(j);
                     
                     totalPay += raiseList.get(j);
                   
                     totalField.setText("" + currencyfmt.format(totalPay));
                 }
                 

                 
             }
         }
     }
     
     
     /**
     * The createRadioButton method creates radio buttons
     * and adds it to the panel and registers an
     * radiobutton listener to it
     */
     public void createRadioButton()
     {
         //create Radio buttons
         increase1Radio = new JRadioButton("1%");
         increase2Radio = new JRadioButton("2%");
         increase5Radio = new JRadioButton("5%");
         resetRadio = new JRadioButton("Reset");
         
         
         bg = new ButtonGroup();
         //group radio buttons
         bg.add(increase1Radio);
         bg.add(increase2Radio);
         bg.add(increase5Radio);
         bg.add(resetRadio);
         
         //add action listener to radio buttons
         increase1Radio.addActionListener(new RadioButtonListener());
         increase2Radio.addActionListener(new RadioButtonListener());
         increase5Radio.addActionListener(new RadioButtonListener());
         resetRadio.addActionListener(new RadioButtonListener());
         
         
         //add radio buttons to the panel
         rightPanel.add(increase1Radio);
         rightPanel.add(increase2Radio);
         rightPanel.add(increase5Radio);
         rightPanel.add(resetRadio);
     }
     
     /**
      * setHeader method creates a header for text area in the center panel
      */
     public void setHeader()
     {
        area.setText("Employee Number \t");
        area.append("Base Pay         \t");
        area.append("Hours Worked     \t");
        area.append("Pay Amt \n");
     }


}
    
    
        

    
    

