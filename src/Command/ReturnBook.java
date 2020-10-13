package Command;

import java.util.List;
import State.Library;

public class ReturnBook implements Command{

    private List<String> bookId;
    private Integer id;
    private Library library;

    public ReturnBook(Library library, Integer visitorId, List<String> bookId){
        this.library = library;
        this.id = visitorId;
        this.bookId = bookId;
    }

    @Override
    public void execute() {
        library.returnBooks(id);
    }
}
