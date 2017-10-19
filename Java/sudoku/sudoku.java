import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.StageStyle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import java.io.IOException;
import javafx.scene.shape.Line;
import javafx.scene.control.*;

class square{
  int x;
  int y;
  int num;
  boolean p = false;
  boolean loop = false;
  int most = 0;

  square(int x, int y, int num, boolean p, boolean loop, int most) {
    this.x = x;
    this.y = y;
    this.num = num;
    this.p = p;
    this.loop = loop;
    this.most = most;
  }
  public void setNum(int newNum){
    num = newNum;
  }
  public void setLoop(boolean newLoop){
    loop = newLoop;
  }
  public void setMost(int newMost){
    most = newMost;
  }
}

public class sudoku extends Application {
  @Override
  public void start(Stage stage) {
    Group root = new Group();  // creates the root group
    Scene scene = new Scene(root, 360, 385); // creates the scene
    stage.setScene(scene);  //sets the scene on the stage
    stage.show(); // shows the contents of the stage

//##############################################################################

    TextField[][] tf = new TextField[10][10];
    int x = 1;
    int y = 1;
    for(y = 1; y < 10; y++){
      for(x = 1; x < 10; x++){
        tf[x][y] = new TextField();
        tf[x][y].setLayoutX(40 * x + 6 - 40);
        tf[x][y].setLayoutY(40 * y + 7 - 40);
        tf[x][y].setPrefWidth(35);
        tf[x][y].setStyle("-fx-background-color: white; -fx-text-fill : black; -fx-display-caret: true;"); // sets the textfield styling
        final int m = 1;
        tf[x][y].setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= m ? change : null));
        root.getChildren().add(tf[x][y]);
      }
      x = 0;
    }

    Line[] lv = new Line[8]; x = 0; for(int i = 0; i < 8; i++){
    x += 40;
    lv[i] = new Line(x, 0, x, 360);  //startX, startY, endX, endY
    if(i == 2 || i == 5){
      lv[i].setStroke(Color.BLACK);
    }else{
      lv[i].setStroke(Color.GRAY);
    }
      root.getChildren().add(lv[i]);
    }

    Line[] lh = new Line[9];
    y = 0;
    for(int j = 0; j < 9; j++){
      y += 40;
      lh[j] = new Line(0, y, 360, y);  //startX, startY, endX, endY
      if(j == 2 || j == 5 || j == 8){
        lh[j].setStroke(Color.BLACK);
      }else{
        lh[j].setStroke(Color.GRAY);
      }
      root.getChildren().add(lh[j]);
    }

    Button solve = new Button();
    solve.setText("solve");
    //solve.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
    solve.setLayoutX(150 - (solve.getWidth() / 2) + 6);
    solve.setLayoutY(360);
    root.getChildren().add(solve);

//------------------------------------------------------------------------------


    solve.setOnAction(new EventHandler<ActionEvent>(){
      @Override
       public void handle(ActionEvent event){
         square square[][] = new square[10][10];
           int x = 1;
           int y = 1;
           for(y = 1; y < 10; y++){
             for(x = 1; x < 10; x++){
               if(tf[x][y].getText().equals("")){
                 square[x][y] = new square(x, y, 0, false, false, 0);
               }else{
                 square[x][y] = new square(x, y, Integer.parseInt(tf[x][y].getText()), true, false, 0);
               }
               if(square[x][y].p == true){
                 tf[x][y].setStyle("-fx-background-color: white; -fx-text-fill : red; -fx-display-caret: true;"); // sets the textfield styling
               }
               //System.out.println("(" + square[x][y].x + "," + square[x][y].y + ") = " + square[x][y].num);
               }
               x = 0;
             }

             int i = 0;
             y = 1;
             x = 1;
             for(y = 1; y < 10; y++){ //goes through column
               for(x = 1; x < 10; x++){ //goes through row
                 if(square[x][y].p == false){ //checks if it's og
                   for(i = square[x][y].most + 1; i < 10; i++){ //goes through possible numbers
                     square[x][y].setLoop(true);
                     //tf[x][y].setText(Integer.toString(i));
                     boolean used = false;
                     for(int k = 1; k < 10; k++){ //looks through row;
                       if(square[k][y].num == i || square[x][k].num == i){ //checks row and column for usage
                         used = true;
                         break;
                       }
                     }
                     if(used == false){
                       System.out.println("Setting (" + x + "," + y + ") to " + i);
                       square[x][y].setNum(i);
                       tf[x][y].setText(Integer.toString(square[x][y].num));
                       square[x][y].setLoop(false);
                       break;
                     }
                   }
                   if(square[x][y].loop == true){
                     square[x][y].num = 0; //THIS IS WHAT IS BREAKING IT
                     x-=2;
                   }
                 }
                 square[x][y].setMost(i);
                 if(x != 9){
		 square[x + 1][y].setMost(0);
                 }else{
	           square[1][y + 1].setMost(0);
		 }
               }
             }
           }
         });


//##############################################################################

    stage.setTitle("Sudoku Solver");
  }// method
   public static void main(String args[]){// runs the main method
     launch(args); // launches the javafx window

  }  // method
}// class
