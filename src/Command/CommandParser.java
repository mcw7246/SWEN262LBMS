package Command;

import Books.BookStore;
import Client.Client;
import State.Library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Calendar;

/**
 * Parses through the commands that are given
 * @author Mikayla Wishart - mcw7246
 */
public class CommandParser
{
  private Client client;
  private ArrayList<Command> allCommands;
  private Library library;
  private BookStore bookStore;
  private Calendar checkInDate;
  private Calendar checkOutDate;
  private Calendar calendar;
  private Calendar timeBorrow;

  /**
   * constructor for the CommandParser class
   * @param library the library that is being used
   */
  public CommandParser(Library library, Client client, BookStore bookStore){
    this.library = library;
    this.bookStore = bookStore;
    this.client = client;
    this.allCommands = new ArrayList<>();

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
    for(Command command:allCommands){
      command.execute();
    }
    allCommands.clear();
    System.out.println(client.getMessage());
  }

  public void createCommand(String cmd, ArrayList<String> params){
    Command command;
    switch (cmd){
      case "register":
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
            command = new NewVisitor(fName, lName, address, phoneNum, library);

            allCommands.add(command);
          }

        }
        //there are missing parameters
        else{
          missingParameters(cmd);
        }
        break;
      case "arrive":
        if(params.size() == 1)
        {
          int id = Integer.parseInt(params.get(0));
          if (library.invalidID(id))
          {
            arriveInvalidID();
          }
          else
          {
            command = new BeginVisit(id, library);
            allCommands.add(command);
          }
        }
        else{
          missingParameters(cmd);
        }
        break;
      case "depart":
        if(params.size() == 1){
          int id = Integer.parseInt(params.get(0));
          if(library.invalidID(id)){
            departInvalidID();
          }
          else{
            command = new EndVisit(id, library);
            allCommands.add(command);
          }
        }
        else{
          missingParameters(cmd);
        }
        break;
      case "info":
        //only given title and author(s)

        if(params.size() == 3){
          String title = params.get(0);
          ArrayList<String> authors = new ArrayList<>(Arrays.asList(params.get(1).split(",")));
          command = new BookSearch(library, title, authors);
          allCommands.add(command);
        }
        else if(params.size() == 4){
          String title = params.get(0);
          ArrayList<String> authors = new ArrayList<>(Arrays.asList(params.get(1).split(",")));
          String isbn = params.get(2);

          command = new BookSearch(library, title, authors, isbn);
          allCommands.add(command);
        }
        else if(params.size() == 5){
          String title = params.get(0);
          ArrayList<String> authors = new ArrayList<>(Arrays.asList(params.get(1).split(",")));
          String isbn = params.get(2);
          String publisher = params.get(3);

          command = new BookSearch(library, title, authors, isbn, publisher);
          allCommands.add(command);
        }

        else if(params.size() == 6){
          String title = params.get(0);
          ArrayList<String> authors = new ArrayList<>(Arrays.asList(params.get(1).split(",")));
          String isbn = params.get(2);
          String publisher = params.get(3);
          String sortOrder = params.get(4);

          command = new BookSearch(library, title, authors, isbn, publisher, sortOrder);
          allCommands.add(command);
        }
        break;
      case "borrow":
        if (params.size() == 2){
          int visitorID = Integer.parseInt(params.get(0));
          ArrayList<Integer> bookIDs = new ArrayList<>(Arrays.asList(Integer.parseInt(params.get(1))));

          command = new BorrowBook(visitorID, bookIDs, library, timeBorrow);
          allCommands.add(command);
        }
      case "borrowed":
        if(params.size() == 2){
          int visitorID = Integer.parseInt(params.get(0));
          ArrayList<Integer> bookIDs = new ArrayList<>(Arrays.asList(Integer.parseInt(params.get(1))));

          command = new BorrowedBooks(visitorID, bookIDs, library, checkInDate, checkOutDate);
          allCommands.add(command);
        }
        break;
      case "return":
        if(params.size() == 2){
          int id = Integer.parseInt(params.get(0));
          List<String> bookIds = Arrays.asList(params.get(1).split(","));

          command = new ReturnBook(id, bookIds);
        }
        break;
      case "pay":
        if(params.size() == 2){
          int visitorID = Integer.parseInt(params.get(0));
          double amount = Double.parseDouble(params.get(1));

          command = new PayFine(visitorID, amount);
        }
        break;
      case "search":
        String title = "";
        List<String> authors;
        String isbn;
        String publisher;
        String sortOrder;
        if(params.size() == 2){
          title = params.get(0);
          authors = Arrays.asList(params.get(1).split(","));

          command = new StoreSearch(title, authors);
          allCommands.add(command);
        }
        else if(params.size() == 3){
          title = params.get(0);
          authors = Arrays.asList(params.get(1));
          isbn = params.get(2);

          command = new StoreSearch(title, authors, isbn);
          allCommands.add(command);
        }
        else if(params.size() == 4){
          title = params.get(0);
          authors = Arrays.asList(params.get(1));
          isbn = params.get(2);
          publisher = params.get(3);

          command = new StoreSearch(title, authors, isbn, publisher);
          allCommands.add(command);
        }
        else if(params.size() == 5){
          title = params.get(0);
          authors = Arrays.asList(params.get(1));
          isbn = params.get(2);
          publisher = params.get(3);
          sortOrder = params.get(4);

          command = new StoreSearch(title, authors, isbn, publisher, sortOrder);
          allCommands.add(command);
        }
        break;
      case "buy":
        if(params.size() == 2){
          int quantity = Integer.parseInt(params.get(0));
          int visitorID = Integer.parseInt(params.get(1));

          command = new PurchaseBook(quantity, visitorID);
          allCommands.add(command);
        }
        else if(params.size() == 3){
          int quantity = Integer.parseInt(params.get(0));
          int visitorID = Integer.parseInt(params.get(1));
          //List<Integer> bookIDs = Arrays.asList(params.get(2));
          ArrayList<Integer> bookIDs = new ArrayList<>(Arrays.asList(Integer.parseInt(params.get(2))));


          command = new PurchaseBook(quantity, visitorID, bookIDs, library);
          allCommands.add(command);
        }
        break;

      case "advance":
        if(params.size() == 1){
          int days = Integer.parseInt(params.get(0));

          command = new AdvanceTime(days, client);
          allCommands.add(command);
        }
        else if(params.size() == 2){
          int days = Integer.parseInt(params.get(0));
          int hours = Integer.parseInt(params.get(1));

          command = new AdvanceTime(days, hours, client);
          allCommands.add(command);
        }
        break;
      case "datetime":
        command = new DateTime(client);
        allCommands.add(command);
        break;
      case "report":
        break;
    }


  }

  private void departInvalidID() {
    client.setMessage("depart,invalid-id;");
  }
  public void arriveInvalidID(){
    client.setMessage("arrive,invalid-id;");
  }
  public void errorDuplicateVisitor(){
    client.setMessage("register,duplicate;");
  }

  public void missingParameters(String cmdType){
    if(cmdType.equals("arrive") || cmdType.equals("depart")){
      System.err.print("You are missing the VisitorID for \"" + cmdType + "\" type of command.");
    }
  }


}
