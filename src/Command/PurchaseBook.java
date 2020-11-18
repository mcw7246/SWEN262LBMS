package Command;

import java.util.List;
import State.Library;

/**
 * implements the command interface for the buy command
 * @author Mikayla Wishart - mcw7246
 */
public class PurchaseBook implements Command
{
  private int quantity;

  private List<Integer> bookIDs;
  private Library library;


  /**
   * Constructor
   * @param quantity quantity of the amount of books to buy
   * @param bookIDs what books to buy
   * @param library where the books will be going
   */
  public PurchaseBook(int quantity,List<Integer> bookIDs, Library library){
    this.quantity = quantity;
    this.bookIDs = bookIDs;
    this.library = library;

  }

  /**
   * executes the buy command through the library class
   */
  @Override
  public void execute(){ library.purchaseBooks(quantity, bookIDs);}
}
