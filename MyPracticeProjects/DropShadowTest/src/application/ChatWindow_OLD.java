package application;

import com.sun.javafx.geom.Rectangle;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChatWindow_OLD {
final private Stage stage = new Stage(StageStyle.UNDECORATED);
private Scene scene;
private Group rg;
private Text t = new Text();
private double initx = 0, inity = 0;

public ChatWindow_OLD() {

    rg = new Group();
    scene = new Scene(rg, 320, 240);
    //scene.setFill(null);
    scene.setFill(new Color(0, 0, 0, 0));
    stage.setScene(scene);
    stage.show();
    setupStage();
}

private void setupStage() {
	javafx.scene.shape.Rectangle r = new javafx.scene.shape.Rectangle(5.0, 5.0, stage.getWidth() - 10.0, stage.getHeight() - 10.0); 
    r.setFill(Color.STEELBLUE);
    r.setEffect(new DropShadow());
    rg.setOnMousePressed(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent me) {
            initx = me.getScreenX() - stage.getX();// - me.getSceneX(); 
            inity = me.getScreenY() - stage.getY();
        }
    });
    rg.setOnMouseDragged(new EventHandler<MouseEvent>() {

        public void handle(MouseEvent me) {
            stage.setX(me.getScreenX() - initx);
            stage.setY(me.getScreenY() - inity);
        }
    });
    rg.getChildren().add(r);
    rg.getChildren().add(t);
}

public void setVisible() {
    stage.show();
}
}