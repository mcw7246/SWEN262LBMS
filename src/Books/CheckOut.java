package Books;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

/**
 * Creates object of a checked out book
 *
 * @author Ryan Tytka - rdt7867
 */
public class CheckOut {

    private String idNum;
    private String title;
    private Book book;
    private Date dueDate;
    private Date checkOutDate;
    private int visitorID;

    /**
     * constructor for the CheckOut class
     * @param book the book that is being checked out
     * @param checkInDate the date that it is due
     * @param checkOutDate the date that it is checked out on
     * @param visitorID the visitor that is checking out the book
     */
    public CheckOut(Book book, Date checkInDate, Date checkOutDate, int visitorID){
        this.book = book;
        this.dueDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.visitorID = visitorID;;
        title = book.getTitle();
        idNum = "";
    }

    /**
     *
     * @return the ID number for the book
     */
    public String getIdNum(){return idNum;}
    public void setIdNum(String num){idNum = num;}
    public String getTitle(){
        return title;
    }

    /**
     *
     * @return the book that is connected to the CheckOut object
     */
    public Book getBook(){
        return book;
    }

    /**
     *
     * @return the due date that is connected to
     */
    public Date getDueDate(){
        return dueDate;
    }

    /**
     *
     * @return the date that is connected to the CheckOut object that the book was taken out of the library
     */
    public String  getCheckOutDate(){
        return new SimpleDateFormat("yyyy/MM/dd").format(checkOutDate.getTime());
    }

    /**
     *
     * @return the user ID of the user that checked out the book
     */
    public int getVisitorID(){
        return visitorID;
    }
}
