package Command;

import Books.Book;
import Books.BookStore;

import java.util.ArrayList;
import java.util.List;

public class StoreSearch implements Command
{
  String title;
  List<String> authors = null;
  String isbn = "*";
  String publisher = "*";
  String sortOrder = "*";
  BookStore bookStore;

  public StoreSearch(BookStore bookStore, String title, List<String> authors, String isbn, String publisher, String sortOrder){
    this.bookStore = bookStore;
    this.title = title;
    this.authors = authors;
    this.isbn = isbn;
    this.publisher = publisher;
    this.sortOrder = sortOrder;
  }

  @Override
  public void execute(){
    bookStore.searchBook(title, authors, isbn, publisher, sortOrder);
  }
}
