package GUI;

import Command.BorrowedBooks;
import State.Library;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class BorrowedBooksGui
{
  GridPane gridPane;
  public BorrowedBooksGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane borrowedBooksGridPane(){
    GridPane updated = gridPane;

    updated.getChildren().clear();

    Button backToHome = new Button("Back to home");
    backToHome.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        MainGui.mainPage(gridPane);
      }
    });

    Label idLabel = new Label("Visitor ID: ");
    TextArea id = new TextArea();
    id.setPrefSize(200,20);

    Button submit = new Button("Submit");
    submit.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {

      }
    });

    updated.add(idLabel, 1, 1);
    updated.add(id, 1, 2);
    updated.add(backToHome, 0, 0);
    updated.add(submit, 1, 3);

    updated.setVgap(10);
    updated.setHgap(10);

    return updated;
  }
}
