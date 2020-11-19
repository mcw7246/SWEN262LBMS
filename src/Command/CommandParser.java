package Command;

import Books.BookStore;
import Client.Client;
import State.Library;

import java.lang.reflect.Member;
import java.util.*;

/**
 * Parses through the commands that are given
 *
 * @author Mikayla Wishart - mcw7246
 * @author Yug Patel - ydp4388
 * @author Bryan Wang - jw3513
 */
public class CommandParser
{
  private Client client;
  private ArrayList<Command> allCommands;
  private Library library;
  private BookStore bookStore;

  public List<String> MESSAGE;

  /**
   * constructor for the CommandParser class
   *
   * @param library the library that is being used
   */
  public CommandParser(Library library, Client client, BookStore bookStore)
  {
    this.library = library;
    this.bookStore = bookStore;
    this.client = client;
    this.allCommands = new ArrayList<>();
    MESSAGE = new ArrayList<>();
  }

  /**
   * Parses the command that the user sent to the system
   *
   * @param givenCommand The command that the user sent the system
   */
  public void parseCommand(String givenCommand)
  {
    ArrayList<String> cmd = new ArrayList<>();

    String arg = "";

    boolean longCmd = false;

    //loops through all of the characters in the given command
    for (char current : givenCommand.toCharArray())
    {
      //if the char is a special char that signifies the start of an authors list or the title
      if ((current == '{' || current == '\"') && !longCmd)
      {
        longCmd = true;
        arg += "";
      }
      //special char that signifies the end of the authors list or the title
      else if ((current == '}' || current == '\"') && longCmd)
      {
        longCmd = false;
      }

      //if the current char is a separator between the types of information given
      if (!longCmd && current == ',')
      {
        cmd.add(arg);
        arg = "";
      }
      //if it is the end of the command
      else if (current == ';' && !longCmd)
      {
        cmd.add(arg);
        arg = "";
        break;
      }
      //if it is part of the authors list
      else if (current == '{' || current == '}')
      {
        arg += "";
      }
      //else add it to the arg string
      else
      {
        arg += current;
      }
    }
    String cmdType = cmd.get(0);

    cmd.remove(cmdType);
    ArrayList<String> paramGiven = cmd;

    createCommand(cmdType, paramGiven);
    //loop through all commands.
    for (Command command : allCommands)
    {
      command.execute();
    }
    //empty all commands.
    allCommands.clear();
    //loop through all response.
    for (String message : client.getMessage())
    {
      System.out.println(message);
      MESSAGE.add(message);
    }
    //empty all messages.
    client.getMessage().clear();
  }

  /**
   * Takes the type of command and the parameters that are given and creates a command
   *
   * @param cmd    command type
   * @param params params given
   */
  public void createCommand(String cmd, ArrayList<String> params)
  {
    Command command;
    switch (cmd)
    {
      //takes the parameters given and creates a NewVisitor command
      case "register":
        //makes sure there is the right number of parameters
        if (params.size() == 4)
        {
          String fName = params.get(0);
          String lName = params.get(1);
          String address = params.get(2);
          String phoneNum = params.get(3);

          //creates the new command and adds to the commands list
          boolean duplicate = library.existingVisitor(fName, lName, address, phoneNum);
          //if there is a duplicate user
          if (duplicate)
            errorDuplicateVisitor();
            //no duplicate user
          else
          {
            command = new NewVisitor(fName, lName, address, phoneNum, library);
            allCommands.add(command);
          }
        }
        //there are missing parameters
        else
        {
          missingParameters(cmd);
        }
        break;
        //takes the params given and creates a BeginVisit command
      case "arrive":
        //there is the right amount of parameters given
        if (params.size() == 1)
        {
          int id = Integer.parseInt(params.get(0));
          //if the id is invalid
          if (library.invalidID(id))
          {
            arriveInvalidID();
          }
          //valid id
          else
          {
            command = new BeginVisit(id, library);
            allCommands.add(command);
          }
        }
        else//missing parameters
        {
          missingParameters(cmd);
        }
        break;
        //creates a EndVisit command
      case "depart":
        //not enough parameters given
        if (params.size() == 1)
        {
          int id = Integer.parseInt(params.get(0));
          if (library.invalidID(id))
          {
            departInvalidID();
          }
          else
          {
            command = new EndVisit(id, library);
            allCommands.add(command);
          }
        }
        else
        {
          missingParameters(cmd);
        }
        break;
        //info command for library search
      case "info":
        //only given title and author(s)
        if (params.size() == 2)
        {
          String title = params.get(0);
          ArrayList<String> authors = new ArrayList<>(Arrays.asList(params.get(1).split(",")));
          command = new BookSearch(library, title, authors);
          allCommands.add(command);
        }
        else if (params.size() == 3)
        {
          String title = params.get(0);
          ArrayList<String> authors = new ArrayList<>(Arrays.asList(params.get(1).split(",")));
          String isbn = params.get(2);

          command = new BookSearch(library, title, authors, isbn);
          allCommands.add(command);
        }
        else if (params.size() == 4)
        {
          String title = params.get(0);
          ArrayList<String> authors = new ArrayList<>(Arrays.asList(params.get(1).split(",")));
          String isbn = params.get(2);
          String publisher = params.get(3);

          command = new BookSearch(library, title, authors, isbn, publisher);
          allCommands.add(command);
        }

        else if (params.size() == 5)
        {
          String title = params.get(0);
          ArrayList<String> authors = new ArrayList<>(Arrays.asList(params.get(1).split(",")));
          String isbn = params.get(2);
          String publisher = params.get(3);
          String sortOrder = params.get(4);

          command = new BookSearch(library, title, authors, isbn, publisher, sortOrder);
          allCommands.add(command);
        }
        break;
        //borrow command
      case "borrow":
        if (params.size() == 2)
        {
          int visitorID = Integer.parseInt(params.get(0));
          ArrayList<Integer> bookIDs =
                  new ArrayList<>(Arrays.asList(Integer.parseInt(params.get(1))));

          command = new BorrowBook(visitorID, bookIDs, library);
          allCommands.add(command);
        }
        //checked out command
      case "borrowed":
        if (params.size() == 1)
        {
          int id = Integer.parseInt(params.get(0));

          command = new FindBorrowedBooks(id, library);
          allCommands.add(command);
        }
        break;
        //return command
      case "return":
        if (params.size() == 2)
        {
          int id = Integer.parseInt(params.get(0));
          ArrayList<Integer> bookIds =
                  new ArrayList<>(Collections.singletonList(Integer.parseInt(params.get(1))));

          command = new ReturnBook(library, id, bookIds);
          allCommands.add(command);
        }
        break;
        //pay command
      case "pay":
        if (params.size() == 2)
        {
          int visitorID = Integer.parseInt(params.get(0));
          double amount = Double.parseDouble(params.get(1));

          command = new PayFine(library, visitorID, amount);
          allCommands.add(command);
        }
        break;
        //search book store command
      case "search":
        String title = "";
        List<String> authors = new ArrayList<>();
        String isbn = "*";
        String publisher = "*";
        String sortOrder = "*";
        if (params.size() == 1)
        {
          title = params.get(0);
          authors = Arrays.asList("*");
        }
        else if (params.size() == 2)
        {
          title = params.get(0);
          authors = Arrays.asList(params.get(1).split(","));
        }
        else if (params.size() == 3)
        {
          title = params.get(0);
          authors = Arrays.asList(params.get(1));
          isbn = params.get(2);

        }
        else if (params.size() == 4)
        {
          title = params.get(0);
          authors = Arrays.asList(params.get(1));
          isbn = params.get(2);
          publisher = params.get(3);
        }
        else if (params.size() == 5)
        {
          title = params.get(0);
          authors = Arrays.asList(params.get(1));
          isbn = params.get(2);
          publisher = params.get(3);
          sortOrder = params.get(4);
        }

        command = new StoreSearch(bookStore, title, authors, isbn, publisher, sortOrder);
        allCommands.add(command);
        break;
        //buy command
      case "buy":
        if (params.size() == 2)
        {
          int quantity = Integer.parseInt(params.get(0));
          ArrayList<Integer> bookIDs =
                  new ArrayList<>(Collections.singletonList(Integer.parseInt(params.get(1))));

          command = new PurchaseBook(quantity, bookIDs, library);
          allCommands.add(command);
        }
        break;

        //advance time command
      case "advance":
        if (params.size() == 1)
        {
          int days = Integer.parseInt(params.get(0));

          command = new AdvanceTime(days, client);
          allCommands.add(command);
        }
        else if (params.size() == 2)
        {
          int days = Integer.parseInt(params.get(0));
          int hours = Integer.parseInt(params.get(1));

          command = new AdvanceTime(days, hours, client);
          allCommands.add(command);
        }
        break;
        //date time command
      case "datetime":
        command = new DateTime(client);
        allCommands.add(command);
        break;
        //report command
      case "report":
        if (params.size() == 1)
        {
          int days = Integer.parseInt(params.get(0));
          ;

          command = new Report(days, client);
          allCommands.add(command);
        }
        else
        {
          command = new Report(client);
          allCommands.add(command);
        }
        break;
      default:
        System.out.println("Invalid Command");
        break;
    }
  }

  /**
   * Method to set the error message for depart feature.
   */
  private void departInvalidID()
  {
    client.setMessage("depart,invalid-id;");
  }

  /**
   * Method to set the error message for arrive feature.
   */
  public void arriveInvalidID()
  {
    client.setMessage("arrive,invalid-id;");
  }

  /**
   * Method to set the error message for register visitor feature.
   */
  public void errorDuplicateVisitor()
  {
    client.setMessage("register,duplicate;");
  }

  public void missingParameters(String cmdType)
  {
    if (cmdType.equals("arrive") || cmdType.equals("depart"))
    {
      System.err.print("You are missing the VisitorID for \"" + cmdType + "\" type of command.");
    }
  }

  public List<String> getMessage()
  {
    return MESSAGE;
  }


}
