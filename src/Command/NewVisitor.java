package Command;

import State.Library;
import Visitors.Visitor;

/**
 * implements Command interface for the register command
 *
 * @author Yug Patel - ydp4388
 */
public class NewVisitor implements Command{

    private Library library;
    private String fName;
    private String lName;
    private String address;
    private String  pNumber;

    /**
     * constructor
     * @param firstName first name of the user that is being registered
     * @param lastName last name of the user that is being registered
     * @param address address of the user that is being registered
     * @param pNumber phone number of the user that is being registered
     * @param library library where the user is registering
     */
    public NewVisitor(String firstName, String lastName, String address, String pNumber, Library library){
        this.fName = firstName;
        this.lName = lastName;
        this.address = address;
        this.pNumber = pNumber;
        this.library = library;
    }

    /**
     * executes the register command through the library class
     */
    @Override
    public void execute() {
        library.registerVisitor(fName,lName,address,pNumber);
    }

}
