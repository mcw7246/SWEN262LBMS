package State;

import Books.Book;

import java.util.HashMap;
import java.util.List;
import State.LibraryState;
import State.Open;
import Visitors.Visitor;

import java.util.ArrayList;

/**
 * @author Yug Patel - ydp4388
 */
public class Library {

    LibraryState open;
    LibraryState closed;

    LibraryState libraryState;

    private long visitorID;

    private HashMap<Integer, Book> searchResult;
    //Visitors in the library database: Id -> Visitor.
    private HashMap<Long, Visitor> visitors;
    //Books purchased by the library: Book -> Quantity.
    private HashMap<Book, Integer> books;

    public Library(){
        open = new Open();
        books = new HashMap<>();
        searchResult = new HashMap<>();
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
        for(Integer num: searchResult.keySet()){
            for(Integer id : ID){
                if(id == num){
                    books.put(searchResult.get(num), qty);
                }
            }
        }
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
    }


}
