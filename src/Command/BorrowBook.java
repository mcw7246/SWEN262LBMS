package Command;

import java.util.List;

public class BorrowBook implements Command{

    private List<Integer> bookId;
    private Integer id;

    public BorrowBook(Integer visitorId, List<Integer> bookId){
        this.id = visitorId;
        this.bookId = bookId;
    }

    @Override
    public void execute() {

    }
}
