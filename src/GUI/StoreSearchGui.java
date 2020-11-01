package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class StoreSearchGui
{
  GridPane gridPane;
  String name;
  String authors;
  String isbn;
  String publisher;
  String sortOrd;

  public StoreSearchGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane storeSearchGrid(){
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
    //gets the book title
    Label titleLabel = new Label("Book Title: ");
    TextArea bookName = new TextArea("*");

    //gets the authors
    Label authorLabel = new Label("Book Author(s) Comma Separated: ");
    TextArea bookAuthors = new TextArea("*");

    //gets isbn
    Label isbnLabel = new Label("Book ISBN: ");
    TextArea bookISBN = new TextArea("*");

    //gets publisher
    Label publisherLabel = new Label("Book Publisher: " );
    TextArea bookPublisher = new TextArea("*");

    //gets sort order
    Label sortOrderLabel = new Label("Sort Order: ");
    MenuButton sortOrder = new MenuButton("None");
    sortOrder.getItems().addAll(new MenuItem("Alphabetical"), new MenuItem("Publish date (newest - oldest)"), new MenuItem("Book Status"));

    Button search = new Button("Search");
    search.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        name = bookName.getText();
        isbn = bookISBN.getText();
        publisher = bookPublisher.getText();
        authors = bookAuthors.getText();
        sortOrd = sortOrder.getText();

      }
    });

    updated.add(backToHome, 0, 0);

    //adds title to the gridpane
    bookName.setPrefSize(200,20);
    updated.add(titleLabel, 0,1);
    updated.add(bookName, 1,1);

    //adds authors to the gridpane
    bookAuthors.setPrefSize(200, 20);
    updated.add(authorLabel, 0, 2);
    updated.add(bookAuthors, 1, 2);

    //adds isbn to the gridpane
    bookISBN.setPrefSize(200, 20);
    updated.add(isbnLabel,0,3);
    updated.add(bookISBN, 1, 3);

    //adds publisher to gridpane
    bookPublisher.setPrefSize(200,20);
    updated.add(publisherLabel, 0,4);
    updated.add(bookPublisher, 1, 4);

    //adds sortorder to gridpane
    updated.add(sortOrderLabel, 0,5);
    updated.add(sortOrder, 1, 5);

    updated.add(search, 0, 6);

    updated.setVgap(10);
    return updated;
  }
}
