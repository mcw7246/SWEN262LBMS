package GUI;

import Books.Book;
import Books.BookStore;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;

/**
 * GUI for the BookSearch command
 *
 * @author Mikayla Wishart - mcw7246
 */
public class BookSearchGui
{
  static String name;
  static String authors;
  static String isbn;
  static String publisher;
  static String sortOrd;
  static GridPane updated = new GridPane();
  static Label resultsLabel;

  /**
   * gridpane for the search of books
   * @return gridpane specifically for the beginning of the search
   */
  public static GridPane bookSearch(){
    updated.getChildren().clear();
    resultsLabel = new Label();

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
        //sets the variables to what the user has inputed
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
    updated.add(bookName, 2,1);

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

  /**
   * creates the new GridPane for the results of the search
   * @return GridPane for the results
   */
  public static GridPane searchResults(){
    //creates the command and puts it through the command parser
    String cmd = "info,\"" + name + "\",{" + authors + "}," + isbn + "," + publisher + "," + sortOrd + ";";
    MainGui.commandParser.parseCommand(cmd);

    //clears the gui so that it is a fresh one for the results of the search
    updated.getChildren().clear();

    //creates the list of books that are the results of the search
    ObservableList<Book> books = FXCollections.observableArrayList();
    TableView<Book> table = new TableView<>();

    //gets the response from the commandParser
    String wholeMessage = MainGui.commandParser.getMessage().get(0);

    //remove message after each access.
    MainGui.commandParser.getMessage().clear();
    table.setEditable(true);

    //creates the checkboxes that allows you to select what books you would like to purchase
    TableColumn<Book, Boolean> checkBox = new TableColumn<>("");
    checkBox.setPrefWidth(25);
    checkBox.setCellValueFactory(new PropertyValueFactory<Book, Boolean>("checked"));
    checkBox.setCellFactory(new Callback<TableColumn<Book, Boolean>, TableCell<Book, Boolean>>()
    {
      @Override
      public TableCell<Book, Boolean> call(TableColumn<Book, Boolean> bookBooleanTableColumn)
      {
        return new StoreSearchGui.CheckBoxTableCell<Book, Boolean>();
      }

    });
    checkBox.setEditable(true);

    //displays the ID number for the books
    TableColumn<Book,Integer> idNum = new TableColumn<>("ID Number");
    idNum.setCellValueFactory(new PropertyValueFactory<>("idNum"));
    idNum.setPrefWidth(50);

    //displays the isbn of the books
    TableColumn<Book, String> isbn = new TableColumn<>("ISBN");
    isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
    isbn.setPrefWidth(100);

    //displays the title of the books
    TableColumn<Book, String> title = new TableColumn<>("Book Title");
    title.setCellValueFactory(new PropertyValueFactory<>("title"));
    title.setPrefWidth(100);

    //displays the author(s) of the books
    TableColumn<Book, String> authors = new TableColumn<>("Authors");
    authors.setCellValueFactory(new PropertyValueFactory<>("author"));
    authors.setPrefWidth(100);

    //displays the publisher of the book
    TableColumn<Book, String> publisher = new TableColumn<>("Publisher");
    publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
    publisher.setPrefWidth(100);

    //displays the publish date of the book
    TableColumn<Book, String> publishDate = new TableColumn<>("Publish Date");
    publishDate.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
    publishDate.setPrefWidth(100);

    //inserts all the columns into the TableView
    table.getColumns().addAll(checkBox, idNum, isbn, title, authors, publisher, publishDate);
    String[] message = wholeMessage.split("\n");

    //loops through and inserts every book that is part of search results
    //to the table
    for(int x = 1; x < message.length; x++)
    {
      String[] args = message[x].split(" - ");
      String id = args[0];
      String[] restArgs = args[1].split(",");

      String isbnArg = restArgs[0];

      Book existingBook = BookStore.bookByIsbn.get(isbnArg);
      existingBook.setIdNum(Integer.parseInt(id));

      books.add(existingBook);
    }
    table.setItems(books);

    table.setPrefSize(700, 225);
    updated.getChildren().add(table);

    TextField visitorId = new TextField();
    visitorId.setEditable(true);
    visitorId.setPrefSize(100, 20);
    visitorId.setMaxWidth(100);
    GridPane.setMargin(visitorId, new Insets(5,0,0,30));

    //Restrict to only number input
    visitorId.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue,
                          String newValue) {
        if (!newValue.matches("\\d*")) {
          visitorId.setText(newValue.replaceAll("[^\\d]", ""));
        }
      }
    });

    Label visitorIdLabel = new Label("VisitorId:");
    visitorIdLabel.setPrefSize(200, 20);
    visitorIdLabel.setMaxWidth(200);

    updated.add(visitorIdLabel, 0, 1);
    updated.add(visitorId, 0, 1);

    Button purchaseButton = new Button("Borrow Books");
    purchaseButton.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        Book currentBook = null;
        int totalBooks = 0;
        resultsLabel.setText("");
        ObservableList<TableColumn<Book, ?>>cols = table.getColumns();

        Map<Book, Integer> purchaseBooks = new HashMap<>();
        //loops through each column
        for(TableColumn<Book,?> col : cols){
          //loops through each row
          for(int i = 0; i < books.size(); i++){
            if(cols.get(0).equals(col)){
              if(checkBox.getCellData(i)){
                currentBook = books.get(i);
                purchaseBooks.put(currentBook, Integer.parseInt(visitorId.getText()));
              }
            }
          }
        }

        booksBorrowed(purchaseBooks);
      }
    });
    resultsLabel.setPadding(new Insets(5));
    updated.add(resultsLabel, 0, 2);

    purchaseButton.setPadding(new Insets(5));
    updated.add(purchaseButton, 0,3);
    return updated;
  }

  /**
   *
   * @param booksToPurchase - a map of the books that are to be purchased
   */
  public static void booksBorrowed(Map<Book, Integer> booksToPurchase){
    for(Book book : booksToPurchase.keySet()){
      String command = "borrow," + booksToPurchase.get(book) + "," + book.getIdNum() + ";";
      MainGui.commandParser.parseCommand(command);
    }

    String results = "";
    for(String str : MainGui.commandParser.getMessage()){
      results += str + "\n";
    }
    resultsLabel.setText(results);
    resultsLabel.setPadding(new Insets(5));
    MainGui.commandParser.getMessage().clear();

  }
}
