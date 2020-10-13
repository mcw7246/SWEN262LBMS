package State;

import Books.Book;
import Books.CheckOut;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import Command.*;

import Books.BookStore;
import Client.Client;
import Visitors.Visitor;

/**
 * @author Yug Patel - ydp4388
 */
public class Library
{

    LibraryState open;
    LibraryState closed;

    LibraryState libraryState;

    List<CheckOut> checkOuts;

    private HashMap<Integer, Book> searchResult;
    private int visitorID;

    private BookStore bookStore;
    private Client client;

    //Visitors in the library database: Id -> Visitor.
    private HashMap<Integer, Visitor> visitors;
    //Books purchased by the library: Book -> Quantity.
    private HashMap<Book, Integer> books;
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
        for (Integer num : client.getSearchResult().keySet())
        {
            for (Integer id : ID)
            {
                if (id == num)
                {
                    Book book = client.getSearchResult().get(num);
                    books.put(book, qty);
                    Integer day = client.getEndTime().get(Calendar.DAY_OF_YEAR);
                    if(numPurchased.containsKey(day)) {
                        numPurchased.put(day, numPurchased.get(day) + 1);
                    }
                    else{
                        numPurchased.put(day, 1);
                    }
                }
            }
        }
    }

    public HashMap<Integer, Integer> getNumPurchased() {
        return numPurchased;
    }

    /**
     * Method to register a new Visitor
     *
     * @param fName   first name
     * @param lName   last name
     * @param address address
     * @param pNumber phone number
     */
    public void registerVisitor(String fName, String lName, String address, String pNumber)
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
    public boolean existingVisitor(String fName, String lName, String address, String pNumber)
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
    public boolean invalidID(Integer visitorID)
    {
        if (visitors.containsKey(visitorID))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Method to get all the current visitors in the library.
     *
     * @return - Hashmap of current visitors.
     */
    protected HashMap<Integer, Integer> getCurrentVisitors()
    {
        return currentVisitors;
    }

    /**
     * Method to get all registered visitor of the library.
     *
     * @return - Hashmap of all registered visitors.
     */
    protected HashMap<Integer, Visitor> getVisitors()
    {
        return visitors;
    }

    public Integer getTotalRegistered(){
        return visitors.size();
    }

    /**
     * Method to get the client class through which the library responds.
     *
     * @return - the client.
     */
    protected Client getClient()
    {
        return client;
    }

    /**
     * Method to start a visit in the library.
     *
     * @param visitorId - visitor id.
     */
    public void startVisit(Integer visitorId)
    {
        libraryState.startVisit(visitorId);
    }

    /**
     * Method to end a visit in the library.
     *
     * @param visitorID - visitor id.
     */
    public void endVisit(Integer visitorID)
    {
        libraryState.endVisit(visitorID);
    }

    public void bookSearch(String title, ArrayList<String> authors, String isbn, String publisher, String sortOrd)
    {
        //gets rid of the \" in the front and end of the title
        String titleSub = title.substring(1, title.length() - 1);
        ArrayList<Book> searchResults = new ArrayList<>();
        List<Book> booksTest = bookStore.getBookList();
        boolean sorted = false;
        String message = "";

        //loops through all the books
        for(Book book : books.keySet()){
            int numAuthors = 0;
            //checks if it is any title
            if(titleSub.equals("*")){
                if(authors.get(0).equals("*")){
                    if(isbn.equals("*")){
                        if(publisher.equals(book.getPublisher())){
                            if(!searchResults.contains(book)){
                                searchResults.add(book);
                            }
                        }
                    }
                    else{
                        if(isbn.equals(book.getIsbn())){
                            if(!searchResults.contains(book)){
                                searchResults.add(book);
                            }
                        }
                    }
                }
                else{
                    for(String author : authors){
                        if(book.getAuthor().contains(author)){
                            numAuthors++;
                            if(numAuthors == authors.size()){
                                if(!searchResults.contains(book)){
                                    searchResults.add(book);
                                }
                            }
                        }
                    }
                }

            }
            else if(book.getTitle().toLowerCase().contains(titleSub.toLowerCase())){
                if(authors.get(0).equals("*")){
                    if(!searchResults.contains(book)){
                        searchResults.add(book);
                    }
                }
                for(String author : authors){
                    if(!author.equals("*")){
                        if((!publisher.equals("*") && !publisher.equals("")) || (!isbn.equals("*") && !isbn.equals(""))){
                            if(publisher.equals(book.getPublisher())){
                                if(isbn.equals(book.getIsbn())){
                                    if(!searchResults.contains(book)){
                                        searchResults.add(book);
                                    }
                                }
                            }
                            else{
                                if(isbn.equals(book.getIsbn())){
                                    if(!searchResults.contains(book)){
                                        searchResults.add(book);
                                    }
                                }
                            }

                        }
                        if(book.getAuthor().contains(author)){
                            numAuthors++;
                            if(numAuthors == authors.size()){
                                if(!searchResults.contains(book)){
                                    searchResults.add(book);
                                }
                            }
                        }
                    }
                }
            }


        }
        client.setSearchResult(searchResults);
        message = "info," + searchResults.size();
        for (Book bookSearch : searchResults)
        {
            message += "\n";
            message += bookSearch.getNumCopies() + "," + bookSearch.getIsbn() + "," + bookSearch.getTitle() + ",{" + bookSearch.getAuthor() + "}," + bookSearch.getPublisher() + "," + bookSearch.getPublishDate() + "," + bookSearch.getPageCount();
        }
        client.setMessage(message);
    }

    /**
     * When a visitor returns borrowed book(s)
     * @param visitorID - the visitoryID of the visitor returning books
     */
    public void returnBooks(int visitorID)
    {
        ArrayList<Book> books = new ArrayList<>();
        for (Book book: this.searchResult.values())
        {
            books.add(book);
        }

        Visitor visitor = this.visitors.get(visitorID);

        double fines = visitor.returnBooks(books, this.client.getDateObj());

        if (fines > 0)
        {
            //update client with amount due
        }
        else
        {
            //update client with success message
        }
    }
}
