package GUI;

import Command.Command;
import Command.*;
import State.Library;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class NewVisitorGui
{
  static String fName;
  static String lName;
  static String pNumber;
  static String address;
  protected Library library;
  public static GridPane newVisitor(Library library){
    GridPane updated = new GridPane();

    updated.getChildren().clear();

    Label fNameLabel = new Label("First Name: ");
    TextArea fNameTA = new TextArea();
    fNameTA.setPrefSize(200, 20);

    Label lNameLabel = new Label("Last Name: " );
    TextArea lNameTA = new TextArea();
    lNameTA.setPrefSize(200, 20);

    Label addressLabel = new Label("Address: ");
    TextArea addressTA = new TextArea();
    addressTA.setPrefSize(200, 20);

    Label phoneNumLabel = new Label("Phone Number: ");
    TextArea phoneNumTA = new TextArea();
    phoneNumTA.setPrefSize(200, 20);

    updated.setVgap(10);
    updated.setHgap(10);

    Button submit = new Button("Submit");
    submit.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        fName = fNameTA.getText();
        lName = lNameTA.getText();
        address = addressTA.getText();
        pNumber = phoneNumTA.getText();
        updated.add(handleNewVisitor(), 2, 7);
      }
    });

    updated.add(fNameLabel, 1, 1);
    updated.add(fNameTA, 2, 1);

    updated.add(lNameLabel, 1, 2);
    updated.add(lNameTA, 2, 2);

    updated.add(addressLabel, 1, 3);
    updated.add(addressTA, 2, 3);

    updated.add(phoneNumLabel, 1, 4);
    updated.add(phoneNumTA, 2, 4);

    updated.add(submit, 1, 5);

    return updated;
  }

  public static TextArea handleNewVisitor(){
    String result = "";
    if(!fName.equals("") && !lName.equals("") && !address.equals("") && !pNumber.equals("")){
      String cmd = "register," + fName + "," + lName + "," + address + "," + pNumber + ";";
      MainGui.commandParser.parseCommand(cmd);
      for(String str: MainGui.commandParser.getMessage()){
        if(result != ""){
          result += "\n";
        }
        result += str;
      }
      MainGui.commandParser.getMessage().clear();
    }
    else {
      result += "Invalid Field/s";
    }
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
