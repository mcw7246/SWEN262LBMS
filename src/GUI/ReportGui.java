package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;


public class ReportGui
{
  public static GridPane report(){
    GridPane updated = new GridPane();

    updated.getChildren().clear();

    Label numDays = new Label("Number of days: ");
    TextArea numDaysTA = new TextArea();
    numDaysTA.setPrefSize(200,20);

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

    updated.add(numDays, 1, 1);
    updated.add(numDaysTA, 2, 1);
    updated.add(submit, 1, 2);

    return updated;
  }
}
