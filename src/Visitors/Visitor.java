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
  private long id;

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
  public long getId(){
    return id;
  }

  /**
   * set method for visitor ID
   * @param id visitorID
   */
  public void setId(long id){
    this.id = id;
  }
}
