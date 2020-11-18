package Command;

import State.Library;

/**
 * implements the Command interface for the pay fine command
 */
public class PayFine implements Command
{
  int visitorID;
  double amount;
  Library library;

  /**
   * constructor
   * @param library library that is being paid
   * @param visitorID visitor id for the user that owes money
   * @param amount amount that the user is paying
   */
  public PayFine(Library library, int visitorID, double amount){
    this.library = library;
    this.visitorID = visitorID;
    this.amount = amount;
  }

  /**
   * executes the payfine command through the library class
   */
  @Override
  public void execute(){
    library.payFine(visitorID, amount);
  }
}
