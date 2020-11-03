package Visitors;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Books.Book;
import Books.CheckOut;
import com.sun.javafx.scene.CameraHelper;

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
  private ArrayList<CheckOut> checkOuts;
  private ArrayList<PaidFine> paidFines;
  private ArrayList<UnpaidFine> unpaidFines;
  private int balance;


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
    checkOuts = new ArrayList<>();
    paidFines = new ArrayList<>();
    unpaidFines = new ArrayList<>();
    balance = 0;
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


  public List<Book> getCheckedOutBooks() {
      List<Book> books = new ArrayList<>();
      for(CheckOut checkOut: checkOuts){
          books.add(checkOut.getBook());
      }
      return books;
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


  public void setCheckOuts(List<CheckOut> checkOuts){
      this.checkOuts.addAll(checkOuts);
  }

  /**
   * Visitor returning books
   * @return a string that contains visitor's information
   */
  public double returnBooks(Book book) {
        double Fines = 0.0;
        for(CheckOut checkOut: checkOuts){
            if(checkOut.getBook() == book){
                Fines = calculateFine(checkOut);
                checkOuts.remove(checkOut);
                break;
            }
        }
        return Fines;
  }


    /**
     * Calculates the fines applied to a returned book transaction. $10 is added to the fine for 1 day late, and $2 is
     * added for each additional week late. A fine cannot exceed $30.
     *
     * @param checkout - A Checkout object used to calculate fines.
     * @return An integer representing the amount charged to the visitor.
     */
    private int calculateFine(CheckOut checkout)
    {
        int fineAmount = 0;
        int returnDate = checkout.getCheckInDate().get(Calendar.DAY_OF_YEAR);
        int dueDate = checkout.getCheckOutDate().get(Calendar.DAY_OF_YEAR);

        int days = returnDate - dueDate;

        if (days >= 1)
        {
            fineAmount = Integer.min(10 + (2 * (days / 7)), 30);
        }

        return fineAmount;
    }

     /**
     * Pays a given amount toward the visitor's fine balance.
     *
     * @param amount - The amount to pay toward fines.
     */
    public void payFine(double amount, Calendar datePaid)
    {
        this.balance -= amount;
        this.paidFines.add(new PaidFine(amount, datePaid));
    }

      /**
     * Simple getter for retrieving the visitor's balance.
     *
     * @return The visitor's balance.
     */
    public int getBalance()
    {
        return this.balance;
    }

    public boolean isMaxCheckOut(Integer num){
        return num + checkOuts.size() <= 5;
    }
}
