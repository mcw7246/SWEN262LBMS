package Command;

import java.util.ArrayList;

/**
 * @author Mikayla Wishart - mcw7246
 */
public class CommandParser
{
  private ArrayList<Command> commands;

  /**
   * Parses the command that the user sent to the system
   *
   * @param givenCommand The command that the user sent the system
   */
  public void parseCommand(String givenCommand){
    ArrayList<String> cmd = new ArrayList<>();

    String arg = "";
    //tells if you need to parse through it or not
    boolean parsePiece = false;

    //loops through all of the characters in the given command
    for(char currentChar : givenCommand.toCharArray()){
      //if it is the beginning of authors, or the start of the
      if((currentChar == '{' || currentChar == '\"') && !parsePiece){
        parsePiece = true;
        continue;
      }else if((currentChar == '}'|| currentChar == '\"') && parsePiece){
        parsePiece = false;
        continue;
      }

      if(currentChar == ',' && !parsePiece){
        cmd.add(arg);
      }
      else if (currentChar == ';' && !parsePiece){
        cmd.add(arg);
        break;
      }
      else{
        arg += currentChar;
      }
    }
    //TODO: partial request or invalid arg length
  }

  public void createCommand(String cmd, ArrayList<String> args){

  }
}
