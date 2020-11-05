package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class ReturnBookGui
{
  public static GridPane returnBook(){
    GridPane updated = new GridPane();

    updated.getChildren().clear();

    Label visitorID = new Label("Visitor ID: ");
    TextArea visitorIdTA = new TextArea();
    visitorIdTA.setPrefSize(200, 20);

    Label bookIDs = new Label("Book ID's: ");
    TextArea bookIdsTA = new TextArea();
    bookIdsTA.setPrefSize(200,20);

    Button submit = new Button("Submit");
    submit.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {

      }
    });

    updated.add(visitorID, 1, 1);
    updated.add(visitorIdTA, 2, 1);
    updated.add(bookIDs, 1, 2);
    updated.add(bookIdsTA, 2, 2);
    updated.add(submit, 1, 3);



    return updated;
  }
}
