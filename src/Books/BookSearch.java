package Books;

import Sort.Sort;

import java.util.ArrayList;
import java.util.List;

public class BookSearch {
    
    
    // sortOrder: title, publish-date, book-status(any copies left)
    public BookSearch(String title, String authors, int isbn, String publisher, String sortOrder){
    }

    public String Search(){
        String result = ""; //get results that match search criteria

        Sort s = new Sort();
        List<Book> books = new ArrayList<Book>();

        s.sort(books);

        for(Book book: books)
        {
            result += book.toString();
        }

        return result;
    }

}
