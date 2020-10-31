package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainGui extends Application
{

  @Override
  public void start(Stage primaryStage)
  {
    Group root = new Group();

    GridPane gridPane = new GridPane();

    mainPage(gridPane);

    root.getChildren().add(gridPane);
    Scene scene = new Scene(root, 600, 300);

    primaryStage.setTitle("Library Book Management System");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static GridPane mainPage(GridPane gridPane)
  {
    gridPane.getChildren().clear();
    Label title = new Label("Welcome to Library Book Management System!");

    Label commandType = new Label("Choose which action you would like: ");
    MenuButton commandTypes = new MenuButton("Actions");

    MenuItem advanceTime = new MenuItem("Advance Time");
    advanceTime.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        AdvanceTimeGui advanceTimeGui = new AdvanceTimeGui(gridPane);
        advanceTimeGui.advanceTimeGridPane();
      }
    });
    MenuItem beginVisit = new MenuItem("Begin Visit");
    beginVisit.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        BeginVisitGui beginVisitGui = new BeginVisitGui(gridPane);
        beginVisitGui.beginVisitGridPane();

      }
    });
    MenuItem bookSearch = new MenuItem("Library Book Search");
    bookSearch.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        BookSearchGui bookSearchGui = new BookSearchGui(gridPane);
        bookSearchGui.searchGridPane();
      }
    });
    MenuItem borrowBook = new MenuItem("Borrow Book");
    borrowBook.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        BorrowBookGui borrowBookGui = new BorrowBookGui(gridPane);
        borrowBookGui.borrowBookGridPane();
      }
    });
    MenuItem borrowedBooks = new MenuItem("Borrowed Books");
    borrowedBooks.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        BorrowedBooksGui borrowedBooksGui = new BorrowedBooksGui(gridPane);
        borrowedBooksGui.borrowedBooksGridPane();
      }
    });
    MenuItem dateTime = new MenuItem("Date Time");
    dateTime.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        DateTimeGui dateTimeGui = new DateTimeGui(gridPane);
        dateTimeGui.dateTimeGrid();
      }
    });
    MenuItem endVisit = new MenuItem("End Visit");
    endVisit.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        EndVisitGui endVisitGui = new EndVisitGui(gridPane);
        endVisitGui.endVisitGrid();
      }
    });
    MenuItem newVisitor = new MenuItem("Register Visitor");
    newVisitor.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        NewVisitorGui newVisitorGui = new NewVisitorGui(gridPane);
        newVisitorGui.newVisitorGrid();
      }
    });
    MenuItem payFines = new MenuItem("Pay Fins");
    payFines.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        PayFinesGui payFinesGui = new PayFinesGui(gridPane);
        payFinesGui.payFinesGrid();
      }
    });
    MenuItem purchaseBook = new MenuItem("Purchase Book");
    purchaseBook.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        PurchaseBookGui purchaseBookGui = new PurchaseBookGui(gridPane);
        purchaseBookGui.purchaseBookGrid();
      }
    });
    MenuItem report = new MenuItem("Generate Report");
    report.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        ReportGui reportGui = new ReportGui(gridPane);
        reportGui.reportGrid();
      }
    });
    MenuItem returnBook = new MenuItem("Return Book");
    returnBook.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        ReturnBookGui returnBookGui = new ReturnBookGui(gridPane);
        returnBookGui.returnBookGrid();
      }
    });
    MenuItem storeSearch = new MenuItem("Store Search");
    storeSearch.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent actionEvent)
      {
        StoreSearchGui storeSearchGui = new StoreSearchGui(gridPane);
        storeSearchGui.storeSearchGrid();
      }
    });

    commandTypes.getItems().addAll(advanceTime, beginVisit, bookSearch, borrowBook, borrowedBooks
            , dateTime, endVisit, newVisitor, payFines, purchaseBook, report, returnBook,
            storeSearch);

    commandTypes.show();

    gridPane.add(title, 1, 0);
    gridPane.add(commandType, 1, 1);
    gridPane.add(commandTypes, 1, 2);


    return gridPane;
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}
