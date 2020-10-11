package Client;

import Books.Book;
import Command.CommandParser;
import State.Library;

import java.io.FileNotFoundException;
import java.lang.invoke.CallSite;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Client {

    private Library library;
    private CommandParser commandParser;
    private String message;

    private Calendar startDateTime;
    private Calendar cal;
    private DateFormat dateFormat;

    private HashMap<Integer, Book> searchResult;

    public Client() throws FileNotFoundException {
        library = new Library(this);
        commandParser = new CommandParser(library, this);
        this.cal = Calendar.getInstance();
        this.dateFormat = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss");
        startDateTime = cal;
    }

    public void setMessage(String string){
        this.message = string;
    }

    public String getMessage(){
        return message;
    }

    public String getDateTime(){
        return dateFormat.format(cal.getTime());
    }

    public Integer getTime(){
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public String getDate(){
        return new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());
    }

    public void advanceTime(Integer days, Integer hours){
        if(days > 7 || days < 0){
            setMessage("Invalid-number-of-days," + days + ";");
        }
        else if(hours > 23 || hours < 0){
            setMessage("Invalid-number-of-hours," + hours + ";");
        }
        else{
            cal.add(Calendar.DATE, days);
            cal.add(Calendar.HOUR_OF_DAY, hours);
        }
    }

    public void setSearchResult(List<Book> bookList){
        searchResult = new HashMap<>();
        int i = 1;
        for(Book book:bookList){
            searchResult.put(i, book);
        }
    }

    public HashMap<Integer, Book> getSearchResult(){
        return searchResult;
    }

    public CommandParser getCommandParser(){
        return commandParser;
    }

}
