package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * GUI for the EndVisit command
 *
 * @author Yug Patel - ydp4388
 */
public class EndVisitGui
{
  static String visitorID;

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
        visitorID = idTA.getText();
        updated.add(endVisitAction(), 1, 4);
      }
    });

    updated.add(visitorIDLabel, 1, 1);
    updated.add(idTA, 2, 1);
    updated.add(submit, 1, 2);

    updated.setHgap(10);
    updated.setVgap(10);

    return updated;
  }

  public static TextArea endVisitAction(){
    String result = "";
    String cmd = "depart," + visitorID + ";";
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
