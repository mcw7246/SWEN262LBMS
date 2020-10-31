package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class AdvanceTimeGui
{
  GridPane gridPane;
  public AdvanceTimeGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane advanceTimeGridPane(){
    GridPane updatedGridPane = gridPane;
    gridPane.getChildren().clear();

    Button backToHome = new Button("Back to home");
    backToHome.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        MainGui.mainPage(updatedGridPane);
      }
    });

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

    updatedGridPane.add(backToHome, 0, 0);
    updatedGridPane.add(numDays, 0, 1);
    updatedGridPane.add(numDaysTA, 1, 1);
    updatedGridPane.add(numHours, 0, 2);
    updatedGridPane.add(numHoursTA, 1, 2);

    return updatedGridPane;
  }

  public void handleAdvanceTime(){

  }
}
