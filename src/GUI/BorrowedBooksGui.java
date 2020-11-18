package GUI;

import Books.CheckOut;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * GUI for BorrowBook command
 *
 *
 */
public class BorrowedBooksGui
{

  public static String userID;
  public static GridPane updated;
  public static ObservableList<CheckOut> checkOuts;
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

    checkOuts = FXCollections.observableArrayList(MainGui.library.checkOutsByUserID.get(userID));

    int id = 1;
    for(CheckOut checkOut : checkOuts){
      checkOut.setIdNum(Integer.toString(id));
      id++;
    }

    TableColumn<CheckOut, String> idCol = new TableColumn<>("ID");
    idCol.setCellValueFactory(new PropertyValueFactory<>("idNum"));


    TableColumn<CheckOut, String> titleCol = new TableColumn<>("Title");
    titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

    TableColumn<CheckOut, Date> borrowDateCol = new TableColumn<>("Date Borrowed");
    borrowDateCol.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));

    TableColumn<CheckOut, Void> colBtn = new TableColumn("Button Column");

    Callback<TableColumn<CheckOut, Void>, TableCell<CheckOut, Void>> cellFactory = new Callback<TableColumn<CheckOut, Void>, TableCell<CheckOut, Void>>() {
      @Override
      public TableCell<CheckOut, Void> call(final TableColumn<CheckOut, Void> param) {
        final TableCell<CheckOut, Void> cell = new TableCell<CheckOut, Void>() {

          private final Button btn = new Button("Return Book");

          {
            btn.setOnAction((ActionEvent event) -> {
              handleReturn(this.getTableRow().getItem());
            });
          }

          @Override
          public void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
              setGraphic(null);
            } else {
              setGraphic(btn);
            }
          }
        };
        return cell;
      }
    };
    colBtn.setCellFactory(cellFactory);
    table.getColumns().addAll(idCol, titleCol, borrowDateCol, colBtn);
    table.setItems(checkOuts);

    updated.getChildren().add(table);
  }

  public static void handleReturn(CheckOut checkOut){
    String cmd = "return," + userID + "," + checkOut.getIdNum() + ";";
    MainGui.commandParser.parseCommand(cmd);
    int size = MainGui.commandParser.getMessage().size();
    if(MainGui.commandParser.getMessage().get(size - 1).equals("return,success;")){
      checkOuts.remove(checkOut);
    }
    else{
      System.out.println(MainGui.commandParser.getMessage());
    }

  }
}
