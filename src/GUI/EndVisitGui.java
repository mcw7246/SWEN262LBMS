package GUI;

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

    return updated;
  }
}
