package GUI;

import javafx.scene.layout.GridPane;

public class DateTimeGui
{
  GridPane gridPane;

  public DateTimeGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane dateTimeGrid(){
    GridPane updated = gridPane;

    updated.getChildren().clear();

    return updated;
  }
}
