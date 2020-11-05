/**
 * Stores all books availabel for purchase
 *
 * @author Mikayla Wishart - mcw7246
 */
package Books;

import Client.Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BookStore
{

  List<Book> bookList;
  private Client client;

  public BookStore(Client client) throws FileNotFoundException
  {
    this.client = client;
    bookList = new ArrayList<>();
    File books = new File("src/Books/books.txt");
    Scanner scanner = new Scanner(books);

    boolean specialType = false;

    String arg = "";
    List<String> lines = new ArrayList<>();

    //goes through the given file and puts every line into an array of lines
    while (scanner.hasNext())
    {
      String line = scanner.nextLine();
      if (line != "")
      {
        lines.add(line);
      }
    }

    //loops through every line
    for (String line : lines)
    {
      List<String> bookInfo = new ArrayList<>();

      int numChar = 0;
      //loops through all the characters in the line
      for (char current : line.toCharArray())
      {
        numChar++;
        //if it is the beginning of a special type (ie. author or title) then it sets special type to true
        if ((current == '\"' || current == '{') && !specialType)
        {
          specialType = true;
        }
        //if it is already in a special type and has a special character to end it then it sets specialType to false
        else if ((current == '\"' || current == '}') && specialType)
        {
          specialType = false;
        }
        //if there is a comma and it is not a special type then the parameter for a book is done and it is added to the bookinfo array
        else if (current == ',' && !specialType)
        {
          bookInfo.add(arg);
          arg = "";
        }
        //else it adds the current character to the string
        else
        {
          arg += current;
        }

        //gets the last arg for the book info (the page num) and adds that to the book info array
        if (bookInfo.size() == 5 && line.length() == numChar)
        {
          bookInfo.add(arg);
          arg = "";
        }
      }
      Book newBook = new Book(bookInfo.get(0), bookInfo.get(1), bookInfo.get(2), bookInfo.get(3), bookInfo.get(4), bookInfo.get(5));
      bookList.add(newBook);
    }
  }


  public List<Book> getBookList()
  {
    return bookList;
  }

  public List<Book> searchTitles(String title, List<Book> books){
    List<Book> results = books;
    results.removeIf(book -> (!book.getTitle().contains(title)));
    return results;
  }

  public List<Book> searchAuthors(List<String> authors, List<Book> books){
    List<Book> results = books;
    for(String author : authors){
      results.removeIf(book -> (!book.getAuthor().contains(author)));
    }
    return results;
  }

  public List<Book> searchISBN(String isbn, List<Book> books){
    List<Book> results = books;
    results.removeIf(book -> (!book.getIsbn().equals(isbn)));
    return results;
  }

  public List<Book> searchPublisher(String publisher, List<Book> books){
    List<Book> results = books;
    results.removeIf(book -> (!book.getPublisher().contains(publisher)));
    return results;
  }


  public void searchBook(String title, List<String> authors, String isbn, String publisher, String sortOrd)
  {
    String message = "";
    String titleSub = title.substring(1, title.length() - 1);

    List<Book> bookFits = bookList;
    boolean allAuthors = true;
    boolean allTitles = true;
    boolean allISBN = true;
    boolean allPublisher = true;
    boolean sorted = false;

    if(!titleSub.equals("*"))
      allTitles = false;
    if(!authors.get(0).equals("*"))
      allAuthors = false;
    if(!isbn.equals("*"))
      allISBN = false;
    if(!publisher.equals("*"))
      allPublisher = false;
    if(!sortOrd.equals("*"))
      sorted = true;


    if(!allTitles)
    {
      searchTitles(titleSub, bookFits);
    }

    if(!allAuthors){
      searchAuthors(authors, bookFits);
    }

    if(!allISBN){
      searchISBN(isbn, bookFits);
    }

    if(!allPublisher){
      searchPublisher(publisher, bookFits);
    }
    client.setSearchResult(bookFits);
    message = "search," + bookFits.size();
    for (Integer id : client.getSearchResult().keySet())
    {
      Book bookSearch = client.getSearchResult().get(id);
      message += "\n";
      message += id + " - " + bookSearch.getIsbn() + "," + bookSearch.getTitle() + ",{" + bookSearch.getAuthor() + "}," + bookSearch.getPublisher() + "," + bookSearch.getPublishDate() + "," + bookSearch.getPageCount();
    }
    client.setMessage(message);
  }
}

