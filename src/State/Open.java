package State;

import Client.Client;
import Visitors.Visit;
import Visitors.Visitor;

import java.util.HashMap;
import java.util.List;

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
            Integer visitTime = client.getTime() - startTime;
            Visit visit = new Visit(client.getStartTime(visitTime),visitorId,client.getEndTime());
            client.addNewVisit(visit);
            client.setMessage("arrive," + visitorId + "," + client.getDateTime() + ";");
        }
    }

    @Override
    public void borrowBook(Integer id, List<Integer> bookId) {

    }
}
