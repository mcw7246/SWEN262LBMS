package Books;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookStore {

    private List<Book> bookList;

    public BookStore() throws FileNotFoundException {
        bookList = new ArrayList<>();
        File books = new File("src/Books/books.txt");
        Scanner scanner = new Scanner(books);

        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            if(line != "") {
                String[] bookInfo = line.split(",");
                Book newBook = new Book(bookInfo[0], bookInfo[1].replace("\"", ""), bookInfo[2].replace("{", ""),
                        bookInfo[3].replace("\"", ""), bookInfo[4], bookInfo[5]);
                bookList.add(newBook);
            }
        }
    }

}
