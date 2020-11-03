package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class StoreSearchGui
{
  static String name;
  static String authors;
  static String isbn;
  static String publisher;
  static String sortOrd;

  public static GridPane storeSearch(){
    GridPane updated = new GridPane();

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
    updated.add(titleLabel, 1,1);
    updated.add(bookName, 2, 1);

    //adds authors to the gridpane
    bookAuthors.setPrefSize(200, 20);
    updated.add(authorLabel, 1, 2);
    updated.add(bookAuthors, 2, 2);

    //adds isbn to the gridpane
    bookISBN.setPrefSize(200, 20);
    updated.add(isbnLabel,1,3);
    updated.add(bookISBN, 2, 3);

    //adds publisher to gridpane
    bookPublisher.setPrefSize(200,20);
    updated.add(publisherLabel, 1,4);
    updated.add(bookPublisher, 2, 4);

    //adds sortorder to gridpane
    updated.add(sortOrderLabel, 1,5);
    updated.add(sortOrder, 2, 5);

    updated.add(search, 1, 6);

    updated.setVgap(10);
    updated.setHgap(10);
    return updated;
  }
}
