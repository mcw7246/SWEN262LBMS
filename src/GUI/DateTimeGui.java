package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * GUI for the DateTime Command
 *
 * @author Yug Patel - ydp4388
 */
public class DateTimeGui
{

  public static GridPane dateTime(){
    GridPane updated = new GridPane();

    updated.getChildren().clear();

    String result = "";
    String cmd = "datetime;";
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

    updated.add(ans, 1, 1);

    updated.setVgap(10);
    updated.setHgap(10);

    return updated;
  }
}
