package Command;

import Client.Client;

/**
 * implements the Command interface for the DateTime command
 * @author Mikayla Wishart - mcw7246
 */
public class DateTime implements Command {

  private Client client;

  /**
   * constructor
   * @param client client who inputted the command
   */
  public DateTime(Client client){
    this.client = client;
  }

  /**
   * executes the dateTime command through the client class
   */
  @Override
  public void execute(){
    client.setMessage("datetime," + client.getDateTime());
  }
}
