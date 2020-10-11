package Books;

import java.util.List;
import java.util.Calendar;

public class CheckOut {
    
    private List<Integer> bookISBNs;
    private Calendar checkInDate;
    private Calendar checkOutDate;
    private int visitorID;

    public CheckOut(List<Integer> books, Calendar checkInDate, Calendar checkOutDate, int visitorID){
        this.bookISBNs = books;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.visitorID = visitorID;
    }

    public List<Integer> getBookISBNs(){
        return bookISBNs;
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
