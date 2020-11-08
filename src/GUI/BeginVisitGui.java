package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class BeginVisitGui
{
  static String visitorID;

  public static GridPane beginVisit(){
    GridPane updatedGridPane = new GridPane();

    updatedGridPane.getChildren().clear();

    Label userID = new Label("Enter your Visitor ID: ");
    TextArea id = new TextArea("");
    id.setPrefSize(200,20);

    Button submit = new Button("Begin Visit!");
    submit.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        visitorID = id.getText();
        updatedGridPane.add(beginVisitAction(), 1, 4);
      }
    });

    updatedGridPane.add(userID, 1, 1);
    updatedGridPane.add(id, 1, 2);
    updatedGridPane.add(submit, 1, 3);

    updatedGridPane.setVgap(10);
    updatedGridPane.setHgap(10);

    return updatedGridPane;
  }

  public static TextArea beginVisitAction(){
    String result = "";
    String cmd = "arrive," + visitorID + ";";
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
