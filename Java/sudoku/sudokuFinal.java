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

public class sudokuFinal extends Application {
  public int[][] s = new int[10][10];

  public boolean checksOut(int x, int y, int i){
    if(hCheck(x, y, i) == true && vCheck(x, y, i) == true && bCheck(x, y, i) == true){
      return true;
    }else{
      return false;
    }
  }

  public boolean hCheck(int x, int y, int i){
    for(int h = 1; h < 10; h++){
      if(s[h][y] == i){
        return false;
      }
    }
    return true;
  }

  public boolean vCheck(int x, int y, int i){
    for(int v = 1; v < 10; v++){
      if(s[x][v] == i){
        return false;
      }
    }
    return true;
  }

  public boolean bCheck(int x, int y, int i){
    int hq = (x - 1) / 3; 
    int vq = (y - 1) / 3;
    int j = 3 * hq + 1;
    int k = 3 * vq + 1;
    for(k = 3 * vq + 1; k < 3 * vq + 4; k++){
      for(j = 3 * hq + 1; j < 3 * hq + 4; j++){
        if(s[j][k] == i){
	  return false;
	}
      }
      j = 3 * hq + 1;
    }
    return true;
  }

  public int findNum(int x, int y, int i){
    while(i < 9){
      i++;
      //System.out.println("Testing " + i + " for (" + x + "," + y + ")");
      if(checksOut(x, y, i) == true){
        //System.out.println("Setting " + i + " for (" + x + "," + y + ")");
      return i;
      }
    }
    return 0;
  }

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
  solve.setLayoutX(128);
  solve.setLayoutY(360);
  root.getChildren().add(solve);

  Button clear = new Button();
  clear.setText("clear");
  int gw = (int) clear.getWidth();
  clear.setLayoutX(180);
  clear.setLayoutY(360);
  root.getChildren().add(clear);

//--------------------------------------------------------------------------


  boolean[][] p = new boolean[10][10];

  solve.setOnAction(new EventHandler<ActionEvent>(){
    @Override
    public void handle(ActionEvent event){
      for(int j = 1; j < 10; j++){
        for(int i = 1; i < 10; i++){
          if(tf[i][j].getText().equals("")){}else{
	    s[i][j] = Integer.parseInt(tf[i][j].getText());
	    tf[i][j].setFont(Font.font("Verdana", FontWeight.BOLD, 12));
	    p[i][j] = true;
    	  }
        }
      }
      int x = 1;
      int y = 1;
      boolean b = false;
      sudokuFinal sf = new sudokuFinal();
      while(y < 10){
        while(x < 10){
	  tf[x][y].setText(Integer.toString(s[x][y]));
          if(p[x][y] != true){
    	    s[x][y] = findNum(x, y, s[x][y]);
  	    if(s[x][y] == 0){
  	      x --;
  	      b = true;
	    }else{
	      x++;
	      b = false;
	    }
	  }else{
	    if(b == true){
	      x--;
	    }else{
	      x++;
	    }
	  }
          if(x == 0){
	    x = 9;
	    y -= 1;
	  }
        }
        x = 1;
        y++;
      }
      for(int j = 1; j < 10; j++){
        for(int i = 1; i < 10; i++){
        tf[i][j].setText(Integer.toString(s[i][j]));
        }
      }
    }
  });

  clear.setOnAction(new EventHandler<ActionEvent>(){
    @Override
    public void handle(ActionEvent event){
      int q = 1;
      int w = 1;
      for(w = 1; w < 10; w++){
        for(q = 1; q < 10; q++){
	  tf[q][w].setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
	  tf[q][w].setText("");
	  s[q][w] = 0;
	  p[q][w] = false;
	}
	q = 1;
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
