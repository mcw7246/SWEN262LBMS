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
import java.util.Date;
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
    books.add(HUNGER_GAMES_TRIBUTE_GUIDE);
    books.add(HUNGER_GAMES_AND_PHILOSOPHY);
    books.add(HUNGER_GAMES_TRILOGY);
    books.add(AUTOCAD_FOR_DUMMIES);
  }

  @Test
  public void registerNewVisitor(){
    String request = "register,mikayla,wishart,16chspk,321;";
    Date date = new Date();
    String expected = "register,1000000001,2020/10/24;";

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
    String expected = "arrive,1000000001,2020/10/24,08:00:00;";
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

  @Test
  public void endVisitTest(){
    String request = "register,mikayla,wishart,16chesp,321;";
    commandParser.parseCommand(request);

    request = "arrive,1000000001;";
    commandParser.parseCommand(request);

    request = "depart,1000000001;";
    commandParser.parseCommand(request);

    String expected = "depart,1000000001,2020/10/24,08:00:00;";
    assertEquals("These lines are supposed to be equal.",expected,commandParser.MESSAGE);
  }

  @Test
  public void endVisitInvalidIDTest(){
    String request = "register,mikayla,wishart,16chspk,321;";
    commandParser.parseCommand(request);

    request = "arrive,1000000001;";
    commandParser.parseCommand(request);

    request = "depart,1001;";
    commandParser.parseCommand(request);

    String expected = "depart,invalid-id;";
    assertEquals("These are supposed to be the same.", expected, commandParser.MESSAGE);
  }

  @Test
  public void bookStoreSearch(){
    String request = "search,\"Hunger Games\",{*};";
    commandParser.parseCommand(request);

    String expected = "info,7\n"
            + "1 - 9780545387200,The Hunger Games Trilogy,{Suzanne Collins},Scholastic Inc.,2011-05-01,1000\n"
            + "2 - 9780545452373,The World of the Hunger Games,{Kate Egan},Scholastic Inc.,2012-03-23,192\n"
            + "3 - 9780545227247,Catching Fire (The Second Book of the Hunger Games),{Suzanne Collins},Scholastic Inc.,2010-06-01,400\n" +
            "4 - 9780545470070,The Hunger Games Tribute Guide,{Emily Seife},Scholastic Inc.,2012-02-07,128\n" +
            "5 - 9781407139982,The Hunger Games Complete Trilogy,{Suzanne Collins},Scholastic UK,2013-10-03,1344\n" +
            "6 - 9780545229937,The Hunger Games,{Suzanne Collins},Scholastic Inc.,2009-09-01,384\n" +
            "7 - 9781118206027,The Hunger Games and Philosophy,{George A. Dunn, Nicolas Michaud},John Wiley & Sons,2012-01-26,272"

            ;
    assertEquals("these should match", expected, commandParser.MESSAGE);
  }

  @Test
  public void bookStoreSearchAllTitles(){
    String request = "search,\"*\",{Suzanne Collins};";

    commandParser.parseCommand(request);

    String expected = "info,4\n"
            + "1 - 9780545387200,The Hunger Games Trilogy,{Suzanne Collins},Scholastic Inc.,2011-05-01,1000\n"
            + "2 - 9780545227247,Catching Fire (The Second Book of the Hunger Games),{Suzanne Collins},Scholastic Inc.,2010-06-01,400\n"
            + "3 - 9781407139982,The Hunger Games Complete Trilogy,{Suzanne Collins}," +
            "Scholastic UK,2013-10-03,1344\n"
            + "4 - 9780545229937,The Hunger Games,{Suzanne Collins},Scholastic Inc.," +
            "2009-09-01,384";

    assertEquals("these need to match", expected, commandParser.MESSAGE);
  }

  @Test
  public void bookStoreSearchISBN(){
    String request = "search,\"*\",{Suzanne Collins},9780545387200;";

    commandParser.parseCommand(request);

    String expected = "info,1\n"
            + "1 - 9780545387200,The Hunger Games Trilogy,{Suzanne Collins},Scholastic Inc.,2011-05-01,1000";

    assertEquals("these need to match", expected, commandParser.MESSAGE);
  }

  @Test
  public void bookStoreSearchPublisher(){
    String request = "search,\"*\",{Suzanne Collins},9780545387200,Scholastic Inc.;";

    commandParser.parseCommand(request);

    String expected = "info,1\n"
            + "1 - 9780545387200,The Hunger Games Trilogy,{Suzanne Collins},Scholastic Inc.,2011-05-01,1000";

    assertEquals("these need to match", expected, commandParser.MESSAGE);
  }

  @Test
  public void bookSearchSortOrderAlphabetical(){
    String request = "search,\"*\",{Suzanne Collins},9780545387200,Scholastic Inc.,Alphabetical;";

    commandParser.parseCommand(request);

    String expected = "info,1\n"
            + "1 - 9780545387200,The Hunger Games Trilogy,{Suzanne Collins},Scholastic Inc.,2011-05-01,1000";

    assertEquals("these need to match", expected, commandParser.MESSAGE);
  }

  @Test
  public void buyBook(){
    String request = "search,\"*\",{Suzanne Collins};";
    commandParser.parseCommand(request);

    request = "buy,1,1;";
    commandParser.parseCommand(request);
    String expected = "buy,success,1\n"
            + "9780545387200,The Hunger Games Trilogy,{Suzanne Collins},2011-05-01,1";

    assertEquals("these should be the same",expected,commandParser.MESSAGE);
  }

  @Test
  public void borrowBookTest(){
    String request = "info,\"*\",{Suzanne Collins};";
    commandParser.parseCommand(request);

    request = "buy,2,1,3;";
    commandParser.parseCommand(request);

    request = "register,mikayla,wishart,16chspk,321;";
    commandParser.parseCommand(request);

    request = "borrow,1000000001,1;";
    commandParser.parseCommand(request);

    String expected = "borrow,2020/11/8;";
    assertEquals("these should be equal.",expected,client.getMessage());
  }

}
