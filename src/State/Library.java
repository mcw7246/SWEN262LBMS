package State;

import Books.Book;

import java.util.HashMap;
import java.util.List;
import State.LibraryState;
import State.Open;

import java.util.ArrayList;

public class Library {

    LibraryState open;
    LibraryState closed;

    LibraryState libraryState;

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
}
