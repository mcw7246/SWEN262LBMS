package Command;

import State.Library;
import Visitors.Visitor;

public class NewVisitor implements Command{

    private Library library;
    private String fName;
    private String lName;
    private String address;
    private String  pNumber;

    public NewVisitor(String firstName, String lastName, String address, String pNumber, Library library){
        this.fName = firstName;
        this.lName = lastName;
        this.address = address;
        this.pNumber = pNumber;
        this.library = library;
    }

    @Override
    public void execute() {
        library.registerVisitor(fName,lName,address,pNumber);
    }

}
