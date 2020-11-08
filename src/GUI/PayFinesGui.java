package GUI;

import Visitors.PaidFine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class PayFinesGui
{
  static String dollars;
  static String id;

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
        id = visitorIdTA.getText();
        dollars = amountTA.getText();
        updated.add(PayFine(), 1, 4);
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

  public static TextArea PayFine(){
    String result = "";
    String cmd = "pay," + id + "," + dollars + ";";
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
