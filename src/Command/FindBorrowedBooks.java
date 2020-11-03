package Command;

import java.util.List;


import State.Library;
import java.util.Calendar;


public class FindBorrowedBooks implements Command{

    private List<Integer> bookId;
    private Integer visitorID;
    private Library library;


    public FindBorrowedBooks(Integer visitorID, List<Integer> bookId, Library library){
        this.visitorID = visitorID;
        this.bookId = bookId;
        this.library = library;
    }

    @Override
    public void execute() {
        library.findBorrowedBooks(visitorID, bookId);
    }
}
