package State;

import java.util.List;

public interface LibraryState {

    public void startVisit(Integer visitorId);
    public void endVisit(Integer visitorId);
    public void borrowBook(Integer visitorId, List<Integer> bookId);
}
