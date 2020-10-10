package Command;

import java.util.List;

public class StoreSearch implements Command
{
  String title;
  List<String> authors;
  String isbn;
  String publisher;
  String sortOrder;

  public StoreSearch(String title, List<String> authors){
    this.title = title;
    this.authors = authors;
  }

  public StoreSearch(String title, List<String> authors, String isbn){
    this.title = title;
    this.authors = authors;
    this.isbn = isbn;
  }

  public StoreSearch(String title, List<String> authors, String isbn, String publisher){
    this.title = title;
    this.authors = authors;
    this.isbn = isbn;
    this.publisher = publisher;
  }

  public StoreSearch(String title, List<String> authors, String isbn, String publisher, String sortOrder){
    this.title = title;
    this.authors = authors;
    this.isbn = isbn;
    this.publisher = publisher;
    this.sortOrder = sortOrder;
  }



  @Override
  public void execute(){

  }
}
