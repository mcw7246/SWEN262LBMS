package GUI;

import javafx.scene.layout.GridPane;

public class ReportGui
{
  GridPane gridPane;

  public ReportGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane reportGrid(){
    GridPane updated = gridPane;

    updated.getChildren().clear();

    return updated;
  }
}
