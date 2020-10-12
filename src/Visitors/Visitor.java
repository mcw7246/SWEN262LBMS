package Visitors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mikayla Wishart - mcw7246
 */
public class Visitor
{
  private String fName;
  private String lName;
  private String address;
  private String phoneNum;
  private int id;
  private boolean inVisit;


  /**
   * constructor for creating a new visitor
   * @param fName first name
   * @param lName last name
   * @param address address
   * @param phoneNum phone number
   */
  public Visitor(String fName, String lName, String address, String phoneNum){
    this.fName = fName;
    this.lName = lName;
    this.address = address;
    this.phoneNum = phoneNum;
    this.inVisit = false;
  }

  /**
   * get method for first name
   * @return first name
   */
  public String getfName(){
    return this.fName;
  }

  /**
   * get method for last name
   * @return last name
   */
  public String getlName(){
    return this.lName;
  }

  /**
   * get method for address
   * @return address
   */
  public String getAddress(){
    return this.address;
  }

  /**
   * get method for phone number
   * @return phone number
   */
  public String getPhoneNum(){
    return this.phoneNum;
  }

  /**
   * get method for visitor ID
   * @return visitorID
   */
  public Integer getId(){
    return this.id;
  }

  /**
   * set method for visitor ID
   * @param id visitorID
   */
  public void setId(int id){
    this.id = id;
  }

  /**
   * Method to assign state - In Visit.
   */
  public void setInVisit(){
    inVisit = true;
  }

  /**
   * Method to assign state - End Visit.
   */
  public void setEndVisit(){
    inVisit = false;
  }

  /**
   * Method to get a boolean if state - In Visit.
   */
  public boolean isVisit(){
    return inVisit;
  }

  /**
   * Create a string for visitors
   * @return a string that contains visitor's information
   */
  @Override
  public String toString(){
    return "First Name: " + this.fName + "\nLast Name: " + this.lName + "\nAddress: " + this.address + "\nPhone Number: "
            + this.phoneNum + "\nID: " + this.id + "\nIs In Library: " + this.inVisit;
  }
}
