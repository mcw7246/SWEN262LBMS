package Command;

import State.Library;

public class BeginVisit implements Command{

    private Integer id;
    private Library library;

    public BeginVisit(Integer ID, Library library){
        this.id = ID;
        this.library = library;
    }


    @Override
    public void execute() {
        library.startVisit(id);
    }
}
