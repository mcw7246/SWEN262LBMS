package State;

import Books.Book;
import Books.CheckOut;

import java.util.HashMap;
import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;

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

    public Library(Client client) throws FileNotFoundException {
        this.visitors = new HashMap<>();
        open = new Open();
        closed = new Closed();
        books = new HashMap<>();
        bookStore = new BookStore();
        this.client = client;
        visitorID = 1000000000;
    }

    public HashMap<Book, Integer> getBooks(){
        return books;
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
        client.setMessage("register," + visitorID + "," + client.getDateTime() + ";");
    }

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

    public boolean invalidID(Integer visitorID){
        if(visitors.containsKey(visitorID)){
            return false;
        }
        else{
            return true;
        }
    }


}
