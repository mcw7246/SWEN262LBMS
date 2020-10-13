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
    //Response to the user.
    private String message;

    private Calendar startDateTime;
    //Simulation time of the library.
    private Calendar cal;
    private DateFormat dateFormat;

    //List of all visits made by the visitors.
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

    /**
     * Method to set the response to the user.
     * @param string - the message.
     */
    public void setMessage(String string){
        this.message = string;
    }

    /**
     * Method to get the response.
     * @return - the message.
     */
    public String getMessage(){
        return message;
    }

    /**
     * Method to get date and time in a string format.
     * @return - "date,time"
     */
    public String getDateTime(){
        return dateFormat.format(cal.getTime());
    }

    /**
     * Method to get the hours of the day.
     * @return - Hour.
     */
    public Integer getTime(){
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Method to get the date in a string format.
     * @return - "date"
     */
    public String getDate(){
        return new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());
    }

    /**
     * Method to get the date object
     * @return - "date"
     */
    public Calendar getDateObj(){
        return cal;
    }

    /**
     * Method to advance the simulation time.
     * @param days - days to advance.
     * @param hours - hours to advance.
     */
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

    /**
     * Method to check and change the library state if needed.
     */
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

    /**
     * Method to get the most recent search results.
     * @return - Hashmap of search results.
     */
    public HashMap<Integer, Book> getSearchResult(){
        return searchResult;
    }

    /**
     * Method to get the command parser.
     * @return - command parser
     */
    public CommandParser getCommandParser(){
        return commandParser;
    }

    /**
     * Method to record a new Visit completed by a visitor.
     * @param visit - the visit.
     */
    public void addNewVisit(Visit visit){
        allVisits.add(visit);
    }

    /**
     * Method to get all the visits.
     * @return - List of all visits.
     */
    public List<Visit> getAllVisits(){
        return allVisits;
    }

    /**
     * Method to get the start time of the Visit.
     * @param duration - total duration of the visit.
     * @return - Calender with the the start time of the visit.
     */
    public Calendar getStartTime(Integer duration){
        Calendar calendar = cal;
        calendar.add(Calendar.HOUR_OF_DAY, -duration);
        return calendar;
    }

    /**
     * Method to get the end time(current time) of the visit.
     * @return - Calender with the end time of the visit.
     */
    public Calendar getEndTime(){
        return cal;
    }
}
