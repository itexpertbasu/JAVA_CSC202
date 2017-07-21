/*
 * SHRABANTI BASU
 * CSC 202
 * MARCH 25, 2017
 * PROJECT 4 
 *
 * THIS CLASS OPENS AND READS A FILE, SPLITS EVERY LINE OF THE FILE
 * AND SAVES THE MALE AND FEMALE NAMES IN TWO DIFFERENT ARRAY LIST.
 * 

 */
 

//import necessary classes for input output, Scanner, ArrayList
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


/**
 *
 * @author sb
 */
public class FileRead 
{
   //ArrayLists of string to store baby names from the files
   private ArrayList<String> maleBabyNames = new ArrayList<String>();
   private ArrayList<String> femaleBabyNames = new ArrayList<String>();
   
   File file;   //to store a file object
   Scanner inputFile;       //to store a scanner object
   
   
   /** 
    * The constructor takes as input the name of the file
    * and creates a new file object. The Scanner object reads
    * input from the file.
    * 
    * @param fileName name of file to open
    * @throws IOException 
    */
    public FileRead(String fileName) throws IOException
    {
        
      file = new File (fileName);
      inputFile = new Scanner(file);
        
    }

   /**
    * The fileReader method reads lines from the input file and
    * splits each line separated by any white space. The split tokens are
    * saved in an array. The second element of the array is the male name 
    * and the fourth element is the female name. 
    * We add those names from the arrays in two separate arraylists.
    * This process repeats till the entire file is read.
    * 
    * @throws IOException 
    */
    public void fileReader() throws IOException
    {
        //read contents of the file
        while (inputFile.hasNext())
        {
            //read a line from the file
            String line = inputFile.nextLine();

            //spilt each line read at white spaces and store them in an array
            //the elements of the arrays will be row number, male name, 
            //number of occurences, female names, number of occurences resp.
            String[] lineItems = line.split("\\s+");  

            //access and store the male and female names in separate arraylists
            maleBabyNames.add(lineItems[1].toLowerCase());
            femaleBabyNames.add(lineItems[3].toLowerCase());
            
        }  
        
    }
    
    /**
     * Returns the Arraylist of string containing male baby names
     * @return 
     */
     public ArrayList<String> getMaleBabyNames() 
    {
        return maleBabyNames;
    }
     
     
    /**
     * Returns the Arraylist of strings containing female baby names
     * @return 
     */ 
    public ArrayList<String> getFemaleBabyNames() 
    {
        return femaleBabyNames;
    }
    
    /**
     * Gets the index of the name from the male name array list.
     * The index plus 1 is the rank of the baby name for the year.
     * Returns the rank of the name
     * @param babyName
     * @return 
     */
    public int femaleRanking(String babyName)
    {
        int rank = getFemaleBabyNames().indexOf(babyName.toLowerCase());
        return rank + 1;
    }
    
    /**
     * Gets the index of the name from the female name array list
     * The index plus 1 is the rank of the baby name for the year
     * Returns the rank of the name
     * @param babyName
     * @return 
     */
    public int maleRanking(String babyName)
    {
        int rank = getMaleBabyNames().indexOf(babyName.toLowerCase());
        return rank + 1;
    }
    
    /** 
     * This method closes the input file
     */
    public void closeFile()
    {
        inputFile.close();
    }
    
    
 }
   
   
    
    

