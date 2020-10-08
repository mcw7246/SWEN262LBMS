package State;

import java.util.List;

public class Closed implements LibraryState{

    Library library;

    public Closed(){

    }

    @Override
    public void makeVisit(Integer id) {

    }

    @Override
    public void borrowBook(Integer id, List<Integer> bookId) {

    }
}
