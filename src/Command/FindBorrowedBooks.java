package Command;

import java.util.List;


import State.Library;
import java.util.Calendar;


public class FindBorrowedBooks implements Command{

    private Integer visitorID;
    private Library library;


    public FindBorrowedBooks(Integer visitorID, Library library){
        this.visitorID = visitorID;
        this.library = library;
    }

    @Override
    public void execute() {
        library.findBorrowedBooks(visitorID);
    }
}
