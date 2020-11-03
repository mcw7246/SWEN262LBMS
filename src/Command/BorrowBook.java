package Command;

import Books.Book;
import State.Library;

import java.util.List;
import java.util.Calendar;
import Books.CheckOut;

public class BorrowBook implements Command
{
    private int visitorID;
    private List<Integer> bookIDs;
    private Library library;

    public BorrowBook(int visitorID, List<Integer> bookIDs, Library library){
        this.visitorID = visitorID;
        this.bookIDs = bookIDs;
        this.library = library;
    }


    @Override
    public void execute(){
        library.checkOutBooks(bookIDs, visitorID);
    }
}
