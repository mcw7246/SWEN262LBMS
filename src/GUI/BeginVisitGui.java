package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;


public class BeginVisitGui
{
  static String visitorID;

  public static GridPane beginVisit(){
    GridPane updatedGridPane = new GridPane();

    updatedGridPane.getChildren().clear();

    Button backToHome = new Button("Back to home");
    backToHome.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        MainGui.getMainPane();
      }
    });
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
        beginVisitAction();
      }
    });

    updatedGridPane.add(backToHome, 0, 0);
    updatedGridPane.add(userID, 1, 1);
    updatedGridPane.add(id, 1, 2);
    updatedGridPane.add(submit, 1, 3);

    updatedGridPane.setVgap(10);
    updatedGridPane.setHgap(10);

    return updatedGridPane;
  }

  public static void beginVisitAction(){

  }

}
