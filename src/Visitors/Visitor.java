package Visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import Books.Book;
import Books.CheckOut;

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
  private ArrayList<Fine> fines;


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
    fines = new ArrayList<>();
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
     * Gets a list of currently checked out books.
     *
     * @return An ArrayList of currently checked out books.
     */
    public ArrayList<Book> getCheckedOutBooks()
    {
        ArrayList<Book> books = new ArrayList<>();

        for (CheckOut checkout: this.checkOuts)
        {
            books.addAll(checkout.getBooks());
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

  /**
   * Visitor returning books
   * @return a string that contains visitor's information
   */
  public double returnBooks(ArrayList<Book> books, Calendar dateReturned)
    {
        double totalFines = 0.0;

        for (Book book: books)
        {
            // get all checked out books for this visitor
            ArrayList<Book> checkedOut = this.getCheckedOutBooks();

            // Confirm visitor has checked out book
            if (!checkedOut.contains(book))
            {
                continue;
            }

            CheckOut checkout = this.checkOuts.get(checkedOut.indexOf(book));
            checkout.returnBook(dateReturned);

            // Calculate any fines applied to this book.
            int fineAmount = calculateFine(checkout);

            // Create fine object if necessary
            if (fineAmount > 0)
            {
                this.fines.add(new Fine(fineAmount, dateReturned));
                //this.balance += fineAmount;

                // Add to total fines applied
                totalFines += fineAmount;
            }

            // Put the copy back in the library
            book.returnCopies(1);


            // Remove the checkout
            this.checkOuts.remove(checkout);
        }

        return totalFines;
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
}
