package Command;

import Client.Client;

/**
 * AdvanceTime command that implements the Command class
 * @author Mikayla Wishart - mcw7246
 * @author Yug Patel - ydp4388
 */
public class AdvanceTime implements Command
{
  private int numDays;
  private int numHours;
  private Client client;


  /**
   * Constructor
   * @param numDays number of days to advance by
   * @param client the client who is requesting advance time
   */
  public AdvanceTime(int numDays, Client client){
    this.numDays = numDays;
    this.numHours = 0;
    this.client = client;
  }

  /**
   * Constructor
   * @param numDays number of days to advance by
   * @param numHours number of hours to advance by
   * @param client client who is requesting advance time
   */
  public AdvanceTime(int numDays, int numHours, Client client){
    this.numDays = numDays;
    this.numHours = numHours;
    this.client = client;
  }


  /**
   * Execute method that directs the command to the client class
   */
  @Override
  public void execute(){
    client.advanceTime(numDays, numHours);
  }
}
