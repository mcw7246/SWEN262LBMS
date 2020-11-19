package GUI;

import Books.BookStore;
import Client.Client;
import Command.CommandParser;
import State.Library;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * The GUI that is the home screen; Shows the options for each command that is allowed that is not
 * part of another command
 *
 * @author Mikayla Wishart - mcw7246
 */

public class MainGui extends Application
{
  static Label title;
  static BorderPane borderPane;
  static CommandParser commandParser;
  static Library library;
  static Client client;
  static BookStore bookStore;
  final static List<String> commandTypes = Arrays.asList("Advance Time", "Begin Visit", "Book Search", "Borrowed Books", "Date Time", "End Visit", "New Visitor", "Pay Fines", "Report", "Store Search");


  /**
   * @param primaryStage the main stage that
   */
  @Override
  public void start(Stage primaryStage)
  {
    try{
      client = new Client();
      library = new Library(client);
      bookStore = new BookStore(client);
    }catch(FileNotFoundException e){
      System.out.println(Arrays.toString(e.getStackTrace()));
    }

    commandParser = client.getCommandParser();
    title = new Label();
    //creates the BorderPane for the giu
    borderPane = new BorderPane();

    //creates the title
    title.setText("Welcome to the Library Book Management System");
    title.setFont(new Font("Lucida Sans Unicode", 26));
    borderPane.setTop(title);

    //creates the bottom
    Label release = new Label("Release 2 LBMS: Mikayla Wishart, Yug Patel, Ryan Tytka, Bryan Wang");
    release.setFont(new Font("Lucida Sans Unicode", 15));
    borderPane.setBottom(release);

    //gets and sets the center pane
    getMainPane();

    //sets the alignment for all panes that are used
    BorderPane.setAlignment(borderPane.getBottom(), Pos.CENTER);
    BorderPane.setAlignment(borderPane.getTop(), Pos.CENTER);
    BorderPane.setAlignment(borderPane.getCenter(), Pos.CENTER);

    //sets the scene, the title of the stage and shows it
    Scene scene = new Scene(borderPane, 700, 450);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Library Book Management System");
    primaryStage.show();
  }


  /**
   * the main pane for the home screen
   */
  public static void getMainPane(){
    FlowPane flowPane = new FlowPane();
    title.setText("Welcome to the Library Book Management System");

    //creates list of ImageView for all the command types
    ImageView[] pages = new ImageView[13];
    try
    {
      //loops through all the command types
      for (int i = 0; i < commandTypes.size(); i++)
      {
        int num = i + 1;
        //gets the image, puts it in an ImageView, sets the height and width and inserts it into pages
        Image image = new Image(new FileInputStream("src/Images/" + Integer.toString(num) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        pages[i] = imageView;

        String className = commandTypes.get(i).replace(" ", "");
        final String classNameFinal = className + "Gui";

        //creates a new button for the command type in the list, sets graphic, sets
        //where the graphic is displayed, sets the event handler, and the size
        Button button = new Button(commandTypes.get(i));
        button.setGraphic(pages[i]);
        button.setContentDisplay(ContentDisplay.TOP);
        button.setPrefSize(120, 120);
        button.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent actionEvent)
          {
            //clears the flowPane
            flowPane.getChildren().clear();
            //creates the side panel menu
            borderPane.setLeft(getTabs());
            borderPane.getLeft().maxWidth(50);
            //calls the class that is associated with the button
            borderPane.setCenter(buttonEvent(classNameFinal));
          }

        });
        //adds the button to the FlowPane
        flowPane.getChildren().add(button);
      }
    }
    catch(FileNotFoundException e){
      System.out.println(e.getMessage());
    }

    flowPane.setHgap(10);
    flowPane.setVgap(10);
    flowPane.setPadding(new Insets(20));

    borderPane.setCenter(flowPane);
  }

  /**
   * Calls the class that is associated with the button. Sets the top to
   * the associated class name
   *
   * @param buttonName - determines what type of command is being called
   * @return the GridPane for the specified command type
   */
  public static GridPane buttonEvent(String buttonName){
    borderPane.setCenter(null);
    switch (buttonName){
      case "AdvanceTimeGui":
        title.setText("Advance Time");
        borderPane.setTop(title);
        return AdvanceTimeGui.advanceTime();
      case "BeginVisitGui":
        title.setText("Begin Visit");
        borderPane.setTop(title);
        return BeginVisitGui.beginVisit();
      case "BookSearchGui":
        title.setText(("Library Book Search"));
        borderPane.setTop(title);
        return BookSearchGui.bookSearch();
      case "BorrowedBooksGui":
        title.setText("Borrowed Books");
        borderPane.setTop(title);
        return BorrowedBooksGui.borrowedBooks();
      case "DateTimeGui":
        title.setText("Date Time");
        borderPane.setTop(title);
        return DateTimeGui.dateTime();
      case "EndVisitGui":
        title.setText("End Visit");
        borderPane.setTop(title);
        return EndVisitGui.endVisit();
      case "NewVisitorGui":
        title.setText("New Visitor");
        borderPane.setTop(title);
        return NewVisitorGui.newVisitor(library);
      case "PayFinesGui":
        title.setText("Pay Fines");
        borderPane.setTop(title);
        return PayFinesGui.payFines();
      case "ReportGui":
        title.setText("Generate Report");
        borderPane.setTop(title);
        return ReportGui.report();
      case "StoreSearchGui":
        title.setText("Book Store Search");
        borderPane.setTop(title);
        return StoreSearchGui.storeSearch();
    }
    return null;
  }

  /**
   * creates the side panel menu that is inserted into the GUI when you are not in the home screen
   *
   * @return the VBox that contains all the buttons that contain the commands
   */
  public static VBox getTabs(){
    VBox box = new VBox();

    //creates the home button that brings you back to the home screen
    Button backToHome = new Button("Back to Home");
    backToHome.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        box.getChildren().clear();
        getMainPane();
      }
    });

    backToHome.setPrefSize(130,50);
    box.getChildren().add(backToHome);

    //loops through each command type and creates a button for the side pannel. inserts it into the panel
    for(int x = 0; x < commandTypes.size(); x++){
      Button button = new Button(commandTypes.get(x));

      button.setPrefSize(130, 50);

      String className = commandTypes.get(x).replace(" ", "") + "Gui";

      button.setOnAction(new EventHandler<ActionEvent>()
      {
        @Override
        public void handle(ActionEvent actionEvent)
        {
          //creates the action for the button that is chosen
          borderPane.setCenter(buttonEvent(className));
        }
      });
      box.getChildren().add(button);
    }
    return box;
  }


  public static void main(String[] args)
  {
    launch(args);
  }
}
