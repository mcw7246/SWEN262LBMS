package Command;

import State.Library;

public class PayFine implements Command
{
  int visitorID;
  double amount;
  Library library;

  public PayFine(Library library, int visitorID, double amount){
    this.library = library;
    this.visitorID = visitorID;
    this.amount = amount;
  }
  @Override
  public void execute(){
    library.payFine(visitorID, amount);
  }
}
