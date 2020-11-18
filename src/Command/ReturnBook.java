package Command;

import java.util.List;
import State.Library;

/**
 * implements the command interface for the return command
 *
 * @author Yug Patel - ydp4388
 */
public class ReturnBook implements Command{

    private List<Integer> bookId;
    private Integer id;
    private Library library;

    /**
     * constructor
     * @param library library that the book is being returned to
     * @param visitorId visitor ID of the user that is returning the book
     * @param bookId book id's of the books being returned
     */
    public ReturnBook(Library library, Integer visitorId, List<Integer> bookId){
        this.library = library;
        this.id = visitorId;
        this.bookId = bookId;
    }

    /**
     * executes the return command through the library class
     */
    @Override
    public void execute() {
        library.returnBooks(id, bookId);
    }
}
