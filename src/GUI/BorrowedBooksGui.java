package GUI;

import Books.Book;
import Books.CheckOut;
import com.sun.tools.javac.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.Date;
import java.util.List;

public class BorrowedBooksGui
{

  public static String userID;
  public static GridPane updated;
  public static GridPane borrowedBooks(){
    updated = new GridPane();

    updated.getChildren().clear();

    Label idLabel = new Label("Visitor ID: ");
    TextArea id = new TextArea();
    id.setPrefSize(200,20);

    Button submit = new Button("Submit");
    submit.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        userID = id.getText();
        handleFindBorrowedBooks();
      }
    });

    updated.add(idLabel, 1, 1);
    updated.add(id, 1, 2);

    updated.add(submit, 1, 3);

    updated.setVgap(10);
    updated.setHgap(10);

    return updated;
  }
  public static void handleFindBorrowedBooks(){
    updated.getChildren().clear();
    String cmd = "borrowed,"+ userID + ";";
    MainGui.commandParser.parseCommand(cmd);

    String wholeMessage = MainGui.commandParser.getMessage().get(0);
    String[] message = wholeMessage.split("\n");

    TableView<CheckOut> table = new TableView<>();

    TableColumn<CheckOut, String> idCol = new TableColumn<>("ID");
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

    TableColumn<CheckOut, String> titleCol = new TableColumn<>("Title");
    titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

    TableColumn<CheckOut, Date> borrowDateCol = new TableColumn<>("Date Borrowed");
    borrowDateCol.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));

    ObservableList<CheckOut> checkOuts = FXCollections.observableArrayList(MainGui.library.checkOutsByUserID.get(userID));

    table.getColumns().addAll( idCol, titleCol, borrowDateCol);
    table.setItems(checkOuts);

    updated.getChildren().add(table);
  }
}
