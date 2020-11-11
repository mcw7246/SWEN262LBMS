package GUI;

import Books.Book;
import Books.BookStore;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.util.*;

/**
 * GUI for the StoreSearch and incorporates the GUI for PurchaseBook
 *
 * @author Mikayla Wishart
 */
public class StoreSearchGui
{
  static String name;
  static Label resultsLabel;
  static String authors;
  static String isbn;
  static String publisher;
  static String purchaseNum;
  static String sortOrd;
  static GridPane updated = new GridPane();

  public static GridPane storeSearch(){
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
        name = bookName.getText();
        isbn = bookISBN.getText();
        publisher = bookPublisher.getText();
        authors = bookAuthors.getText();
        sortOrd = sortOrder.getText();
        purchaseNum = "0";

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
    //creates the command and puts it through the command parser
    String cmd = "search,\"" + name + "\",{" + authors + "}," + isbn + "," + publisher + "," + sortOrd + ";";
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
        return new CheckBoxTableCell<Book, Boolean>();
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

    TextField numQty = new TextField();
    numQty.setEditable(true);
    numQty.setPrefSize(100, 20);
    numQty.setMaxWidth(100);
    GridPane.setMargin(numQty, new Insets(5,0,0,30));

    //Restrict to only number input
    numQty.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue,
                          String newValue) {
        if (!newValue.matches("\\d*")) {
          numQty.setText(newValue.replaceAll("[^\\d]", ""));
        }
      }
    });

    Label qtyLabel = new Label("Qty:");
    qtyLabel.setPrefSize(100, 20);
    qtyLabel.setMaxWidth(100);

    updated.add(qtyLabel, 0, 1);
    updated.add(numQty, 0, 1);

    Button purchaseButton = new Button("Purchase Books");
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
                purchaseBooks.put(currentBook, Integer.parseInt(numQty.getText()));
              }
            }
          }
        }

        booksPurchase(purchaseBooks);
      }
    });
    resultsLabel.setPadding(new Insets(5));
    updated.add(resultsLabel, 0, 2);

    purchaseButton.setPadding(new Insets(5));
    updated.add(purchaseButton, 0,3);
    return updated;
  }

  public static class CheckBoxTableCell<S,T> extends TableCell<S,T>{
    private final CheckBox checkBox;
    private ObservableValue<T> value;

    public CheckBoxTableCell(){
      this.checkBox = new CheckBox();
      this.checkBox.setAlignment(Pos.CENTER);

      setAlignment(Pos.CENTER);
      setGraphic(checkBox);
    }

    @Override
    public void updateItem(T item, boolean empty){
      super.updateItem(item, empty);
      if(empty){
        setText(null);
        setGraphic(null);
      }else{
        setGraphic(checkBox);
        if(value instanceof BooleanProperty){
          checkBox.selectedProperty().unbindBidirectional((BooleanProperty)value);
        }
        value = getTableColumn().getCellObservableValue(getIndex());
        if(value instanceof BooleanProperty){
          checkBox.selectedProperty().bindBidirectional((BooleanProperty)value);
        }
      }
    }
  }

  public static void booksPurchase(Map<Book, Integer> booksToPurchase){
    for(Book book : booksToPurchase.keySet()){
      String command = "buy," + booksToPurchase.get(book) + "," + Integer.toString(book.getIdNum()) + ";";
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
