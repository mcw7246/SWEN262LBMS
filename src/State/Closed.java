package State;

import Client.Client;
import Visitors.Visitor;

import java.util.HashMap;
import java.util.List;

/**
 * implements the closed state of the library and implements the interface LibraryState
 * @author
 */
public class Closed implements LibraryState{

    private final Library library;
    private HashMap<Integer, Visitor> visitors;
    private Client client;

    /**
     * constructor
     * @param library library that the state is changing
     */
    public Closed(Library library){
        this.library = library;
        visitors = library.getVisitors();
        client = library.getClient();
    }

    /**
     * creates the client message for if the user is trying to start a visit when the library is closed
     * @param visitorId - visitor id.
     */
    @Override
    public void startVisit(Integer visitorId) {
        client.setMessage("The Library is currently closed!");
    }

    /**
     * creates the client message for if the user tries to end a visit when library is closed
     * @param visitorId - visitor id.
     */
    @Override
    public void endVisit(Integer visitorId) {
        client.setMessage("The Library is currently closed!");
    }

    /**
     * creates the client message for a user who tries to borrow books when the library is closed
     * @param books books client is trying to borrow
     * @param visitorID visitor id
     */
    @Override
    public void borrowBook(List<Integer> books, int visitorID) {
        client.setMessage("The Library is currently closed!");
    }

    /**
     * creates client message for a user who tries to return books when the library is closed
     * @param visitorID visitor idd
     * @param bookId books being returned
     */
    @Override
    public void returnBooks(int visitorID, List<Integer> bookId) {
        client.setMessage("The Library is currently closed!");
    }
}
