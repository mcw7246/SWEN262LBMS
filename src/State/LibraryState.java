package State;

import java.util.List;

public interface LibraryState {

    public void makeVisit(Integer id);
    public void borrowBook(Integer id, List<Integer> bookId);
}
