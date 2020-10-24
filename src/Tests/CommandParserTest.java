package Tests;

import Books.Book;
import Books.BookStore;
import Client.Client;
import Command.Command;
import Command.CommandParser;
import State.Library;
import org.junit.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommandParserTest
{
  private Client client;
  private Library library;
  private BookStore bookStore;
  private CommandParser commandParser;


    private static final Book HUNGER_GAMES_TRILOGY = new Book("9780545387200", "The Hunger Games Trilogy","{Suzanne Collins}","Scholastic Inc.","2011-05-01","1000");
    private static final Book HUNGER_GAMES_AND_PHILOSOPHY = new Book("9781118206027", "The Hunger Games and PHilosophy","{George A. Dunn, Nicolas Michaud}", "John Wiley & Sons", "2012-01-26","272");
  private static final Book AUTOCAD_FOR_DUMMIES = new Book("97811182558919","AutoCAD For Dummies","{Bill Fane}","John Wiley & Sons","2016-05-02","544");
  private static final Book HUNGER_GAMES_TRIBUTE_GUIDE = new Book("9780545470070","The Hunger Games Tribute Guide","{Emily Seife}","Scholastic Inc.","2012-02-07","128");
    private List<Book> books = new ArrayList<>(4);


  @Before
  public void runBeforeTests(){
    try{
      client = new Client();
      library = new Library(client);
      bookStore = new BookStore(client);
      commandParser = new CommandParser(library, client,bookStore);
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

  @Test
  public void registerNewVisitor(){
    String request = "register,mikayla,wishart,16chspk,321;";
    String expected = "register,1000000001,2020/10/23;";

    commandParser.parseCommand(request);
    assertEquals("The messages are supposed to be the same", expected, commandParser.MESSAGE);
    assertEquals("The visitor list should not be 0", (Integer)1,library.getTotalRegistered());
  }

  @Test
  public void duplicateVisitor(){
    String request = "register,mikayla,wishart,16chspk,321;";
    String expected = "register,duplicate;";

    commandParser.parseCommand(request);
    commandParser.parseCommand(request);
    assertEquals("This message should tell you there is a duplicate visitor", expected, commandParser.MESSAGE);
    assertEquals("there should only be one visitor", (Integer)1, library.getTotalRegistered());
  }

  @Test
  public void beginVisitTest(){
    String request = "register,mikayla,wishart,16chspk,321;";
    commandParser.parseCommand(request);
    request = "arrive,1000000001;";
    commandParser.parseCommand(request);
    String expected = "arrive,1000000001,2020/10/23,08:00:00;";
    assertEquals("These should be the same",expected,commandParser.MESSAGE);
  }

  @Test
  public void duplicateBeginVisitTest(){
    String request = "register,mikayla,wishart,16chspk,321;";
    commandParser.parseCommand(request);

    request = "arrive,1000000001;";
    commandParser.parseCommand(request);
    commandParser.parseCommand(request);
    String expected = "arrive,duplicate;";
    assertEquals("you should not be able to arrive twice without leaving.",expected, commandParser.MESSAGE);
  }

  @Test
  public void invalidIdBeginVisitTest(){
    String request = "arrive,1000000001;";
    commandParser.parseCommand(request);
    String expected = "arrive,invalid-id;";

    assertEquals("You should not be able to arrive without a proper visitor id", expected, commandParser.MESSAGE);
  }


}
