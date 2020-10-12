package Command;

import Books.Book;
import Books.BookStore;
import State.Library;

import java.util.ArrayList;


public class BookSearch implements Command{
    Library library;
    String title;
    ArrayList<String> authors;
    String isbn = null;
    String publisher = null;
    String sortOrder = null;

    public BookSearch(Library library, String title, ArrayList<String> authors){
        this.library = library;
        this.title = title;
        this.authors = authors;
    }

    public BookSearch(Library library, String title, ArrayList<String> authors, String isbn){
        this.library = library;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
    }

    public BookSearch(Library library, String title, ArrayList<String> authors, String isbn, String publisher){
        this.library = library;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
    }

    public BookSearch(Library library, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder){
        this.library = library;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
        this.sortOrder = sortOrder;
    }

    @Override
    public void execute() {
        library.bookSearch(title,authors,isbn,publisher, sortOrder);
    }


}
