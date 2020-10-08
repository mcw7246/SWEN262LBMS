package Command;

import Visitors.Visitor;

public class NewVisitor implements Command{

    private String fName;
    private String lName;
    private String address;
    private Integer pNumber;

    public NewVisitor(String firstName, String lastName, String address, String pNumber){
        this.fName = firstName;
        this.lName = lastName;
        this.address = address;
        this.pNumber = Integer.getInteger(pNumber);
    }

    @Override
    public void execute() {

    }

}
