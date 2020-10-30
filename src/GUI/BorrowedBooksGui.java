package GUI;

import Command.BorrowedBooks;
import State.Library;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BorrowedBooksGui
{
  GridPane gridPane;
  public BorrowedBooksGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane borrowedBooksGridPane(){
    GridPane updated = gridPane;

    updated.getChildren().clear();

    Button backToHome = new Button("Back to home");


    updated.add(backToHome, 0, 0);


    return updated;
  }
}
