package Command;

import java.util.List;


import State.Library;
import java.util.Calendar;


public class BorrowBook implements Command{

    private List<Integer> bookId;
    private Integer visitorID;
    private Library library;
    private int qty;
    private List<Integer> bookIDs;
    private Calendar timeBorrow; //the time borrowed the book


    public BorrowBook(Integer visitorID, List<Integer> bookId, Library library, Calendar timeBorrow){
        this.visitorID = visitorID;
        this.bookId = bookId;
        this.library = library;
        this.timeBorrow = timeBorrow;

    }

    @Override
    public void execute() {
        library.borrowBooks(qty, bookIDs);
    }
}
