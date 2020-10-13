package Books;

import java.util.List;
import java.util.Calendar;

/**
 * Creates object of a checked out book
 *
 * @author Ryan Tytka - rdt7867
 */
public class CheckOut {
    
    private List<Book> books;
    private Calendar checkInDate;
    private Calendar checkOutDate;
    private int visitorID;

    public CheckOut(List<Book> books, Calendar checkInDate, Calendar checkOutDate, int visitorID){
        this.books = books;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.visitorID = visitorID;
    }

    public List<Book> getBooks(){
        return books;
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

    /**
     * Sets the check out date date of the checkout.
     * @return true if there are still books checkedout
     */
    public boolean returnBook(Calendar checkOutDate, Book book)
    {
        this.checkOutDate = checkOutDate;
        books.remove(book);
        return books.size() == 0;
    }
}
