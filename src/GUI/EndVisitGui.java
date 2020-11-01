package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class EndVisitGui
{
  GridPane gridPane;

  public EndVisitGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane endVisitGrid()
  {
    GridPane updated = gridPane;

    updated.getChildren().clear();

    Button backToHome = new Button("Back to Home");
    backToHome.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {

      }
    });

    Label visitorIDLabel= new Label("Visitor ID: ");
    TextArea idTA = new TextArea();
    idTA.setPrefSize(200,20);

    updated.add(backToHome, 0,0);
    updated.add(visitorIDLabel, 1, 1);
    updated.add(idTA, 1, 2);

    updated.setHgap(10);
    updated.setVgap(10);

    return updated;
  }
}
