package Books;

import Sort.Sort;

import java.util.ArrayList;
import java.util.List;

public class BookQuery {
    
    private int sortType; // 1=title, 2=publish date, 3=total # copies, 4=available # copies

    public BookQuery(int sortType){
        this.sortType = sortType;
    }

    public String Search(){
        String result = "";

        Sort s = new Sort(sortType);
        List<Book> books = new ArrayList<Book>();

        s.sort(books);

        for(Book book: books)
        {
            result += book.toString();
        }

        return result;
    }

}
