package GUI;

import Command.AdvanceTime;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * gui for Advance Time
 * @author Mikayla Wishart - mcw7246
 * @author Yug Patel - ydp4388
 */
public class AdvanceTimeGui
{
  static String days;
  static String hours;

  /**
   * creates the gridpane specific for advance time
   * @return gridpane for advance time
   */
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
        days = numDaysTA.getText();
        hours = numHoursTA.getText();
        if(days == ""){
          days = "0";
        }
        if (hours == "") {
          hours = "0";
        }
        updatedGridPane.add(AdvanceTime(), 1, 5);
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

  /**
   * handles the advance time results gui
   * @return textarea that holds the results
   */
  public static TextArea AdvanceTime(){
    String result = "";
    String cmd = "advance," + days + "," + hours + ";";
    MainGui.commandParser.parseCommand(cmd);
    for(String str: MainGui.commandParser.getMessage()){
      if(result != ""){
        result += "\n";
      }
      result += str;
    }
    MainGui.commandParser.getMessage().clear();
    String[] lines = result.split("\r\n|\r|\n");
    TextArea ans = new TextArea(result);
    ans.setEditable(false);
    ans.setStyle("-fx-font-size: 1.2em;");
    ans.setMaxWidth(400);
    ans.setPrefSize(300, 40 * lines.length);
    GridPane.setColumnSpan(ans, 2);
    GridPane.setRowSpan(ans, 2);
    return ans;
  }
}
