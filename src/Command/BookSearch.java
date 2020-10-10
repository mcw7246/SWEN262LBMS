package Command;

import java.util.ArrayList;

public class BookSearch implements Command{
    String title;
    ArrayList<String> authors;
    String isbn;
    String publisher;
    String sortOrder;

    public BookSearch(String title, ArrayList<String> authors){
        this.title = title;
        this.authors = authors;
    }

    public BookSearch(String title, ArrayList<String> authors, String isbn){
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
    }

    public BookSearch(String title, ArrayList<String> authors, String isbn, String publisher){
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
    }

    public BookSearch(String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder){
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
        this.sortOrder = sortOrder;
    }



    @Override
    public void execute() {

    }
}
