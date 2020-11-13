package State;

import Books.Book;
import Books.CheckOut;
import Client.Client;
import Visitors.UnpaidFine;
import Visitors.Visit;
import Visitors.Visitor;

import java.text.SimpleDateFormat;
import java.util.*;

public class Open implements LibraryState{

    private final Library library;
    private HashMap<Integer, Visitor> visitors;
    private Client client;

    public Open(Library library){
        this.library = library;
        visitors = library.getVisitors();
        client = library.getClient();
    }

    @Override
    public void startVisit(Integer visitorId) {
        if(visitors.get(visitorId).isVisit()){
            client.setMessage("arrive,duplicate;");
        }
        else{
            visitors.get(visitorId).setInVisit();
            library.getCurrentVisitors().put(visitorId, client.getTime());
            client.setMessage("arrive," + visitorId + "," + client.getDateTime() + ";");
        }
    }

    @Override
    public void endVisit(Integer visitorId) {
        if(!visitors.get(visitorId).isVisit()){
            client.setMessage("depart,invalid-id;");
        }
        else{
            visitors.get(visitorId).setEndVisit();
            //start time in hours(24 hrs)
            Integer startTime = library.getCurrentVisitors().remove(visitorId);
            Visit visit = new Visit(startTime,visitorId, client.getTime(), client.getEndTime());
            client.addNewVisit(visit);
            client.setMessage("depart," + visitorId + "," + client.getDateTime() + ";");
        }
    }

    @Override
    public void borrowBook(List<Integer> books, int visitorID) {
        if (library.invalidID(visitorID)){
            client.setMessage("borrow,invalid-visitor-id;");
        }
        else if(library.getVisitors().get(visitorID).isMaxCheckOut(books.size())){
            client.setMessage("borrow,book-limit-exceeded;");
        }
        else if(library.getVisitors().get(visitorID).getBalance() != 0){
            client.setMessage("borrow,outstanding-fine," + library.getVisitors().get(visitorID).getBalance() + ";");
        }
        else{
            Visitor visitor = visitors.get(visitorID);
            Date checkOutDate = client.getDateObj().getTime();
            client.getDateObj().add(Calendar.DATE, 7);
            Date checkInDate = client.getDateObj().getTime();
            client.getDateObj().add(Calendar.DATE, -7);
            boolean checkout = true;
            ArrayList<Integer> invalidNum = new ArrayList<>();
            List<CheckOut> currentCheckOut = new ArrayList<>();
            HashMap<Integer, Book> searchResults = client.getSearchResult();
            for(Integer num:books) {
                if(client.getSearchResult().containsKey(num)) {
                    CheckOut CO = new CheckOut(searchResults.get(num), checkInDate, checkOutDate, visitorID);
                    currentCheckOut.add(CO);
                }
                else{
                    checkout = false;
                    invalidNum.add(num);
                }
            }
            if(checkout){
                for(CheckOut checkOut: currentCheckOut){
                    Book book = checkOut.getBook();
                    //change book quantity.
                    library.books.replace(book,library.books.get(book) - 1);
                }
                library.checkOuts.addAll(currentCheckOut);
                library.checkOutsByUserID.put(Integer.toString(visitorID), currentCheckOut);
                visitor.setCheckOuts(currentCheckOut);
                String date = new SimpleDateFormat("yyyy/MM/dd").format(checkOutDate.getTime());
                client.setMessage("borrow," + date + ";");
            }
            else{
                currentCheckOut.clear();
                client.setMessage("borrow,invalid-book-id," + invalidNum.toString() + ";");
            }
        }
    }

    @Override
    public void returnBooks(int visitorID, List<Integer> bookId) {
        if(library.invalidID(visitorID)){
            client.setMessage("borrow,invalid-visitor-id;");
        }
        else {
            Visitor visitor = visitors.get(visitorID);
            boolean returnBooks = true;
            List<Integer> invalidNum = new ArrayList<>();
            List<Integer> booksToReturn = new ArrayList<>();
            HashMap<Integer, Book> searchResults = client.getSearchResult();
            for (Integer num : bookId) {
                if (searchResults.containsKey(num)) {
                    booksToReturn.add(num);
                } else {
                    returnBooks = false;
                    invalidNum.add(num);
                }
            }
            if (returnBooks) {
                double totalFine = 0.0;
                boolean overdue = true;
                List<Integer> overdueId = new ArrayList<>();
                for (Integer book : booksToReturn) {
                    Book currentBook = searchResults.get(book);
                    double fine = 0;
                    Date currentDate = client.getDateObj().getTime();
                    UnpaidFine unpaidFine = visitor.returnBooks(currentBook, currentDate);
                    library.books.replace(currentBook,library.books.get(currentBook) + 1);
                    if(unpaidFine != null){
                        fine = unpaidFine.getAmount();
                        library.allUnpaidFines.add(unpaidFine);
                        library.libraryBalance += fine;
                    }
                    if(fine > 0){
                        overdue = false;
                        totalFine += fine;
                        overdueId.add(book);
                    }
                }
                client.setMessage("return,success;");
                if (!overdue) {
                    client.setMessage("return,overdue,$" + totalFine + overdueId.toString() + ";");
                }
            }
            else{
                booksToReturn.clear();
                client.setMessage("return,invalid-book-id," + invalidNum.toString() + ";");
            }
        }
    }
}
