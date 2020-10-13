package Command;

import State.Library;


public class EndVisit implements Command{

    private Integer id;
    private Library library;

    public EndVisit(Integer ID, Library library){
        this.id = ID;
        this.library = library;
    }

    @Override
    public void execute() {
        library.endVisit(id);
    }
}
