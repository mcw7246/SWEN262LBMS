package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;


public class ReportGui
{
  static String days;

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
        days = numDaysTA.getText();
        if(days == ""){
          days = "0";
        }
        updated.add(Report(), 1 , 4);
      }
    });

    updated.setVgap(10);
    updated.setHgap(10);

    updated.add(numDays, 1, 1);
    updated.add(numDaysTA, 2, 1);
    updated.add(submit, 1, 2);

    return updated;
  }

  public static TextArea Report(){
    String result = "";
    String cmd = "report," + days + ";";
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
