package Client;

import Books.Book;
import Books.BookStore;
import Command.CommandParser;
import State.Library;
import Visitors.PaidFine;
import Visitors.Visit;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Handles requests and responses
 *
 * @author Mikayla Wishart - mcw7246
 */
public class Client {

    private Library library;
    BookStore bookStore;
    private CommandParser commandParser;
    //Response to the user.
    private List<String> message;

    private Date startDateTime;
    //Simulation time of the library.
    private Calendar cal;
    private DateFormat dateFormat;

    //List of all visits made by the visitors.
    private List<Visit> allVisits;

    private HashMap<Integer, Book> searchResult;

    /**
     * Constructor for the Client class
     * @throws FileNotFoundException if the books file from the bookStore object is not found
     */
    public Client() throws FileNotFoundException {
        this.message = new ArrayList<>();
        library = new Library(this);
        bookStore = new BookStore(this);
        commandParser = new CommandParser(library, this, bookStore);
        this.cal = Calendar.getInstance();
        this.dateFormat = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss");
        startDateTime = cal.getTime();
        this.allVisits = new ArrayList<>();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE),8,0,0);
    }

    /**
     * Method to set the response to the user.
     * @param string - the message.
     */
    public void setMessage(String string){
        this.message.add(string);
    }

    /**
     * Method to get the response.
     * @return - the message.
     */
    public List<String> getMessage(){
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
            boolean closeLibrary = false;
            //if day changes, close the library.
            if(days > 0){
                closeLibrary = true;
            }
            cal.add(Calendar.DATE, days);
            //if hours are past close time.
            if(cal.get(Calendar.HOUR_OF_DAY) + hours >= 19){
                closeLibrary = true;
            }
            cal.add(Calendar.HOUR_OF_DAY, hours);
            setMessage("advance,success;");
            if(closeLibrary){
                if(library.isOpen()) {
                    library.closeLibrary();
                }
            }
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
            i++;
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
        Calendar calendar = this.getEndTime();
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

    public void generateReport(Integer days){
        String date = this.getDate();
        int numBooks = library.getBooks().size();
        Integer numVisitors = library.getTotalRegistered();
        List<Visit> visitList = new ArrayList<>();
        //Number of previous days.
        int numDay;
        if(days == 0){
            numDay = 0;
        }
        else {
            numDay = cal.get(Calendar.DAY_OF_YEAR) - days;
        }
        if(numDay > 0) {
            //Adding all visits in past "day" days.
            for (Visit visit : allVisits) {
                if(visit.getVisitDay() >= numDay){
                    visitList.add(visit);
                }
            }
        }
        //Calculating avg visit length.
        int visitLength = 0;
        for(Visit visit: visitList){
            visitLength += visit.getVisitLength();
        }
        Float numAvgVisit;
        if(days != 0) {
            numAvgVisit = (float) visitLength / days;
        }
        else{
            numAvgVisit = (float) visitLength;
        }
        //Number of Books Purchased.
        Integer booksPurchased = 0;
        for(Integer num: library.getNumPurchased().keySet()){
            if(num >= numDay){
                booksPurchased += library.getNumPurchased().get(num);
            }
        }
        //Fines Collected
        double finesCollected = 0.0;
        for(PaidFine paidFine: library.getAllPaidFines()){
            if(paidFine.getDatePaid() >= numDay){
                finesCollected += paidFine.getAmount();
            }
        }
        //total outstanding amount.
        double outstanding = library.getLibraryBalance();
        this.setMessage("report," + getDate() +
                ",\n Number of Books:" + numBooks +
                "\n Number of Visitors:" + numVisitors +
                "\n Average Length of Visit:"+ numAvgVisit +
                "\n Number of Books Purchased:" + booksPurchased +
                "\n Fines Collected:" + finesCollected +
                "\n Fines Outstanding:" + outstanding);
    }
}
