package GUI;

import Books.Book;
import Books.BookStore;
import Command.CommandParser;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.Collection;

public class StoreSearchGui
{
  static String name;
  static String authors;
  static String isbn;
  static String publisher;
  static String sortOrd;
  static GridPane updated = new GridPane();
  public static GridPane storeSearch(){


    updated.getChildren().clear();

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

        MainGui.borderPane.setCenter(searchResults());
      }
    });
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

  public static GridPane searchResults(){
    String cmd = "search,\"" + name + "\",{" + authors + "}," + isbn + "," + publisher + "," + sortOrd + ";";
    MainGui.commandParser.parseCommand(cmd);

    updated.getChildren().clear();

    ObservableList<Book> books = FXCollections.observableArrayList();
    TableView<Book> table = new TableView<>();

    String wholeMessage = MainGui.commandParser.getMessage().get(0);
    //remove message after each access.
    MainGui.commandParser.getMessage().clear();


    TableColumn<Book, Boolean> checkBox = new TableColumn<>("");
    checkBox.setPrefWidth(25);
    checkBox.setEditable(true);

    TableColumn<Book, Integer> qty = new TableColumn<>("Qty");
    qty.setPrefWidth(50);



    TableColumn<Book,Integer> idNum = new TableColumn<>("ID Number");
    idNum.setCellValueFactory(new PropertyValueFactory<>("idNum"));
    idNum.setPrefWidth(50);

    TableColumn<Book, String> isbn = new TableColumn<>("ISBN");
    isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
    isbn.setPrefWidth(100);

    TableColumn<Book, String> title = new TableColumn<>("Book Title");
    title.setCellValueFactory(new PropertyValueFactory<>("title"));
    title.setPrefWidth(100);

    TableColumn<Book, String> authors = new TableColumn<>("Authors");
    authors.setCellValueFactory(new PropertyValueFactory<>("author"));
    authors.setPrefWidth(100);

    TableColumn<Book, String> publisher = new TableColumn<>("Publisher");
    publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
    publisher.setPrefWidth(100);

    TableColumn<Book, String> publishDate = new TableColumn<>("Publish Date");
    publishDate.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
    publishDate.setPrefWidth(100);

    table.getColumns().addAll(checkBox, qty, idNum, isbn, title, authors, publisher, publishDate);
    String[] message = wholeMessage.split("\n");

    for(int x = 1; x < message.length; x++)
    {
      String[] args = message[x].split(" - ");
      String id = args[0];
      String[] restArgs = args[1].split(",");

      ComboBox<Integer> num = new ComboBox<>();

      String isbnArg = restArgs[0];
      String titleArg = restArgs[1];
      String authorArgs = restArgs[2];
      String publisherArgs = restArgs[3];
      String pubDateArgs = restArgs[4];

      Book currentBook = new Book(Integer.parseInt(id), isbnArg, titleArg, authorArgs, publisherArgs, pubDateArgs);
      books.add(currentBook);

      checkBox.setCellFactory(CheckBoxTableCell.forTableColumn(checkBox));
    }



    table.setItems(books);

    updated.getChildren().add(table);


    return updated;
  }
}
