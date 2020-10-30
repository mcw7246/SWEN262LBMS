package GUI;

import javafx.scene.layout.GridPane;

public class StoreSearchGui
{
  GridPane gridPane;

  public StoreSearchGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane storeSearchGrid(){
    GridPane updated = gridPane;

    updated.getChildren().clear();

    return updated;
  }
}
