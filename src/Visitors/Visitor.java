package Visitors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mikayla Wishart - mcw7246
 */
public class Visitor
{
  private int numUsers = 1;
  private String fName;
  private String lName;
  private String address;
  private String phoneNum;
  private String id;
  private List<Visitor> existingVisitors = new ArrayList<>();

  /**
   * constructor for creating a new visitor
   * @param fName
   * @param lName
   * @param address
   * @param phoneNum
   */
  public Visitor(String fName, String lName, String address, String phoneNum){
    this.fName = fName;
    this.lName = lName;
    this.address = address;
    this.phoneNum = phoneNum;
  }

  /**
   * Sets the visitor id to the next number available
   */
  public void setVisitorID(){
    //gets how many characters are in the number of users already registered
    int lengthOfNum = Integer.toString(numUsers).length();

    //finds how many zero's will need to be in the string
    int numZeros = 10-lengthOfNum;
    String visitorID = "";

    //loops through and adds the amount of zeros necessary to the beginning of the string
    for(int x = 0; x < numZeros; x++){
      visitorID += "0";
    }

    //adds the actual number that the user is
    visitorID += Integer.toString(numUsers);

    //sets the visitorID to the id
    this.id = visitorID;
    System.out.println(visitorID);
    System.out.println(this.id);
    numUsers++;
  }

  /**
   * get method for first name
   * @return first name
   */
  public String getfName(){
    return fName;
  }

  /**
   * get method for last name
   * @return last name
   */
  public String getlName(){
    return lName;
  }

  /**
   * get method for address
   * @return address
   */
  public String getAddress(){
    return address;
  }

  /**
   * get method for phone number
   * @return phone number
   */
  public String getPhoneNum(){
    return phoneNum;
  }

  /**
   * get method for visitor ID
   * @return visitorID
   */
  public String getId(){
    return id;
  }
}
