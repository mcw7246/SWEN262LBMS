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

        boolean specialType = false;

        String arg = "";
        List<String> lines = new ArrayList<>();

        //goes through the given file and puts every line into an array of lines
        while(scanner.hasNext())
        {
          String line = scanner.nextLine();
          if(line !=""){
            lines.add(line);
          }
        }

        //loops through every line
        for(String line : lines){
          List<String> bookInfo = new ArrayList<>();
          int numChar = 0;
          //loops through all the characters in the line
          for(char current : line.toCharArray()){
            numChar++;
            //if it is the beginning of a special type (ie. author or title) then it sets special type to true
            if((current == '\"' || current == '{') && !specialType){
              specialType = true;
            }
            //if it is already in a special type and has a special character to end it then it sets specialType to false
            else if((current == '\"' || current == '}') && specialType){
              specialType = false;
            }
            //if there is a comma and it is not a special type then the parameter for a book is done and it is added to the bookinfo array
            else if(current == ',' && !specialType){
              bookInfo.add(arg);
              arg = "";
            }
            //else it adds the current character to the string
            else{
              arg += current;
            }

            //gets the last arg for the book info (the page num) and adds that to the book info array
            if(bookInfo.size() == 5 && line.length() == numChar){
              bookInfo.add(arg);
              arg = "";
            }
          }
          System.out.println(bookInfo);
          Book newBook = new Book(bookInfo.get(0), bookInfo.get(1), bookInfo.get(2), bookInfo.get(3), bookInfo.get(4), bookInfo.get(5));
          bookList.add(newBook);
        }
    }


    public List<Book> getBookList() {
        return bookList;
    }
}
