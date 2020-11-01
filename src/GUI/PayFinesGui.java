package GUI;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PayFinesGui
{
  GridPane gridPane;

  public PayFinesGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane payFinesGrid(){
    GridPane updated = gridPane;

    updated.getChildren().clear();

    Button backToHome = new Button("Back to Home");

    return updated;
  }
}
