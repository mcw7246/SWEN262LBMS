package Command;

import Books.Book;
import State.Library;

import java.util.List;
import java.util.Calendar;
import Books.CheckOut;

public class BorrowedBooks implements Command
{
    private int visitorID;
    private List<Book> bookIDs;
    private Library library;
    private Calendar checkInDate;
    private Calendar checkOutDate;

    public BorrowedBooks(int visitorID, List<Book> bookIDs, Library library, Calendar checkInDate, Calendar checkOutDate){
        this.visitorID = visitorID;
        this.bookIDs = bookIDs;
        this.library = library;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }


    @Override
    public void execute(){
        library.checkOutBooks(bookIDs, checkInDate, checkOutDate, visitorID);
    }
}
