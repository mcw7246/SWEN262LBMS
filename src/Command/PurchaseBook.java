package Command;

import java.util.List;

public class PurchaseBook implements Command
{
  int quantity;
  int visitorID;
  List<String> bookIDs;

  public PurchaseBook(Integer quantity, int visitorID){
    this.quantity = quantity;
    this.visitorID = visitorID;
  }

  public PurchaseBook(int quantity, int visitorID, List<String> bookIDs){
    this.quantity = quantity;
    this.visitorID = visitorID;
    this.bookIDs = bookIDs;
  }

  @Override
  public void execute(){}
}
