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

    public CheckOut(Book book, Date checkInDate, Date checkOutDate, int visitorID){
        this.book = book;
        this.dueDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.visitorID = visitorID;;
        title = book.getTitle();
        idNum = "";
    }

    public String getIdNum(){return idNum;}
    public void setIdNum(String num){idNum = num;}
    public String getTitle(){
        return title;
    }

    public Book getBook(){
        return book;
    }

    public Date getDueDate(){
        return dueDate;
    }

    public String  getCheckOutDate(){
        return new SimpleDateFormat("yyyy/MM/dd").format(checkOutDate.getTime());
    }

    public int getVisitorID(){
        return visitorID;
    }
}
