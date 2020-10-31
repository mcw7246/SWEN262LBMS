package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;


public class BeginVisitGui
{
  GridPane gridPane;
  String visitorID;
  public BeginVisitGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane beginVisitGridPane(){
    GridPane updatedGridPane = gridPane;

    updatedGridPane.getChildren().clear();

    Button backToHome = new Button("Back to home");
    backToHome.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        MainGui.mainPage(gridPane);
      }
    });
    Label userID = new Label("Enter your Visitor ID: ");
    TextArea id = new TextArea("");

    Button submit = new Button("Begin Visit!");
    submit.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        visitorID = id.getText();
        beginVisitAction();
      }
    });

    updatedGridPane.add(backToHome, 0, 0);
    updatedGridPane.add(userID, 0, 1);
    updatedGridPane.add(id, 0, 2);
    updatedGridPane.add(submit, 0, 3);

    updatedGridPane.setVgap(10);

    return updatedGridPane;
  }

  public void beginVisitAction(){

  }

}
