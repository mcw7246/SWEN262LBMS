package Command;

import java.util.List;

public class BorrowBook implements Command{

    private List<Integer> bookId;
    private Integer visitorID;

    public BorrowBook(Integer visitorID, List<Integer> bookId){
        this.visitorID = visitorID;
        this.bookId = bookId;
    }

    @Override
    public void execute() {

    }
}
