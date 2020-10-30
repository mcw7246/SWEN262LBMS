package GUI;

import javafx.scene.layout.GridPane;

public class BeginVisitGui
{
  GridPane gridPane;
  public BeginVisitGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane beginVisitGridPane(){
    GridPane updatedGridPane = gridPane;

    updatedGridPane.getChildren().clear();

    return updatedGridPane;
  }

}
