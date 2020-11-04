import Client.Client;
import Command.CommandParser;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Mikayla Wishart - mcw7246
 */
public class main
{
  /**
   * Main method for the application. Takes in input from user
   * @param args
   */
  public static void main(String[] args) throws FileNotFoundException {

    Scanner in = new Scanner(System.in);

    System.out.println("---------- Welcome to State.Library Book Management System (LBMS) R1 ----------");

    Client client = new Client();
    CommandParser commandParser = client.getCommandParser();

    commandParser.parseCommand("register,first name,last name,address, phone-number;");
    commandParser.parseCommand("search,Harry Potter;");
    commandParser.parseCommand("buy,5,5;");
    commandParser.parseCommand("info,Harry Potter,Jody Revenson;");
    commandParser.parseCommand("borrow,1000000001,1;");
    commandParser.parseCommand("borrowed,1000000001;");
    commandParser.parseCommand("advance,7,0;");
    commandParser.parseCommand("advance,7,0;");
    commandParser.parseCommand("advance,1,0;");
    commandParser.parseCommand("return,1000000001,1;");
    //commandParser.parseCommand("advance,7,0;");
    //commandParser.parseCommand("advance,1,0;");
    String input = in.nextLine();
    while(!input.equals("exit")){
      if(input != ""){
        String inputCommand = input;
        while (input.charAt(input.length()-1) != ';'){
          input = in.nextLine();
          inputCommand += input;
        }
        commandParser.parseCommand(inputCommand);
      }
      input = in.nextLine();
    }
  }
}
