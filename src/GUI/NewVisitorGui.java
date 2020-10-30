package GUI;

import javafx.scene.layout.GridPane;

public class NewVisitorGui
{
  GridPane gridPane;

  public NewVisitorGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane newVisitorGrid(){
    GridPane updated = gridPane;

    updated.getChildren().clear();

    return updated;
  }
}
