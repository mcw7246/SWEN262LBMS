package Command;

import State.Library;

/**
 * implements Command interface for the depart command
 */
public class EndVisit implements Command{

    private Integer id;
    private Library library;

    /**
     * constructor
     * @param ID id for the user that is leaving the library
     * @param library the library that the user is leaving from
     */
    public EndVisit(Integer ID, Library library){
        this.id = ID;
        this.library = library;
    }

    @Override
    public void execute() {
        library.endVisit(id);
    }
}
