package State;

import Books.Book;
import State.LibraryState;
import State.Open;

import java.util.ArrayList;

public class Library {

    LibraryState open;
    LibraryState closed;

    LibraryState libraryState;

    private ArrayList<Book> books;

    public Library(){
        open = new Open();
        books = new ArrayList<>();
    }

    public ArrayList<Book> getBooks(){
        return books;
    }

}
