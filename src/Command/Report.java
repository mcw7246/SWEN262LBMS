package Command;

import Client.Client;

public class Report implements Command
{

  private int days = 0;
  private Client client;


  public Report(Client client){
    this.client = client;
  }
  public Report(int days, Client client){
    this.days = days;
    this.client = client;
  }


  @Override
  public void execute(){
    client.generateReport(days);
  }
}
