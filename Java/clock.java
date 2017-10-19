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
import javafx.scene.transform.Rotate;
import javafx.scene.shape.Rectangle;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.animation.Interpolator;
import javafx.scene.shape.Circle;
import java.util.Calendar;

public class clock extends Application {
   @Override
   public void start(Stage stage) {
     Group root = new Group();  // creates the root group
     Scene scene = new Scene(root, 400, 400); // creates the scene
     stage.setScene(scene);  //sets the scene on the stage
     stage.show(); // shows the contents of the stage

     Circle circle = new Circle(200,200,200); //radius x, radius y, radius
     circle.setFill(Color.WHITE);
     circle.setStroke(Color.BLACK);
     circle.setStrokeWidth(4);
     root.getChildren().add(circle);

     Rectangle[] tick = new Rectangle[60];
     int i = 0;
     int j = 0;
     for(i = 0; i < 60; i++){
       j += 6;
       tick[i] = new Rectangle(199, 0, 2, 10);
       tick[i].getTransforms().add(new Rotate(j, 200, 200));
       root.getChildren().add(tick[i]);
     }

     Rectangle[] stick = new Rectangle[12];
     i = 0;
     j = 0;
     for(i = 0; i < 12; i++){
       j += 30;
       stick[i] = new Rectangle(198, 0, 4, 15);
       stick[i].getTransforms().add(new Rotate(j, 200, 200));
       root.getChildren().add(stick[i]);
     }

     Circle centerCircle = new Circle(200, 200, 10);

     DateFormat dfhour = new SimpleDateFormat("HH");
     DateFormat dfminute = new SimpleDateFormat("mm");
     DateFormat dfsecond = new SimpleDateFormat("ss");
     Date dateobj = new Date();
     int hours = Integer.parseInt(dfhour.format(dateobj));
     int minutes = Integer.parseInt(dfminute.format(dateobj));
     int seconds = Integer.parseInt(dfsecond.format(dateobj));
     if(hours > 12){
       hours -= 12;
     }
     Double hoursRotation = ((((double)hours) / 12) * 360) + (((double)minutes) / 60) * 30;
     Double minutesRotation = ((((double)minutes) / 60) * 360) + (((double)seconds) / 60) * 6;
     Double secondsRotation = (((double)seconds) / 60) * 360;

     Rectangle s = new Rectangle(199, 10, 2, 380); // creates one line of the x
     s.setStyle("-fx-fill:linear-gradient( from 0.0% 0.0% to 100.0% 100.0%, rgb(255,0,0) 0.5," + "transparent" +" 0.5);");
     s.setRotate(secondsRotation);
     root.getChildren().add(s);  // adds the line to the scene

     Rectangle m = new Rectangle(197, 20, 4, 360); // creates one line of the x
     m.setStyle("-fx-fill:linear-gradient( from 0.0% 0.0% to 100.0%  100.0%, rgb(0,0,0) 0.5," + "transparent" +" 0.5);");
     m.setRotate(minutesRotation);
     root.getChildren().add(m);  // adds the line to the scene

     Rectangle h = new Rectangle(196, 60, 6, 280); // creates one line of the x
     h.setStyle("-fx-fill:linear-gradient( from 0.0% 0.0% to 100.0%  100.0%, rgb(0,0,0) 0.5," + "transparent" +" 0.5);");
     h.setRotate(hoursRotation);
     root.getChildren().add(h);  // adds the line to the scene

     RotateTransition rts = new RotateTransition(Duration.minutes(1), s);
     rts.setByAngle(360); // sets rotation degrees
     rts.setCycleCount(Animation.INDEFINITE);
     rts.setInterpolator(Interpolator.LINEAR);
     rts.play();

     RotateTransition rtm = new RotateTransition(Duration.minutes(60), m);
     rtm.setByAngle(360);
     rtm.setCycleCount(Animation.INDEFINITE);
     rtm.setInterpolator(Interpolator.LINEAR);
     rtm.play();

     RotateTransition rth = new RotateTransition(Duration.minutes(1440), h);
     rth.setByAngle(360);
     rth.setCycleCount(Animation.INDEFINITE);
     rth.setInterpolator(Interpolator.LINEAR);
     rth.play();

     root.getChildren().add(centerCircle);
     scene.setFill(Color.WHITE);
   }// method
   public static void main(String args[]){// runs the main method
      launch(args); // launches the javafx window

   }  // method
}
