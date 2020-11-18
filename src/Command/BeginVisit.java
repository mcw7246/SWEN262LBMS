package Command;

import State.Library;

/**
 * Implements the Command interface
 * @author Mikayla Wishart - mcw7246
 */
public class BeginVisit implements Command{

    private Integer id;
    private Library library;

    /**
     * Constructor
     * @param ID ID of the user that is beginning their visit
     * @param library the library that the user is entering
     */
    public BeginVisit(Integer ID, Library library){
        this.id = ID;
        this.library = library;
    }

    /**
     * execute method that begins the visit for the user in the library class
     */
    @Override
    public void execute() {
        library.startVisit(id);
    }
}
