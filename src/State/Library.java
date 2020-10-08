package State;

import Books.Book;
import Books.CheckOut;

import java.util.HashMap;
import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;

import State.LibraryState;
import State.Open;

public class Library {

    LibraryState open;
    LibraryState closed;

    LibraryState libraryState;

    List<CheckOut> checkOuts;

    private HashMap<Integer, Book> searchResult;
    //Books purchased by the library: Book -> Quantity.
    private HashMap<Book, Integer> books;

    public Library(){
        open = new Open();
        books = new HashMap<>();
        searchResult = new HashMap<>();
    }

    public HashMap<Book, Integer> getBooks(){
        return books;
    }

    public void purchaseBooks(Integer qty, List<Integer> ID){
        for(Integer num: searchResult.keySet()){
            for(Integer id : ID){
                if(id == num){
                    books.put(searchResult.get(num), qty);
                }
            }
        }
    }

    public void checkOutBooks(List<Integer> bookISBNs, Calendar checkInDate, Calendar checkOutDate, int visitorID){
        CheckOut CO = new CheckOut(bookISBNs, checkInDate, checkOutDate, visitorID);
        checkOuts.add(CO);
    }
}
