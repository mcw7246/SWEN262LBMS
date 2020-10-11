package Command;

public class AdvanceTime implements Command
{
  int numDays;
  int numHours;

  public AdvanceTime(int numDays){
    this.numDays = numDays;
  }

  public AdvanceTime(int numDays, int numHours){
    this.numDays = numDays;
    this.numHours = numHours;
  }


  @Override
  public void execute(){}
}
