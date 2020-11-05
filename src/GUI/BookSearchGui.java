package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class BookSearchGui
{
  static GridPane gridPane;
  static String name;
  static String authors;
  static String isbn;
  static String publisher;
  static String sortOrd;

  public static GridPane bookSearch(){
    GridPane updatedGridPane = new GridPane();

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
        searchBookResults();
      }
    });
    //adds title to the gridpane
    bookName.setPrefSize(200,20);
    updatedGridPane.add(titleLabel, 1,1);
    updatedGridPane.add(bookName, 2,1);

    //adds authors to the gridpane
    bookAuthors.setPrefSize(200, 20);
    updatedGridPane.add(authorLabel, 1, 2);
    updatedGridPane.add(bookAuthors, 2, 2);

    //adds isbn to the gridpane
    bookISBN.setPrefSize(200, 20);
    updatedGridPane.add(isbnLabel,1,3);
    updatedGridPane.add(bookISBN, 2, 3);

    //adds publisher to gridpane
    bookPublisher.setPrefSize(200,20);
    updatedGridPane.add(publisherLabel, 1,4);
    updatedGridPane.add(bookPublisher, 2, 4);

    //adds sortorder to gridpane
    updatedGridPane.add(sortOrderLabel, 1,5);
    updatedGridPane.add(sortOrder, 2, 5);

    updatedGridPane.add(search, 1, 6);

    updatedGridPane.setVgap(10);
    updatedGridPane.setHgap(10);

    return updatedGridPane;
  }

  public static void searchBookResults(){

  }
}
