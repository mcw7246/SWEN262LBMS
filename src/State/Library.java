package State;

import Books.Book;
import Books.CheckOut;

import java.io.FileNotFoundException;
import java.lang.invoke.CallSite;
import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;

import Command.*;
import State.LibraryState;
import State.Open;

import Books.BookStore;
import Client.Client;
import Visitors.Visitor;

/**
 * @author Yug Patel - ydp4388
 */
public class Library {

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


    public Library(Client client) throws FileNotFoundException
    {
        this.visitors = new HashMap<>();
        books = new HashMap<>();
        bookStore = new BookStore();
        this.client = client;
        visitorID = 1000000000;
        currentVisitors = new HashMap<>();
        open = new Open(this);
        closed = new Closed(this);
        libraryState = open;
    }

    /**
     * Method to change the Library State
     * @param state - State of Library.
     */
    void setLibraryState(LibraryState state){
        libraryState = state;
    }

    /**
     * Method to check is the library is open or not.
     * @return - true  - if open.
     */
    public boolean isOpen() {
        return libraryState == open;
    }

    /**
     * Mehtod to get all the books purchased by the library.
     * @return
     */
    public HashMap<Book, Integer> getBooks(){
        return books;
    }

    /**
     * Method to simulate the closure of the Library.
     */
    public void closeLibrary(){
        Command command;
        for(Integer visitorId:currentVisitors.keySet()){
            command = new EndVisit(visitorId, this);
            command.execute();
        }
        setLibraryState(closed);
    }

    /**
     * Method to simulate the opening of the Library.
     */
    public void openLibrary(){
        setLibraryState(open);
    }

    /**
     * Method used by employees to purchase books.
     * @param qty quantity of books
     * @param ID book id for search results
     */
    public void purchaseBooks(Integer qty, List<Integer> ID){
        for(Integer num: client.getSearchResult().keySet()){
            for(Integer id : ID){
                if(id == num){
                    books.put(client.getSearchResult().get(num), qty);
                }
            }
        }
    }


    public void checkOutBooks(List<Integer> bookISBNs, Calendar checkInDate, Calendar checkOutDate, int visitorID){
        CheckOut CO = new CheckOut(bookISBNs, checkInDate, checkOutDate, visitorID);
        checkOuts.add(CO);
    }

    /**
     * Method to register a new Visitor
     * @param fName first name
     * @param lName last name
     * @param address address
     * @param pNumber phone number
     */
    public void registerVisitor(String fName, String lName, String address, String pNumber){
        visitorID++;
        Visitor visitor = new Visitor(fName,lName,address,pNumber);
        visitor.setId(visitorID);
        visitors.put(visitorID, visitor);
        client.setMessage("register," + visitorID + "," + client.getDate() + ";");
    }

    /**
     * Method to check if a Visitor is registered or not.
     * @param fName - first name.
     * @param lName - last name.
     * @param address - address.
     * @param pNumber - phone number.
     * @return - ture if registered.
     */
    public boolean existingVisitor(String fName, String lName, String address, String pNumber){
        boolean existing = false;
        for(Visitor visitor : visitors.values()){
            if(fName.equals(visitor.getfName()) && lName.equals(visitor.getlName()) && address.equals(visitor.getAddress()) && pNumber.equals(visitor.getPhoneNum())){
                existing = true;
                break;
            }
        }
        return existing;
    }

    /**
     * Method to check if a visitor id exists or not.
     * @param visitorID - visitor id.
     * @return - false if exists.
     */
    public boolean invalidID(Integer visitorID){
        if(visitors.containsKey(visitorID)){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Method to get all the current visitors in the library.
     * @return - Hashmap of current visitors.
     */
    protected HashMap<Integer, Integer> getCurrentVisitors() {
        return currentVisitors;
    }

    /**
     * Method to get all registered visitor of the library.
     * @return - Hashmap of all registered visitors.
     */
    protected HashMap<Integer, Visitor> getVisitors() {
        return visitors;
    }

    /**
     * Method to get the client class through which the library responds.
     * @return - the client.
     */
    protected Client getClient() {
        return client;
    }

    /**
     * Method to start a visit in the library.
     * @param visitorId - visitor id.
     */
    public void startVisit(Integer visitorId){
        libraryState.startVisit(visitorId);
    }

    /**
     * Method to end a visit in the library.
     * @param visitorID - visitor id.
     */
    public void endVisit(Integer visitorID){
        libraryState.endVisit(visitorID);
    }

    public void bookSearch(int id, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrd){
        ArrayList<Book> searchResults = new ArrayList<>();
        List<Book> books = bookStore.getBookList();
        boolean sorted = false;
        int caseNum;
        if(isbn.equals("")){
            caseNum = 0;
        }
        else if (publisher.equals("")){
            caseNum = 1;
        }
        else{
            caseNum = 1;
            sorted = true;
        }

        for(Book book : books){
            if(book.getTitle().equals("*")){
                if(authors.equals(book.getAuthor())){
                    searchResults.add(book);
                }
            }
            else
            {
                if (book.getTitle().equals(title) && book.getAuthor().equals(authors))
                {
                    switch (caseNum)
                    {
                        case 0:
                            if(isbn.equals(book.getIsbn())){
                                searchResults.add(book);
                            }
                        case 1:
                            if(isbn.equals(book.getIsbn()) && publisher.equals(book.getPublisher())){
                                searchResults.add(book);
                            }
                    }

                }
            }
        }
    }
}
