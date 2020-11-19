package Command;

import Books.Book;
import State.Library;

import java.util.List;
import java.util.Calendar;
import Books.CheckOut;

/**
 * implements the Command interface for the borrow command
 * @author Mikayla Wishart - mcw724
 * @author Yug Patel - ydp4388
 */
public class BorrowBook implements Command
{
    private int visitorID;
    private List<Integer> bookIDs;
    private Library library;

    /**
     * constructor
     * @param visitorID visitor ID of the user trying to borrow a book
     * @param bookIDs book id's of the books the user is checking out
     * @param library library that the user is trying to check books out of
     */
    public BorrowBook(int visitorID, List<Integer> bookIDs, Library library){
        this.visitorID = visitorID;
        this.bookIDs = bookIDs;
        this.library = library;
    }

    /**
     * executes the method through the library class
     */
    @Override
    public void execute(){
        library.checkOutBooks(bookIDs, visitorID);
    }
}
