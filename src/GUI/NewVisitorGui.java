package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class NewVisitorGui
{
  GridPane gridPane;

  String fname;
  String lname;
  String pNumber;
  String address;
  public NewVisitorGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane newVisitorGrid(){
    GridPane updated = gridPane;

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

    Button backToHome = new Button("Back to Home");
    backToHome.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        MainGui.mainPage(gridPane);
      }
    });
    Button submit = new Button("Submit");
    submit.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        handleNewVisitor();
      }
    });

    updated.add(backToHome, 0, 0);

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

  public void handleNewVisitor(){

  }
}
