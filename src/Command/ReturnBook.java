package Command;

import java.util.List;

public class ReturnBook implements Command{

    private List<String> bookId;
    private Integer id;

    public ReturnBook(Integer visitorId, List<String> bookId){
        this.id = visitorId;
        this.bookId = bookId;
    }

    @Override
    public void execute() {

    }
}
