package Command;

/**
 * @author Mikayla Wishart - mcw7246
 */
public class CommandParser
{
  public void parseCommand(String givenCommand){
    String[] command = givenCommand.split(",");


    //partial request
    if(!command[command.length - 1].endsWith(";")){
      do{
        System.out.println("Please finish your request with all necessary information and end it with a \";\"");
      }while(false);
    }
    //full request
    else{
      Command createdCommand;
      if(command[0].equals("register")){
        String firstName = command[1];
        String lastName = command[2];
        String address = command[3];
        String phoneNumber = command[4];

        createdCommand = new NewVisitor(firstName, lastName, address, phoneNumber);
      }
    }

  }
}
