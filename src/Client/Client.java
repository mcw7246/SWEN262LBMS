package Client;

import Books.Book;
import Books.BookStore;
import Command.CommandParser;
import State.Closed;
import State.Library;
import Visitors.Visit;

import java.io.FileNotFoundException;
import java.lang.invoke.CallSite;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Client {

    private Library library;
    BookStore bookStore;
    private CommandParser commandParser;
    private String message;

    private Calendar startDateTime;
    private Calendar cal;
    private DateFormat dateFormat;

    private List<Visit> allVisits;

    private HashMap<Integer, Book> searchResult;

    public Client() throws FileNotFoundException {
        library = new Library(this);
        commandParser = new CommandParser(library, this, bookStore);
        this.cal = Calendar.getInstance();
        this.dateFormat = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss");
        startDateTime = cal;
        this.allVisits = new ArrayList<>();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE),8,0,0);
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
            setMessage("advance,invalid-number-of-days," + days + ";");
        }
        else if(hours > 23 || hours < 0){
            setMessage("advance,invalid-number-of-hours," + hours + ";");
        }
        else{
            cal.add(Calendar.DATE, days);
            cal.add(Calendar.HOUR_OF_DAY, hours);
            setMessage("advance,success;");
            checkLibraryState();
        }
    }

    private void checkLibraryState(){
        if(cal.get(Calendar.HOUR_OF_DAY) < 8 || cal.get(Calendar.HOUR_OF_DAY) >= 19){
            if(library.isOpen()){
                library.closeLibrary();
            }
        }
        else{
            if(!library.isOpen()){
                library.openLibrary();
            }
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

    public void addNewVisit(Visit visit){
        allVisits.add(visit);
    }

    public List<Visit> getAllVisits(){
        return allVisits;
    }

    public Calendar getStartTime(Integer duration){
        Calendar calendar = cal;
        calendar.add(Calendar.HOUR_OF_DAY, -duration);
        return calendar;
    }

    public Calendar getEndTime(){
        Calendar calendar = cal;
        return calendar;
    }
}
