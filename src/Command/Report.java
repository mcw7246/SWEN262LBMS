package Command;

public class Report implements Command
{

  int days;
  public Report(){}
  public Report(int days){
    this.days = days;
  }
  @Override
  public void execute(){
  }
}
