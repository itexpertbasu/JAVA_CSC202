/*
* SHRABANTI BASU
* FINAL EXAM
* MAY 2, 2017
* THIS CLASS CREATES A STUDENT DATABASE AND ADDS ACTION LISTENERS TO IT.
* IT ALLOWS USERS TO ENTER THEIR ID, NAME, ADDRESS AND SAVES IT TO A HASHMAP.
* IT ALLOWS USERS TO SEARCH FOR A STUDENT WITH STUDENT ID AND DISPLAY STUDENT INFO.
* IT ALSO DISPLAYS ERROR MESSAGES WHEN NEEDED.
*/

//import necessary classes
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author sb
 */

public class StudentTable extends JFrame
{
    //frame width and height
    private static final int FRAME_WIDTH = 350;
    private static final int FRAME_HEIGHT = 500;
    
    //length of text fields
    private static final int TEXT_FIELD_LENGTH = 15;
    
    //to hold main panel and top, center, bottom, right panels
    private JPanel panel, centerPanel, bottomPanel; 
    
    //text fields for id, name, address, city, state, zip
    private JTextField idField, nameField, addressField, cityField, stateField, zipField;
    
    //academic year
    private JComboBox yearBox;

    //to hold button object
    private JButton saveButton, searchButton, newButton;   
    
    private int studentID = 0; //student id
    //student name, street address, city, state, zip, year
    private String name, streetAddr, city, state, zip, year = "";
    
    //Student object 
    Student aStudent;
    
    //Map object to hold each student info
     Map<Integer, Student> studentInfo = new HashMap<Integer, Student>();
     
     int studentKey;  //to hold student key when needed
     boolean foundKey = false;  //holds boolean based on student key found or not
;   

    /** Constructor creates frame, calls helper
     * methods to create top panel and bottom panel.
     * Add the main panel to the frame
     */
     
    public StudentTable()
    {
        //set size of the frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        
        //create a panel for the frame and set a Border Layout
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
                       
        //creates the output panel where the table is displayed
        createCenterPanel();
        
        //creates the bottom panel where total is shown
        createBottomPanel();
           
        //add the main panel to the frame
        add(panel);
                   
    }
    
    /**
     * Creates a panel where user enter input and adds it to 
     * the center of the main panel
     */
     public void createCenterPanel()
     {
         //create center panel
        centerPanel = new JPanel();
        
        //create proper labels and text fields for fields
        //needed for the center panel
        centerPanel.add(new JLabel("Student ID      "));
        
        idField = new JTextField(TEXT_FIELD_LENGTH);
        centerPanel.add(idField);
        
        centerPanel.add(new JLabel("Name             "));
        
        nameField = new JTextField(TEXT_FIELD_LENGTH);
        centerPanel.add(nameField);
        
        centerPanel.add(new JLabel("Street Address"));
        
        addressField = new JTextField(TEXT_FIELD_LENGTH);
        centerPanel.add(addressField);
        
        centerPanel.add(new JLabel("City           "));
        
        cityField = new JTextField(TEXT_FIELD_LENGTH);
        centerPanel.add(cityField);
        
        centerPanel.add(new JLabel("State            "));
        stateField = new JTextField(TEXT_FIELD_LENGTH);
        centerPanel.add(stateField);
        
        centerPanel.add(new JLabel("Zip Code       "));
        
        zipField = new JTextField(TEXT_FIELD_LENGTH);
        centerPanel.add(zipField);
        
        centerPanel.add(new JLabel("Year          "));
        
        //call helper method to create JComboBox for gender
        createYearBox();
        
        panel.add(centerPanel, BorderLayout.CENTER); 
     }
     
     /**
     * The createYearBox creates a JComboBox 
     * and adds academic year String elements to it. The combo box 
     * is then added to the center panel.
     */
    public void createYearBox()
    {
        yearBox = new JComboBox();
        
        yearBox.addItem("Freshman");
        yearBox.addItem("Sophomore");
        yearBox.addItem("Junior");
        yearBox.addItem("Senior");
        
        centerPanel.add(yearBox);
    }
     
     /**
      * The createBottom creates a bottom panel.
      * Calls helper methods to create buttons
      * 
      */
     
     public void createBottomPanel()
     {
         bottomPanel = new JPanel();
         
         //helper methods to create buttons   
        createSaveButton();
        createSearchButton();
        createNewButton();
        
        //add bottom panel to the south
        panel.add(bottomPanel, BorderLayout.SOUTH);  
     
     }
     
     /**
     * Class ButtonListener implements the ActionLister.
     * User input is collected and validated and necessary action taken
     * depending on which button is clicked
     */
     class ButtonListener implements ActionListener
    {
        
        public void actionPerformed(ActionEvent e)
        {
             
            //SELECT WHICH BUTTON IS SELECTED AND TAKE
            //NECESSARY ACTIONS
            if (e.getSource() == newButton)
            {
                //clear fields and make fields ready for user input
                clearFields();

                idField.setEditable(true);
                nameField.setEditable(true);
                addressField.setEditable(true);
                cityField.setEditable(true);
                stateField.setEditable(true);
                zipField.setEditable(true);
               
                
            }
            
            else if (e.getSource() == searchButton)
            {
                //search a student with matching id
                foundKey = false;
                try
                {
                    //get and convert values
                    studentID = Integer.parseInt(idField.getText());
                    
                    
                    Set<Integer> keySet = studentInfo.keySet();
                    
                    if (studentID < 0) //student id cannot be negative
                    {
                        //display error message and clear fields
                            JOptionPane.showMessageDialog(null, "Student ID "
                                + "must be a positive integer.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                            
                            clearFields(); 
                    }
                    else
                    {
            

                        //iterate through the set and look for student 
                        //with matching id. IF matching is found get that
                        //student's information and display
                        
                        for (Integer key : keySet)
                        {
                            
                            //Student oneStudent = studentInfo.get(key);
                            //if (studentID == oneStudent.getID())
                            if (key == studentID)   
                            
                            {
                                studentKey = key;
                                foundKey = true;
                            }
                            
                         }
                        if (foundKey)
                        {
                               Student oneStudent = studentInfo.get(studentKey); 
                                //display values and make fields non editable
                                idField.setText("" + oneStudent.getID());
                                nameField.setText(oneStudent.getName());
                                addressField.setText(oneStudent.getStreet());
                                cityField.setText(oneStudent.getCity());
                                stateField.setText(oneStudent.getState());
                                zipField.setText(oneStudent.getZip());
                                
                                //get academic year and show in combo box
                                String yr = oneStudent.getacademicYear();
                                if (yr.equalsIgnoreCase("Junior"))
                                    yearBox.setSelectedItem("Junior");
                                else if (yr.equalsIgnoreCase("Senior"))
                                    yearBox.setSelectedItem("Senior");
                                else if (yr.equalsIgnoreCase("Sophomore"))
                                    yearBox.setSelectedItem("Sophomore");
                                else if (yr.equalsIgnoreCase("Freshman"))
                                    yearBox.setSelectedItem("Freshman");
                                
                                //set text fields to non editable                            
                                nameField.setEditable(false);
                                addressField.setEditable(false);
                                cityField.setEditable(false);
                                stateField.setEditable(false);
                                zipField.setEditable(false);
                                
                                
                        }     
                           
                            
                            else
                            {
                                //display error message and clear fields
                                JOptionPane.showMessageDialog(null, "Student ID "
                                + "does not exist",
                                "Error", JOptionPane.ERROR_MESSAGE);
                            
                                clearFields();   
                                
                                //set text fields to non editable                            
                                nameField.setEditable(false);
                                addressField.setEditable(false);
                                cityField.setEditable(false);
                                stateField.setEditable(false);
                                zipField.setEditable(false);
                        
                            }   
                        
                        }
                    

                }
                catch(NumberFormatException err)
                {
                    //catch exception
                    JOptionPane.showMessageDialog(null, "Student ID must be "
                            + "a positive integer.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
            
            else if (e.getSource() == saveButton)
            {
                //get input and convret to appropriate format and save
                try
                {
                    studentID = Integer.parseInt(idField.getText());
                    name = nameField.getText();
                    streetAddr = addressField.getText();
                    city = cityField.getText();
                    state = stateField.getText();
                    zip = zipField.getText();
                    year = (String) yearBox.getSelectedItem();
                    
                    //student id cannot be negative
                     if (studentID < 0)
                    {
                        //display error message and clear fields
                            JOptionPane.showMessageDialog(null, "Student ID "
                                + "must be a positive integer.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                            
                            clearFields(); 
                    }
                    //check names are not empty
                     
                     else if (streetAddr.isEmpty() || 
                            name.isEmpty() || state.isEmpty() || zip.isEmpty())
                     {
                            JOptionPane.showMessageDialog(null, 
                                                "Please enter a valid input.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                      

                             clearFields();
                        
                     }
                    
                     else if (name.matches(".*\\d+.*") || name.matches("[^a-z]")
                             || state.matches(".*\\d+.*") || state.matches("[^a-z]")
                             ||city.matches(".*\\d+.*") || city.matches("[^a-z]"))
                     {
                         
                        JOptionPane.showMessageDialog(null, "Name, city, state " +
                                 "cannot contain numbers.\n" +
                                               "Please enter a valid name.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                        clearFields();
                     }
                    
                    else
                     {
                            //create and save student object in hashmap
                            aStudent = new Student(studentID, name, 
                                        streetAddr, city, state, zip, year);
                            studentInfo.put(studentID, aStudent);                               
                            
                            
                            

                     }
                         
                     
         
                    }
                
                catch (NumberFormatException err)
                {
                    //display error message
                    JOptionPane.showMessageDialog(null, "Student ID must be "
                            + "a positive integer.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
               
                
                 }
            }
            
        }
     }
     
     /**
     * The createSaveButton method creates a button
     * and adds it to the bottom panel and registers an
     * action listener to it
     */
     public void createSaveButton()
    {
        saveButton = new JButton("Save");
        
        ActionListener listener = new ButtonListener();
        saveButton.addActionListener(listener);
        
        bottomPanel.add(saveButton);
        
    }
     
      /**
     * The createSearchButton method creates a button
     * and adds it to the bottom panel and registers an
     * action listener to it
     */
    public void createSearchButton()
    {
        searchButton = new JButton("Search");
        
        ActionListener listener = new ButtonListener();
        searchButton.addActionListener(listener);
        
        bottomPanel.add(searchButton);
        
    }
    
    /**
     * The createNewButton method creates a button
     * and adds it to the bottom panel and registers an
     * action listener to it
     */
    public void createNewButton()
    {
        newButton = new JButton("New");
        
        ActionListener listener = new ButtonListener();
        newButton.addActionListener(listener);
        
        bottomPanel.add(newButton);
        
    }
    
    /**
     * clears all fields
     */
    public void clearFields()
    {
        idField.setText("");
        nameField.setText("");
        addressField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipField.setText("");
        
        
    }
    
                        
     
    
    
}
