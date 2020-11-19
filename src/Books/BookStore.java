package Books;

import Client.Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 * Stores all books available for purchase
 *
 * @author Mikayla Wishart - mcw7246
 */
public class BookStore
{

  List<Book> bookList;
  private Client client;
  public static Map<Book, Integer> availableBooks;
  public static Map<String, Book> bookByIsbn;

  /**
   * Constructor for the BookStore class
   * @param client - passed in client object
   * @throws FileNotFoundException - if the file for the books is not found
   */
  public BookStore(Client client) throws FileNotFoundException
  {
    //initializes all of the variables
    this.client = client;
    bookList = new ArrayList<>();
    availableBooks = new HashMap<>();
    bookByIsbn = new HashMap<>();

    //grabs the file that has the books info in it
    File books = new File("src/Books/books.txt");
    Scanner scanner = new Scanner(books);

    boolean specialType = false;

    String arg = "";
    List<String> lines = new ArrayList<>();

    //goes through the given file and puts every line into an array of lines
    while (scanner.hasNext())
    {
      String line = scanner.nextLine();
      //adds the lines to the arraylist of lines in the given file
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
      //creates a new book object from the info that is given from the file
      Book newBook = new Book(bookInfo.get(0), bookInfo.get(1), bookInfo.get(2), bookInfo.get(3), bookInfo.get(4), bookInfo.get(5));
      availableBooks.put(newBook, newBook.getNumCopiesAvailable());
      bookByIsbn.put(newBook.getIsbn(), newBook);
      bookList.add(newBook);
    }
  }

  /**
   * searches for all books that have the title that is given
   * as part of its own title
   * @param title the given title
   * @param books books that is being searched through
   * @return a list of books that contain the title
   */
  public List<Book> searchTitles(String title, List<Book> books){
    List<Book> results = books;
    results.removeIf(book -> (!book.getTitle().contains(title)));
    return results;
  }

  /**
   * searches for all books that have the authors that is
   * given by the user
   * @param authors list of strings of the authors names that the user is looking for
   * @param books list of books to look through
   * @return list of books that are written by the authors that the user is looking for
   */
  public List<Book> searchAuthors(List<String> authors, List<Book> books){
    List<Book> results = books;
    for(String author : authors){
      results.removeIf(book -> (!book.getAuthor().contains(author)));
    }
    return results;
  }

  /**
   *searches for all books that have the isbn that the user inputted (should only be one)
   * @param isbn the isbn that the user is looking for
   * @param books the list of books to look through
   * @return list of the book(s) that have the same isbn as the user was looking for
   */
  public List<Book> searchISBN(String isbn, List<Book> books){
    List<Book> results = books;
    results.removeIf(book -> (!book.getIsbn().equals(isbn)));
    return results;
  }

  /**
   * searches for all books that are published by a specific publisher
   * @param publisher the publisher that the user is looking for
   * @param books list of books to look through
   * @return list of books that are published by the specified publisher
   */
  public List<Book> searchPublisher(String publisher, List<Book> books){
    List<Book> results = books;
    results.removeIf(book -> (!book.getPublisher().contains(publisher)));
    return results;
  }


  /**
   * uses the user input to search for a specific group of books
   * @param title title of the book
   * @param authors author of the book
   * @param isbn isbn of the book
   * @param publisher publisher of the book
   * @param sortOrd sort order of the book
   */
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
    //creates the response to the command
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

