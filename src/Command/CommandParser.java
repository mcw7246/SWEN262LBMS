package Command;

import State.Library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Mikayla Wishart - mcw7246
 */
public class CommandParser
{
  private ArrayList<Command> allCommands;
  private Library library;

  public CommandParser(Library library){
    this.library = library;
  }

  /**
   * Parses the command that the user sent to the system
   *
   * @param givenCommand The command that the user sent the system
   */
  public void parseCommand(String givenCommand){
    ArrayList<String> cmd = new ArrayList<>();

    String arg = "";
    //tells if you need to parse through it or not
    //when false, it is in the beginning or middle of the authors/title (do not parse through it)
    //when true, it is not inside a piece that should not be parsed
    boolean parsePiece = false;

    //loops through all of the characters in the given command
    for(char currentChar : givenCommand.toCharArray()){

      //if it is the beginning of authors, or the start of the
      //indicates it is at the beginning of something that should not be parsed
      if((currentChar == '{' || currentChar == '\"') && !parsePiece){
        parsePiece = true;
        continue;
      }else if((currentChar == '}'|| currentChar == '\"') && parsePiece){
        parsePiece = false;
        continue;
      }


      if(currentChar == ',' && !parsePiece){
        cmd.add(arg);
        arg = "";
      }
      else if (currentChar == ';' && !parsePiece){
        cmd.add(arg);
        arg = "";
        break;
      }
      else{
        arg += currentChar;
      }
    }

    String cmdType = cmd.get(0);

    cmd.remove(cmdType);
    ArrayList<String> paramGiven = cmd;

    createCommand(cmdType, paramGiven);
  }

  public void createCommand(String cmd, ArrayList<String> params){
    Command command;
    switch (cmd){
      case "register":
        //TODO: error(duplicate); need to create a method inside of visitor to make sure there is no duplicate people

        //makes sure there is the right number of parameters
        if(params.size() == 4){
          String fName = params.get(0);
          String lName = params.get(1);
          String address = params.get(2);
          String phoneNum = params.get(3);

          //creates the new command and adds to the commands list
          boolean duplicate = library.existingVisitor(fName, lName, address, phoneNum);
          //if there is a duplicate user
          if(duplicate)
            errorDuplicateVisitor();
          //no duplicate user
          else{
            command = new NewVisitor(fName, lName, address, phoneNum);

            allCommands.add(command);
          }

        }
        //there are missing parameters
        else{
          missingParameters(cmd);
        }
      case "arrive":
        if(params.size() == 1)
        {
          int id = Integer.parseInt(params.get(0));
          if (library.invalidID(id))
          {
            invalidID();
          }
          else
          {
            command = new BeginVisit(id);
            allCommands.add(command);
          }
        }
        else{
          missingParameters(cmd);
        }
      case "depart":
        if(params.size() == 1){
          int id = Integer.parseInt(params.get(0));
          if(library.invalidID(id)){
            invalidID();
          }
          else{
            command = new EndVisit(id);
            allCommands.add(command);
          }
        }
        else{
          missingParameters(cmd);
        }
      case "info":
        if(params.size() >= 3){

        }
    }


  }

  public void errorDuplicateVisitor(){

  }

  public void missingParameters(String cmdType){
    if(cmdType.equals("arrive") || cmdType.equals("depart")){
      System.err.print("You are missing the VisitorID for \"" + cmdType + "\" type of command.");
    }
  }
  public void invalidID(){}

}
