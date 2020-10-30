package GUI;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class BookSearchGui
{
  GridPane gridPane;
  public BookSearchGui(GridPane gridPane){
    this.gridPane = gridPane;
  }

  public GridPane searchGridPane(){
    GridPane updatedGridPane = gridPane;

    updatedGridPane.getChildren().clear();
    Button backToHome = new Button("Back to Home");


    //gets the book title
    Label titleLabel = new Label("Book Title: ");
    TextArea bookName = new TextArea("Enter book title here.");

    //gets the authors
    Label authorLabel = new Label("Book Author(s): " );
    TextArea bookAuthors = new TextArea("Enter book authors seperated by a comma.");

    //gets isbn
    Label isbnLabel = new Label("Book ISBN: ");
    TextArea bookISBN = new TextArea("Enter book ISBN.");

    //gets publisher
    Label publisherLabel = new Label("Book Publisher: " );
    TextArea bookPublisher = new TextArea("Enter book publisher");

    //gets sort order
    Label sortOrderLabel = new Label("Sort Order: ");
    MenuButton sortOrder = new MenuButton("None");
    sortOrder.getItems().addAll(new MenuItem("Alphabetical"), new MenuItem("Publish date (newest - oldest)"), new MenuItem("Book Status"));

    updatedGridPane.add(backToHome, 0, 0);

    //adds title to the gridpane
    bookName.setPrefSize(200,20);
    updatedGridPane.add(titleLabel, 0,1);
    updatedGridPane.add(bookName, 1,1);

    //adds authors to the gridpane
    bookAuthors.setPrefSize(200, 20);
    updatedGridPane.add(authorLabel, 0, 2);
    updatedGridPane.add(bookAuthors, 1, 2);

    //adds isbn to the gridpane
    bookISBN.setPrefSize(200, 20);
    updatedGridPane.add(isbnLabel,0,3);
    updatedGridPane.add(bookISBN, 1, 3);

    //adds publisher to gridpane
    bookPublisher.setPrefSize(200,20);
    updatedGridPane.add(publisherLabel, 0,4);
    updatedGridPane.add(bookPublisher, 1, 4);

    //adds sortorder to gridpane
    updatedGridPane.add(sortOrderLabel, 0,5);
    updatedGridPane.add(sortOrder, 1, 5);



    updatedGridPane.setVgap(10);

    return updatedGridPane;
  }

  public void searchBookResults(){

  }
}
