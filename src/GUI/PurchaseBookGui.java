package GUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import javax.xml.stream.util.XMLEventAllocator;

public class PurchaseBookGui
{
  GridPane gridPane;

  public PurchaseBookGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane purchaseBookGrid(){
    GridPane updated = gridPane;

    updated.getChildren().clear();

    Button backToHome = new Button("Back to Home");
    backToHome.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        MainGui.mainPage(updated);
      }
    });

    Label quantity = new Label("Book quantity: ");
    TextArea quantityTA = new TextArea();
    quantityTA.setPrefSize(200,20);

    Label id = new Label("Book ID's (Comma Separated): ");
    TextArea idTA = new TextArea();
    idTA.setPrefSize(200, 20);

    Button submit = new Button("Submit");
    submit.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {

      }
    });



    updated.add(backToHome, 0, 0);
    updated.add(quantity, 1, 1);
    updated.add(quantityTA, 2, 1);
    updated.add(id, 1, 2);
    updated.add(idTA, 2, 2);
    updated.add(submit, 1, 3);

    updated.setHgap(10);
    updated.setVgap(10);
    return updated;
  }
}
