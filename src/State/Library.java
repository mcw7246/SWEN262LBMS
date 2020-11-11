package State;

import Books.Book;
import Books.CheckOut;

import java.io.FileNotFoundException;
import java.lang.reflect.GenericArrayType;
import java.text.SimpleDateFormat;
import java.util.*;

import Command.*;

import Books.BookStore;
import Client.Client;
import Visitors.PaidFine;
import Visitors.UnpaidFine;
import Visitors.Visit;
import Visitors.Visitor;

import javax.swing.*;

/**
 * @author Yug Patel - ydp4388
 */
public class Library
{

    LibraryState open;
    LibraryState closed;

    protected LibraryState libraryState;

    protected List<CheckOut> checkOuts;

    protected List<UnpaidFine> allUnpaidFines;
    protected List<PaidFine> allPaidFines;
    protected Double libraryBalance;

    private HashMap<Integer, Book> searchResult;
    private int visitorID;

    private BookStore bookStore;
    private Client client;

    //Visitors in the library database: Id -> Visitor.
    private HashMap<Integer, Visitor> visitors;
    //Books purchased by the library: Book -> Quantity.
    protected HashMap<Book, Integer> books;
    //List of current visitors: Id -> Visit start time.
    private HashMap<Integer, Integer> currentVisitors;
    //Number of purchased books with date: Day_Of_Year -> Number of Books.
    private HashMap<Integer, Integer> numPurchased;


    public Library(Client client) throws FileNotFoundException
    {
        this.visitors = new HashMap<>();
        books = new HashMap<>();
        bookStore = new BookStore(client);
        this.client = client;
        visitorID = 1000000000;
        currentVisitors = new HashMap<>();
        open = new Open(this);
        closed = new Closed(this);
        libraryState = open;
        this.numPurchased = new HashMap<>();
        this.checkOuts = new ArrayList<>();
        this.libraryBalance = 0.0;
        this.allPaidFines = new ArrayList<>();
        this.allUnpaidFines = new ArrayList<>();
    }

    /**
     * Method to change the Library State
     *
     * @param state - State of Library.
     */
    void setLibraryState(LibraryState state)
    {
        libraryState = state;
    }

    /**
     * Method to check is the library is open or not.
     *
     * @return - true  - if open.
     */
    public boolean isOpen()
    {
        return libraryState == open;
    }

    /**
     * Method to get all the books purchased by the library.
     *
     * @return Hashmap of all books
     */
    public HashMap<Book, Integer> getBooks()
    {
        return books;
    }

    /**
     * Method to simulate the closure of the Library.
     */
    public void closeLibrary()
    {
        client.setMessage("Library is now closing!");
        List<Command> commands = new ArrayList<>();
        for (Integer visitorId : currentVisitors.keySet())
        {
            commands.add(new EndVisit(visitorId, this));
        }
        for (Command command : commands)
        {
            command.execute();
        }
        setLibraryState(closed);
        client.setMessage("The library is now closed!");
    }

    /**
     * Method to simulate the opening of the Library.
     */
    public void openLibrary()
    {
        setLibraryState(open);
        client.setMessage("The Library is now open!");
    }

    /**
     * Method used by employees to purchase books.
     *
     * @param qty quantity of books
     * @param ID  book id for search results
     */
    public void purchaseBooks(Integer qty, List<Integer> ID)
    {
        List<Book> booksToBuy = new ArrayList<>();
        for (Integer num : client.getSearchResult().keySet())
        {
            for (Integer id : ID)
            {
                if (id == num)
                {
                    Book book = client.getSearchResult().get(num);
                    books.put(book, qty);
                    booksToBuy.add(book);
                    Integer day = client.getEndTime().get(Calendar.DAY_OF_YEAR);
                    if (numPurchased.containsKey(day))
                    {
                        numPurchased.put(day, numPurchased.get(day) + 1);
                    }
                    else
                    {
                        numPurchased.put(day, 1);
                    }
                }
            }
        }
        String message = "buy,success," + qty;
        for(Book book : booksToBuy){
             message += "\n" + book.getIsbn() + "," + book.getTitle() + ",{" + book.getAuthor() + "}," + book.getPublishDate() + "," + qty ;
        }
        client.setMessage(message);
    }


    public HashMap<Integer, Integer> getNumPurchased()
    {
        return numPurchased;

    }


    public void findBorrowedBooks (Integer visitorID)
    {
        Visitor visitor = visitors.get(visitorID);
        client.setSearchResult(visitor.getCheckedOutBooks());
        String message = "";
        message += "borrowed," + client.getSearchResult().size();
        for (Integer id : client.getSearchResult().keySet()) {
            Book bookSearch = client.getSearchResult().get(id);
            message += "\n";
            message += id + " - " + bookSearch.getIsbn() + "," + bookSearch.getTitle() + ",{" + bookSearch.getAuthor() + "}," + bookSearch.getPublisher() + "," + bookSearch.getPublishDate() + "," + bookSearch.getPageCount();
        }
        client.setMessage(message);
    }

    /**
     * Method to register a new Visitor
     *
     * @param fName   first name
     * @param lName   last name
     * @param address address
     * @param pNumber phone number
     */
    public void registerVisitor (String fName, String lName, String address, String pNumber)
    {
        visitorID++;
        Visitor visitor = new Visitor(fName, lName, address, pNumber);
        visitor.setId(visitorID);
        visitors.put(visitorID, visitor);
        client.setMessage("register," + visitorID + "," + client.getDate() + ";");
    }

    /**
     * Method to check if a Visitor is registered or not.
     *
     * @param fName   - first name.
     * @param lName   - last name.
     * @param address - address.
     * @param pNumber - phone number.
     * @return - ture if registered.
     */
     public boolean existingVisitor (String fName, String lName, String address, String pNumber)
     {
         boolean existing = false;
         for (Visitor visitor : visitors.values())
         {
            if (fName.equals(visitor.getfName()) && lName.equals(visitor.getlName()) && address.equals(visitor.getAddress()) && pNumber.equals(visitor.getPhoneNum()))
            {
                existing = true;
                break;
            }
         }
         return existing;
     }

     /**
      * Method to check if a visitor id exists or not.
      *
      * @param visitorID - visitor id.
      * @return - false if exists.
      */
     public boolean invalidID (Integer visitorID)
     {
         if (visitors.containsKey(visitorID))
         {
             return false;
         }
         else {
             return true;
         }
     }
     /**
      * Method to get all the current visitors in the library.
      *
      * @return - Hashmap of current visitors.
      */
     protected HashMap<Integer, Integer> getCurrentVisitors ()
     {
         return currentVisitors;
     }

     /**
      * Method to get all registered visitor of the library.
      *
      * @return - Hashmap of all registered visitors.
      */
     protected HashMap<Integer, Visitor> getVisitors ()
     {
         return visitors;
     }

     public Integer getTotalRegistered () {
         return visitors.size();
     }

     /**
      * Method to get the client class through which the library responds.
      *
      * @return - the client.
      */
     protected Client getClient ()
     {
         return client;
     }

     /**
      * Method to start a visit in the library.
      *
      * @param visitorId - visitor id.
      */
     public void startVisit (Integer visitorId)
     {
         libraryState.startVisit(visitorId);
     }

     /**
      * Method to end a visit in the library.
      *
      * @param visitorID - visitor id.
      */
     public void endVisit (Integer visitorID)
     {
         libraryState.endVisit(visitorID);
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


    public void bookSearch(String title, List<String> authors, String isbn, String publisher, String sortOrd)
    {
        String message = "";
        String titleSub = title.substring(1, title.length() - 1);


        List<Book> bookFits = new ArrayList<>(books.keySet());
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
            bookFits = searchTitles(titleSub, bookFits);
        }

        if(!allAuthors){
            bookFits = searchAuthors(authors, bookFits);
        }

        if(!allISBN){
            bookFits = searchISBN(isbn, bookFits);
        }

        if(!allPublisher){
            bookFits = searchPublisher(publisher, bookFits);
        }
        client.setSearchResult(bookFits);
        message = "info," + bookFits.size();
        for (Integer id : client.getSearchResult().keySet())
        {
            Book bookSearch = client.getSearchResult().get(id);
            message += "\n";
            message += id + " - " + bookSearch.getIsbn() + "," + bookSearch.getTitle() + ",{" + bookSearch.getAuthor() + "}," + bookSearch.getPublisher() + "," + bookSearch.getPublishDate() + "," + bookSearch.getPageCount();
        }
        client.setMessage(message);
    }

    public void checkOutBooks (List<Integer> books, int visitorID){
        libraryState.borrowBook(books, visitorID);
    }

     /**
      * When a visitor returns borrowed book(s)
      * @param visitorID - the visitoryID of the visitor returning books
      */
     public void returnBooks (int visitorID, List<Integer> bookId)
     {
        libraryState.returnBooks(visitorID,bookId);
     }

     /**
      * Pay a given amount toward a visitor's fine
      *
      * @param visitorID - id of visitor paying the fine
      * @param amount - amount to pay
      */
     public void payFine ( int visitorID, double amount)
     {
         if(this.invalidID(visitorID)) {
             client.setMessage("pay,invalid-visitor-id;");
         }
         else {
             Visitor visitor = this.visitors.get(visitorID);

             // Check for invalid amount
             if (amount < 0 || amount > visitor.getBalance()) {
                 client.setMessage("pay,invalid-amount," + amount + "," + visitor.getBalance() + ";");
             }
             else {
                 PaidFine paidFine = visitor.payFine(amount, this.client.getDateObj().get(Calendar.DAY_OF_YEAR));
                 this.allPaidFines.add(paidFine);
                 libraryBalance -= amount;
                 this.client.setMessage("pay,success," + visitor.getBalance() + ";");
             }
         }
     }

    public List<PaidFine> getAllPaidFines() {
        return allPaidFines;
    }

    public Double getLibraryBalance(){
         return libraryBalance;
    }
}

