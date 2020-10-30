package GUI;

import javafx.scene.layout.GridPane;

public class ReturnBookGui
{
  GridPane gridPane;

  public ReturnBookGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane returnBookGrid(){
    GridPane updated = gridPane;

    updated.getChildren().clear();

    return updated;
  }
}
