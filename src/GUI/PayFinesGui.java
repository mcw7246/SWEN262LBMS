package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class PayFinesGui
{

  public static GridPane payFines(){
    GridPane updated = new GridPane();

    updated.getChildren().clear();

    Label visitorID = new Label("Visitor ID: ");
    TextArea visitorIdTA = new TextArea();
    visitorIdTA.setPrefSize(200, 20);

    Label amount = new Label("Amount: ");
    TextArea amountTA = new TextArea();
    amountTA.setPrefSize(200, 20);

    Button submit = new Button("Submit");
    submit.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {

      }
    });

    updated.setVgap(10);
    updated.setHgap(10);

    updated.add(visitorID, 1, 1);
    updated.add(visitorIdTA, 2, 1);
    updated.add(amount, 1, 2);
    updated.add(amountTA, 2, 2);
    updated.add(submit, 1, 3);

    return updated;
  }
}
