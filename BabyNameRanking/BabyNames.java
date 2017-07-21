/*
 * SHRABANTI BASU
 * CSC 202
 * MARCH 25, 2017
 * PROJECT 4 
 * 
 * THIS PROGRAM SHOWS THE RANKING OF A BABY NAME FROM YEARS 2006-2010
 * THE USER SELECTS WHICH YEAR, GENDER OF THE CHILD, AND ENTERS NAME,
 * AND THE PROGRAM DISPLAYS WHAT RANKING THE NAME HAD FOR THAT YEAR.
 * IF THAT NAME DID NOT APPEAR THEN A PROPER MESSAGE IS DISPLAYED.
 *
 * THE FILES WE HAVE ARE ALREADY SORTED IN ORDER THE NAMES WERE USED FOR 
 * THAT YEAR. SO THE ROW NUMBER FOR THE ARRAY IS ITS RANK. WHEN WE SAVE THE 
 * NAMES IN AN ARRAYLIST THE INDEX OF THE ARRAY PLUS 1 IS THE RANK OF THE NAME AS 
 * IT APPEARS IN THE FILE ROW
 *
 */
 

//import necessary swing, awt, event, input-output, Scanner classes
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author sb
 */
public class BabyNames extends JFrame
{
    //frame width and height
    private static final int FRAME_WIDTH = 280;
    private static final int FRAME_HEIGHT = 200;
    
    //length of text areas
    private static final int NAME_FIELD_LENGTH = 10;
    private static final int RESULT_FIELD_LENGTH = 22;

    
    private JPanel panel, inputPanel, outputPanel;   //to store main panel, top and bottom panels
    private JButton button;                          //to hold button object
    private JComboBox yearBoxCombo, genderBoxCombo;  //to hold combo boxes for year and gender
    private JTextField nameField, resultField;       //to hold textfields for baby name and reusult
    
    private String year, gender, name = "";   //to store baby name, year, gender
    private String fileName;             //name of the file to choose from
    private int position;                //to store the position of male and female name arrays 
    /** 
     * The Constructor creates a panel with Border Layout and
     * calls helper methods for creating two panels namely inputPanel
     * and outputPanel. The main panel is added to the frame.
     * 
     */
    public BabyNames()
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
        
        //create label
        inputPanel.add(new JLabel("Select a year:"));  
        
        //call helper method to create JComboBox for year
        createYearBox();
        
        //create label
        inputPanel.add(new JLabel("Select a gender:"));   
        
        //call helper method to create JComboBox for gender
        createGenderBox();
        
        //create label and text field to enter name
        inputPanel.add(new JLabel("Enter a name: "));
        
        nameField = new JTextField(NAME_FIELD_LENGTH);
        inputPanel.add(nameField);
        
        //helper method to create button        
        createButton();
        
        //add panel to the center
        panel.add(inputPanel, BorderLayout.CENTER);     
        
                
    }
    
    
    
    /**
     * The createYearBox creates a JComboBox 
     * and adds String elements to it. The combo box 
     * is then added to the input panel.
     */
    public void createYearBox()
    {
        yearBoxCombo = new JComboBox();
        
        yearBoxCombo.addItem("2006");
        yearBoxCombo.addItem("2007");
        yearBoxCombo.addItem("2008");
        yearBoxCombo.addItem("2009");
        yearBoxCombo.addItem("2010");
        
        inputPanel.add(yearBoxCombo);
    }
    
    
     /**
     * The createGenderBox creates a JComboBox 
     * and adds String elements to it. The combo box 
     * is then added to the input panel.
     */
    public void createGenderBox()
    {
        genderBoxCombo = new JComboBox();
        
        genderBoxCombo.addItem("Male");
        genderBoxCombo.addItem("Female");
        
        
        inputPanel.add(genderBoxCombo);
    }
    
    /**
     * createOutputPanel creates a panel where output is displayed.
     * it is then added to the south of the main panel.
     */
    public void createOutputPanel()
    {
        outputPanel = new JPanel();
        resultField = new JTextField(RESULT_FIELD_LENGTH);
        resultField.setEditable(false);   //cannot be edit by user
        
        outputPanel.add(resultField);  
        panel.add(outputPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Class ButtonListener implements the ActionLister.
     * User input is collected and validated. A custom FileRead object is then
     * generated with the correct file and output is displayed.
     */
    class ButtonListener implements ActionListener 
    {
        
        public void actionPerformed(ActionEvent e)
        {
            //set the result text field to an empty string
           // resultField.setText("");
            
            //collect user input for year, gender and name
            year = (String) yearBoxCombo.getSelectedItem();
            gender = (String) genderBoxCombo.getSelectedItem();
            name = nameField.getText();
            
            //if names contains anything other than letters
            //display proper errormessage
            if (name.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid input.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                resultField.setText("");
            }
            
            else if (name.matches(".*\\d+.*") || name.matches("[^a-z]") )         
            {
               
                JOptionPane.showMessageDialog(null, "Valid name cannot contian " +
                                                     "numbers.\n" +
                                                     "Please enter a valid name.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                nameField.setText("");
                resultField.setText("");
            }
            
            else
            {
                //choose and open correct files for different years
                if (year == "2006")
                    fileName = "Babynamesranking2006.txt";
                else if (year == "2007")
                    fileName = "Babynamesranking2007.txt";
                else if (year == "2008")
                    fileName = "Babynamesranking2008.txt";
                else if (year == "2009")
                    fileName = "Babynamesranking2009.txt";
                else if (year == "2010")
                    fileName = "Babynamesranking2010.txt";
                
                try
                {
                   
                    
                    /**
                     * Create a FileRead object and read lines from the file
                     * with the fileReader method. The fileReader method
                     * reads each line from a file as a string, then splits the
                     * line into tokens and stores male and female names into 
                     * two different Arraylists 
                     * 
                     */
                    
                    FileRead fReader = new FileRead(fileName);
                    fReader.fileReader();
                    
                    
                    /** 
                     * If the name is a male name, we get the index of the 
                     * male name from the male Array list.
                     * Similar for the female names.                     * 
                     */
                                       

                    if (gender == "Male")
                        position = fReader.maleRanking(name);
                    else
                        position = fReader.femaleRanking(name);
                        
                    //Check if the rank is less than zero, then display message 
                    //that the name was not found that year
                    //else display the rank for the name.            
                    
                    if (position >= 1)
                    resultField.setText(name + " was ranked " + position +
                                        " in " + year);
                    else
                        resultField.setText(name + " was not found in " + year);
                    
                    //close the fileRead object after reading is done
                    fReader.closeFile();
                    
                }
                //dispaly error message if file cannot be opened properly
                catch(IOException err)
                {
                    JOptionPane.showMessageDialog(null, "Error opening file.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                                                
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
