package State;

import Client.Client;
import Visitors.Visitor;

import java.util.HashMap;
import java.util.List;

public class Closed implements LibraryState{

    private final Library library;
    private HashMap<Integer, Visitor> visitors;
    private Client client;

    public Closed(Library library){
        this.library = library;
        visitors = library.getVisitors();
        client = library.getClient();
    }

    @Override
    public void startVisit(Integer visitorId) {
        client.setMessage("Library is currently closed!");
    }

    @Override
    public void endVisit(Integer visitorId) {
        client.setMessage("Library is currently closed!");
    }

    @Override
    public void borrowBook(Integer id, List<Integer> bookId) {

    }
}
