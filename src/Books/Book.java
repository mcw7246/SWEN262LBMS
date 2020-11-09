package Books;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Creates object of a single book in the library
 *
 * @author Mikayla Wishart - mcw7246
 */
public class Book implements Product
{
  private SimpleBooleanProperty checked = new SimpleBooleanProperty(false);
  private Integer numToBuy;

  private Integer idNum;
  private String isbn;
  private String title;
  private String author;
  private String publisher;
  private String publishDate;
  private String pageCount;
  private int numCopies;
  private int numCopiesAvailable;

  public Book( Integer idNum, String isbn, String title, String authors, String publisher, String publishDate){

    this.idNum = idNum;
    this.isbn = isbn;
    this.title = title;
    this.author = authors;
    this.publisher = publisher;
    this.publishDate = publishDate;

  }
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
  public Book(String isbn, String title, String author, String publisher, String publishDate, String pageCount){
    this.isbn = isbn;
    this.title = title;
    this.author = author;
    this.publisher = publisher;
    this.publishDate = publishDate;
    this.pageCount = pageCount;
    numCopies = 0;
    numCopiesAvailable = 0;
  }

  public Integer getIdNum(){return idNum;}

  public SimpleBooleanProperty checkedProperty(){return this.checked;}

  public java.lang.Boolean getChecked(){return this.checkedProperty().get();}

  public void setChecked(final Boolean checked){
    this.checkedProperty().set(checked);
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
   * @return Publisher for given book
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
  public String getPageCount(){
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
  public void setIdNum(int num){idNum = num; }
  public void setNumToBuy(int num){numToBuy = num;}
  public Integer getNumToBuy(){return numToBuy;}

    /**
   *  Adds to the number of copies of available when a visitor returns books
   */
  public void returnCopies(int amount){
      numCopiesAvailable += amount;
  }
}
