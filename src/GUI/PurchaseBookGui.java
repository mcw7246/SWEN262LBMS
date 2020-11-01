package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PurchaseBookGui
{
  GridPane gridPane;

  public PurchaseBookGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane purchaseBookGrid(){
    GridPane updated = gridPane;

    updated.getChildren().clear();

    Button backToHome = new Button("Back to Home");
    backToHome.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        MainGui.mainPage(updated);
      }
    });

    return updated;
  }
}
