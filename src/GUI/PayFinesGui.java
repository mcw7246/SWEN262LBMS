package GUI;

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

    return updated;
  }
}
