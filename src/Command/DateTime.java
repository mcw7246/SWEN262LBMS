package Command;

import Client.Client;

public class DateTime implements Command {

  private Client client;

  public DateTime(Client client){
    this.client = client;
  }


  @Override
  public void execute(){
    client.setMessage("datetime," + client.getDateTime());
  }
}
