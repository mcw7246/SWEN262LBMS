package Command;

public class PayFine implements Command
{
  int visitorID;
  double amount;

  public PayFine(int visitorID, double amount){
    this.visitorID = visitorID;
    this.amount = amount;
  }
  @Override
  public void execute(){}
}
