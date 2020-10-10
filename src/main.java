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
