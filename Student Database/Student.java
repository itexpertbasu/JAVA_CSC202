/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * PROVIDED BY INSTRUCTOR
 */
 

public class Student
{
  private int    ID;
  private String name;
  private String street;
  private String city;
  private String state;
  private String zip; 
  private String academicYear;

  public Student(int ID, String name, String street, String city,
                 String state, String zip, String academicYear)
  {

    this.ID = ID;
    this.name = name;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.academicYear = academicYear;
  }

  public int getID() 
  {
    return ID;
  }

  public String getName() 
  {
    return name;
  }

  public String getStreet() 
  {
    return street;
  }

  public String getCity() 
  {
    return city;
  }

  public String getState() 
  {
    return state;
  }

  public String getZip() 
  {
    return zip;
  }

  public String getacademicYear () 
  {
    return academicYear;
  }

  public void setID(int ID) 
  {
    this.ID = ID;
  }

  public void setName(String name) 
  {
    this.name = name;
  }

  public void setStreet(String street) 
  {
    this.street = street;
  }

  public void setCity(String city) 
  {
    this.city = city;
  }

  public void setState(String state) 
  {
    this.state = state;
  }

  public void setZip(String zip) 
  {
    this.zip = zip;
  }

  public void setAcademicYear (String academicYear) 
  {
    this.academicYear = academicYear;
  }
}

