package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class AdvanceTimeGui
{

  public static GridPane advanceTime(){
    GridPane updatedGridPane = new GridPane();


    Label numDays = new Label("Number of days: ");
    TextArea numDaysTA = new TextArea();
    numDaysTA.setPrefSize(200, 20);

    Label numHours = new Label("Number of hours: ");
    TextArea numHoursTA = new TextArea();
    numHoursTA.setPrefSize(200, 20);

    Button submit = new Button("Submit");
    submit.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {

      }
    });

    updatedGridPane.setHgap(10);
    updatedGridPane.setVgap(10);

    updatedGridPane.add(numDays, 1, 1);
    updatedGridPane.add(numDaysTA, 2, 1);
    updatedGridPane.add(numHours, 1, 2);
    updatedGridPane.add(numHoursTA, 2, 2);

    updatedGridPane.add(submit, 1, 3);
    return updatedGridPane;
  }

  public void handleAdvanceTime(){

  }
}
