import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application
{

  @Override
  public void start(Stage primaryStage){

    Group root = new Group();


    GridPane gridPane = new GridPane();

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





    //adds sortorder to gridpane
    gridPane.add(sortOrderLabel, 0,4);
    gridPane.add(sortOrder, 1, 4);

    //adds publisher to gridpane
    bookPublisher.setPrefSize(200,20);
    gridPane.add(publisherLabel, 0,3);
    gridPane.add(bookPublisher, 1, 3);

    //adds isbn to the gridpane
    bookISBN.setPrefSize(200, 20);
    gridPane.add(isbnLabel,0,2);
    gridPane.add(bookISBN, 1, 2);

    //adds authors to the gridpane
    bookAuthors.setPrefSize(200, 20);
    gridPane.add(authorLabel, 0, 1);
    gridPane.add(bookAuthors, 1, 1);

    //adds title to the gridpane
    bookName.setPrefSize(200,20);
    gridPane.add(titleLabel, 0,0);
    gridPane.add(bookName, 1,0);

    gridPane.setVgap(10);

    root.getChildren().add(gridPane);

    Scene scene = new Scene(root,600,300);


    primaryStage.setTitle("Library Book Management System");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args){
    launch(args);
  }
}
