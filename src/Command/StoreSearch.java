package Command;

import Books.Book;
import Books.BookStore;

import java.util.ArrayList;
import java.util.List;

/**
 * implements the command interface for the storesearch command
 */
public class StoreSearch implements Command
{
  String title;
  List<String> authors = null;
  String isbn = "*";
  String publisher = "*";
  String sortOrder = "*";
  BookStore bookStore;

  /**
   * constructor
   * @param bookStore book store that is being searched
   * @param title title of the book being searched
   * @param authors authors of the book
   * @param isbn isbn of the book
   * @param publisher publisher of the book being searched
   * @param sortOrder sort order of the books
   */
  public StoreSearch(BookStore bookStore, String title, List<String> authors, String isbn, String publisher, String sortOrder){
    this.bookStore = bookStore;
    this.title = title;
    this.authors = authors;
    this.isbn = isbn;
    this.publisher = publisher;
    this.sortOrder = sortOrder;
  }

  /**
   * executes the search command through the bookstore class
   */
  @Override
  public void execute(){
    bookStore.searchBook(title, authors, isbn, publisher, sortOrder);
  }
}
