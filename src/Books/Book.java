package Books;

/**
 * Creates object of a single book in the library
 *
 * @author Mikayla Wishart - mcw7246
 */
public class Book
{
  private String isbn;
  private String title;
  private String author;
  private String publisher;
  private String publishDate;
  private int pageCount;
  private int numCopies;
  private int numCopiesAvailable;

  /**
   * Constructor for creating a Book object
   *
   * @param isbn ISBN for the given book
   * @param title title for the given book
   * @param author author for the given book
   * @param publisher publisher for the given book
   * @param publishDate publish date for the given book
   * @param pageCount page count for the given book
   */
  public Book(String isbn, String title, String author, String publisher, String publishDate, int pageCount){
    this.isbn = isbn;
    this.title = title;
    this.author = author;
    this.publisher = publisher;
    this.publishDate = publishDate;
    this.pageCount = pageCount;
    numCopies = 0;
    numCopiesAvailable = 0;
  }

  /**
   *
   * @return ISBN for given book
   */
  public String getIsbn(){
    return isbn;
  }

  /**
   *
   * @return Title for given book
   */
  public String getTitle(){
    return title;
  }

  /**
   *
   * @return Author for given book
   */
  public String getAuthor(){
    return author;
  }

  /**
   *
   * @return Publisher for givvn book
   */
  public String getPublisher(){
    return publisher;
  }

  /**
   *
   * @return Publish date for given book
   */
  public String getPublishDate(){
    return publishDate;
  }

  /**
   *
   * @return Page count for given book
   */
  public int getPageCount(){
    return pageCount;
  }

  public int getNumCopies(){
    return numCopies;
  }

  public int getNumCopiesAvailable(){
    return numCopiesAvailable;
  }

  public void setNumCopies(int value){
    numCopies = value;
  }

  public void setNumCopiesAvailable(int value){
    numCopiesAvailable = value;
  }
}
