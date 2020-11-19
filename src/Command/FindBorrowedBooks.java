package Command;

import java.util.List;


import State.Library;
import java.util.Calendar;

/**
 * implements the Command interface for the borrowed command
 * @author Mikayla Wishart - mcw7246
 * @author Yug Patel - ydp4388
 * @author Bryan Wang - jw3513
 */
public class FindBorrowedBooks implements Command{

    private Integer visitorID;
    private Library library;


    /**
     * Constructor
     * @param visitorID visitor ID to check what they have checked out of the library
     * @param library the library that the user is checking
     */
    public FindBorrowedBooks(Integer visitorID, Library library){
        this.visitorID = visitorID;
        this.library = library;
    }

    @Override
    public void execute() {
        library.findBorrowedBooks(visitorID);
    }
}
