package GUI;

import javafx.scene.layout.GridPane;

public class BorrowBookGui
{
  GridPane gridPane;
  public BorrowBookGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane borrowBookGridPane(){
    GridPane updated = gridPane;

    updated.getChildren().clear();

    return updated;
  }
}
