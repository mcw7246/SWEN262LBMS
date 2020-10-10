package Command;

public class BorrowedBooks implements Command
{
  int visitorID;
  public BorrowedBooks(int visitorID){
    this.visitorID = visitorID;
  }


  @Override
  public void execute(){

  }
}
