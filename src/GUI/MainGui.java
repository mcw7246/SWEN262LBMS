package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;



public class MainGui extends Application
{


  @Override
  public void start(Stage primaryStage)
  {
    BorderPane borderPane = new BorderPane();

    Label title = new Label("Welcome to the Library Book Management System");
    title.setFont(new Font("Lucida Sans Unicode", 26));
    borderPane.setTop(title);

    Label release = new Label("Release 2 LBMS: Mikayla Wishart, Yug Patel, Ryan Tytka, Bryan Wang");
    release.setFont(new Font("Lucida Sans Unicode", 15));
    borderPane.setBottom(release);


    borderPane.setCenter(getMainPane());

    BorderPane.setAlignment(borderPane.getBottom(), Pos.CENTER);
    BorderPane.setAlignment(borderPane.getTop(), Pos.CENTER);
    BorderPane.setAlignment(borderPane.getCenter(), Pos.CENTER);

    Scene scene = new Scene(borderPane, 700, 450);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Library Book Management System");
    primaryStage.show();
  }

  public static FlowPane getMainPane(){
    FlowPane flowPane = new FlowPane();

    List<String> commandTypes = Arrays.asList("Advance Time", "Begin Visit", "Book Search", "Borrow Book", "Borrowed Books", "Date Time", "End Visit", "New Visitor", "Pay Fines", "Purchase Book", "Report", "Return Book", "Store Search");




    ImageView pages[] = new ImageView[13];
    try
    {
      for (int i = 0; i < 13; i++)
      {
        int objectNum = i;
        int num = i + 1;
        Image image = new Image(new FileInputStream("src/Images/" + Integer.toString(num) + ".png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        pages[i] = imageView;

        String methodName = commandTypes.get(i).replace(" ", "");
        final String methodNameFinal = methodName.substring(0,1).toLowerCase() + methodName.substring(1, methodName.length());
        String objectName = commandTypes.get(i).replace(" ", "");
        final String objectNameFinal = objectName + "Gui.java";


        Button button = new Button(commandTypes.get(i));
        button.setGraphic(pages[i]);
        button.setContentDisplay(ContentDisplay.TOP);
        button.setPrefSize(100, 100);
        button.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent actionEvent)
          {
            try{
              Class c = Class.forName(objectNameFinal);

              Object objects = c.getDeclaredConstructor().newInstance();

              Method method = c.getDeclaredMethod(methodNameFinal, null);
              method.setAccessible(true);
              method.invoke(objects, null);
            }catch(Exception e){
              e.printStackTrace();
            }


          }
        });
        flowPane.getChildren().add(button);
      }
    }
    catch(FileNotFoundException e){
      System.out.println(e.getMessage());
    }

    flowPane.setHgap(10);
    flowPane.setVgap(10);
    flowPane.setPadding(new Insets(20));
    return flowPane;
  }

  public static GridPane mainPage(GridPane gridPane){


    return null;
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}
