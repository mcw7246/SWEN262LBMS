package GUI;

import Command.AdvanceTime;
import Command.NewVisitor;
import Command.StoreSearch;
import com.sun.javafx.fxml.builder.JavaFXSceneBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
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



public class MainGui extends Application
{
  static Label title;
  static BorderPane borderPane;
  final static List<String> commandTypes = Arrays.asList("Advance Time", "Begin Visit", "Book Search", "Borrowed Books", "Date Time", "End Visit", "New Visitor", "Pay Fines", "Report", "Store Search");
  @Override
  public void start(Stage primaryStage)
  {
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




  public static void getMainPane(){
    FlowPane flowPane = new FlowPane();
    title.setText("Welcome to the Library Book Management System");

    //creates the list of all commands
    //List<String> commandTypes = Arrays.asList("Advance Time", "Begin Visit", "Book Search", "Borrow Book", "Borrowed Books", "Date Time", "End Visit", "New Visitor", "Pay Fines", "Purchase Book", "Report", "Return Book", "Store Search");

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
            flowPane.getChildren().clear();
            borderPane.setLeft(getTabs());
            borderPane.getLeft().maxWidth(50);
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
    //return flowPane;
  }

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
        return NewVisitorGui.newVisitor();
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

  public static VBox getTabs(){
    VBox box = new VBox();


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

    backToHome.setPrefSize(150,50);
    box.getChildren().add(backToHome);
    for(int x = 0; x < commandTypes.size(); x++){
      Button button = new Button(commandTypes.get(x));

      button.setPrefSize(150, 50);

      String className = commandTypes.get(x).replace(" ", "") + "Gui";

      button.setOnAction(new EventHandler<ActionEvent>()
      {
        @Override
        public void handle(ActionEvent actionEvent)
        {
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
