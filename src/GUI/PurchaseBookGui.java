package GUI;

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

    return updated;
  }
}
