package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class EndVisitGui
{
  public static GridPane endVisit()
  {
    GridPane updated = new GridPane();

    updated.getChildren().clear();

    Label visitorIDLabel= new Label("Visitor ID: ");
    TextArea idTA = new TextArea();
    idTA.setPrefSize(200,20);

    Button submit = new Button("Submit");
    submit.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {

      }
    });

    updated.add(visitorIDLabel, 1, 1);
    updated.add(idTA, 2, 1);
    updated.add(submit, 1, 2);

    updated.setHgap(10);
    updated.setVgap(10);

    return updated;
  }
}
