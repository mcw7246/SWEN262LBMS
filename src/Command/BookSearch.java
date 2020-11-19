package Command;

import State.Library;

import java.util.ArrayList;


/**
 * implements the Command interface for the BookSearch command
 * @author Mikayla Wishart - mcw7246
 */
public class BookSearch implements Command{
    Library library;
    String title;
    ArrayList<String> authors;
    String isbn = "*";
    String publisher = "*";
    String sortOrder = "*";

    /**
     * constructor
     * @param library library that the user is searching in
     * @param title title of the book that the user is looking for
     * @param authors authors of the book
     */
    public BookSearch(Library library, String title, ArrayList<String> authors){
        this.library = library;
        this.title = title;
        this.authors = authors;
    }

    /**
     * constructor
     * @param library library that the user is searching in
     * @param title title of the book
     * @param authors authors of the book
     * @param isbn isbn of the book
     */
    public BookSearch(Library library, String title, ArrayList<String> authors, String isbn){
        this.library = library;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
    }

    /**
     * constructor
     * @param library library that the user is searching in
     * @param title title of the book that the user is searching for
     * @param authors authors of the book that the user is searching for
     * @param isbn isbn of the book
     * @param publisher publisher of the book
     */
    public BookSearch(Library library, String title, ArrayList<String> authors, String isbn, String publisher){
        this.library = library;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
    }

    /**
     * constructor
     * @param library the library that the user is searching in
     * @param title the title of the book that the user is looking for
     * @param authors the authors of the book that the user is looking for
     * @param isbn isbn of the book that the user is looking for
     * @param publisher publisher of the book that the user is looking for
     * @param sortOrder sort order of the results
     */
    public BookSearch(Library library, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder){
        this.library = library;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
        this.sortOrder = sortOrder;
    }

    /**
     *
     */
    @Override
    public void execute() {
        library.bookSearch(title,authors,isbn,publisher, sortOrder);
    }


}
