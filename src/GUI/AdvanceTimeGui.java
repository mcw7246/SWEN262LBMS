package GUI;

import javafx.scene.layout.GridPane;

public class AdvanceTimeGui
{
  GridPane gridPane;
  public AdvanceTimeGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane advanceTimeGridPane(){
    GridPane updatedGridPane = gridPane;
    gridPane.getChildren().clear();


    return updatedGridPane;
  }
}
