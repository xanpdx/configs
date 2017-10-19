import java.util.*;
import javafx.application.*;
import javafx.scene.shape.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;

public class barefx extends Application{
Stage window;
  public static void main(String[] args){
    launch (args);
  }
    @Override
    public void start(Stage primaryStage) throws Exception{
      window = primaryStage;
      Circle circle = new Circle(200, 200, 200);
      Group root = new Group(circle);
      Scene scene = new Scene(root, 400, 400);
      primaryStage.setScene(scene);
      primaryStage.show();
    }
  }
