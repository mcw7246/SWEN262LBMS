package Command;

import java.util.List;
import State.Library;

public class PurchaseBook implements Command
{
  private int quantity;

  private List<Integer> bookIDs;
  private Library library;

  /*public PurchaseBook(Integer quantity, int visitorID){
    this.quantity = quantity;
    this.visitorID = visitorID;
  }*/

  public PurchaseBook(int quantity,List<Integer> bookIDs, Library library){
    this.quantity = quantity;
    this.bookIDs = bookIDs;
    this.library = library;

  }


  @Override
  public void execute(){ library.purchaseBooks(quantity, bookIDs);}
}