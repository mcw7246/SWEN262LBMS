package Command;

import Client.Client;

/**
 * implements the command that runs the report command
 * @author Mikayla Wishart - mcw7246
 * @author Yug Patel - ydp4388
 */
public class Report implements Command
{

  private int days = 0;
  private Client client;


  /**
   * constructor
   * @param client client that is requesting the report
   */
  public Report(Client client){
    this.client = client;
  }

  /**
   * constructor
   * @param days number of days to be included in the report
   * @param client client requesting the report
   */
  public Report(int days, Client client){
    this.days = days;
    this.client = client;
  }


  /**
   * executes the report command through the client class
   */
  @Override
  public void execute(){
    client.generateReport(days);
  }
}
