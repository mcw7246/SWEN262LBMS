package Books;

import java.util.List;
import java.util.Calendar;

/**
 * Creates object of a checked out book
 *
 * @author Ryan Tytka - rdt7867
 */
public class CheckOut {
    
    private Book book;
    private Calendar checkInDate;
    private Calendar checkOutDate;
    private int visitorID;

    public CheckOut(Book book, Calendar checkInDate, Calendar checkOutDate, int visitorID){
        this.book = book;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.visitorID = visitorID;
    }

    public Book getBook(){
        return book;
    }

    public Calendar getCheckInDate(){
        return checkInDate;
    }

    public Calendar getCheckOutDate(){
        return checkOutDate;
    }

    public int getVisitorID(){
        return visitorID;
    }
}
