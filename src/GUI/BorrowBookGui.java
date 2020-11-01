package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class BorrowBookGui
{
  GridPane gridPane;
  public BorrowBookGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane borrowBookGridPane(){
    GridPane updated = gridPane;

    updated.getChildren().clear();

    Button backToHome = new Button("Back to Home");
    backToHome.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        MainGui.mainPage(updated);
      }
    });

    Label visitorID = new Label("Visitor ID: ");
    TextArea visitorIDTA = new TextArea();
    visitorID.setPrefSize(200, 20);

    Label bookIDs = new Label("Book ID's: ");
    TextArea bookIDsTA = new TextArea();
    bookIDsTA.setPrefSize(200,20);

    Button submit = new Button("Submit");
    submit.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {

      }
    });

    updated.setHgap(10);
    updated.setVgap(10);

    updated.add(backToHome, 0,0);
    updated.add(visitorID, 1, 1);
    updated.add(visitorIDTA, 2, 1);
    updated.add(bookIDs, 1, 2);
    updated.add(bookIDsTA, 2, 2);
    updated.add(submit, 1, 3);

    return updated;
  }
}
