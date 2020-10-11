package Command;

import Client.Client;

public class AdvanceTime implements Command
{
  private int numDays;
  private int numHours;
  private Client client;


  public AdvanceTime(int numDays, Client client){
    this.numDays = numDays;
    this.numHours = 0;
    this.client = client;
  }

  public AdvanceTime(int numDays, int numHours, Client client){
    this.numDays = numDays;
    this.numHours = numHours;
    this.client = client;
  }


  @Override
  public void execute(){
    client.advanceTime(numDays, numHours);
  }
}
