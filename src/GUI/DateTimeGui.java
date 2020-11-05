package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class DateTimeGui
{

  public static GridPane dateTime(){
    GridPane updated = new GridPane();

    updated.getChildren().clear();


    updated.setVgap(10);
    updated.setHgap(10);

    return updated;
  }
}
