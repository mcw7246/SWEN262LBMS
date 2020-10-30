package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainGui extends Application
{

  @Override
  public void start(Stage primaryStage){

    Group root = new Group();


    GridPane gridPane = new GridPane();

    Label commandType = new Label("Choose which action you would like: " );
    MenuButton commandTypes = new MenuButton();

    MenuItem advanceTime = new MenuItem("Advance Time");
    MenuItem beginVisit = new MenuItem("Begin Visit");
    MenuItem bookSearch = new MenuItem("Library Book Search");
    bookSearch.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        BookSearchGui bookSearchGui = new BookSearchGui(gridPane);
        bookSearchGui.searchGridPane();
      }
    });
    MenuItem borrowBook = new MenuItem("Borrow Book");
    MenuItem borrowedBooks = new MenuItem("Borrowed Books");
    MenuItem dateTime = new MenuItem("Date Time");
    MenuItem endVisit = new MenuItem("End Visit");
    MenuItem newVisitor = new MenuItem("Register Visitor");
    MenuItem payFines = new MenuItem("Pay Fins");
    MenuItem purchaseBook = new MenuItem("Purchase Book");
    MenuItem report = new MenuItem("Generate Report");
    MenuItem returnBook = new MenuItem("Return Book");
    MenuItem storeSearch = new MenuItem("Store Search");

    commandTypes.getItems().addAll(advanceTime, beginVisit, bookSearch, borrowBook, borrowedBooks, dateTime, endVisit, newVisitor, payFines, purchaseBook, report, returnBook, storeSearch);

    commandTypes.show();

    gridPane.add(commandType, 0, 0);
    gridPane.add(commandTypes, 0,1);


    root.getChildren().add(gridPane);

    Scene scene = new Scene(root,600,300);


    primaryStage.setTitle("Library Book Management System");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args){
    launch(args);
  }
}
